package dk.nytmodultest.studieportal.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.view.View
import android.widget.TextView
import com.squareup.picasso.Picasso
import dk.nytmodultest.studieportal.Config
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestWordsCommand
import dk.nytmodultest.studieportal.domain.model.Word
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.media.MediaPlayer

class MemoryActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    var imageViews: MutableList<TextView> = mutableListOf()
    val wordTable = mutableMapOf<String,String>()
    val cards = mutableListOf<String>()

    var firstCard = ""; var secondCard = ""
    lateinit var firstView: TextView; lateinit var secondView: TextView
    var cardNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        doAsync{
            val words = RequestWordsCommand(ProfileActivity.ONLINE_USER,8).execute()
            uiThread{
                //val wordTable = mutableMapOf<String,String>()
                words.forEach{
                    wordTable[it.word] = choosePair(it)
                }

                val keys = wordTable.keys.toMutableList()
                val values = wordTable.values.toMutableList()

                cards.addAll(keys); cards.addAll(values)

                cards.shuffle()

                d("Lene","words: $wordTable")
                d("Lene", "cards: $cards")

                init()
                imageViews.forEach {
                    it.setOnClickListener{
                        val tv: TextView = it as TextView
                        val card = Integer.parseInt(tv.tag.toString())

                        flip(tv,card)
                    }
                }

            }
        }
    }

    private fun flip(tv: TextView, card: Int){
        val src = cards[card]

        if(src.contains(".png") || src.contains(".jpg")){
            //showImage
            doAsync{
                val bitmap = Picasso.with(tv.context).load("${Config.BACKEND}images/$src").get()
                uiThread{
                    val d: Drawable = BitmapDrawable(resources,bitmap)
                    tv.background = d
                }
            }
        }else if(src.contains(".mp3")){
            //play sound
            //tv.text = src
            var mp3Url = "${Config.BACKEND}audio/$src"
            doAsync{
                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(mp3Url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
            tv.setBackgroundResource(R.drawable.sound)
        }else{
            tv.text = src
            tv.setBackgroundResource(android.R.color.transparent)
        }

        if(cardNumber == 1){
            firstCard = src
            firstView = tv
            cardNumber = 2

            tv.isEnabled = false
        }else if(cardNumber == 2){
            secondCard = src
            secondView = tv
            cardNumber = 1

            imageViews.forEach{
                it.isEnabled = false
            }

            val handler = Handler()
            handler.postDelayed({
                compare()
            },2000)
        }
    }

    private fun compare(){
        var key = ""
        if(wordTable.containsKey(firstCard)){
            key = firstCard
        }else if(wordTable.containsKey(secondCard)){
            key = secondCard
        }

        if(wordTable[key] == firstCard || wordTable[key] == secondCard){
            firstView.visibility = View.INVISIBLE
            secondView.visibility = View.INVISIBLE

            checkEnd()
        }else{
            val back = R.drawable.back
            imageViews.forEach {
                it.setBackgroundResource(back)
                it.text = ""
            }
        }
        imageViews.forEach{
            it.isEnabled = true
        }
    }

    private fun checkEnd(){
        var end = true
        imageViews.forEach{
            if(it.visibility != View.INVISIBLE){
                end = false
            }
        }
        if(end){
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Game over!")
            builder.setPositiveButton("New Game"){_, _ ->
                startActivity(Intent(this, MemoryActivity::class.java))
                finish()
            }
            builder.setNegativeButton("Exit"){_, _ ->
                finish()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }


    private fun choosePair(word: Word): String {
        val options: MutableList<String> = mutableListOf("meaning","synonym","english","pronunciation","image")
        options.shuffle()

        var result = ""
        var i = 0

        while(result == ""){
            var chosen = options[i]
            result = when(chosen){
                "meaning" -> word.meaning
                "synonym" -> word.synonym
                "english" -> word.english
                "pronunciation" -> word.pronunciation
                "image" -> word.image
                else -> ""
            }
            i++
        }

        return result
    }

    private fun init(){
        imageViews = mutableListOf(
            findViewById(R.id.iv_11),
            findViewById(R.id.iv_12),
            findViewById(R.id.iv_13),
            findViewById(R.id.iv_14),
            findViewById(R.id.iv_21),
            findViewById(R.id.iv_22),
            findViewById(R.id.iv_23),
            findViewById(R.id.iv_24),
            findViewById(R.id.iv_31),
            findViewById(R.id.iv_32),
            findViewById(R.id.iv_33),
            findViewById(R.id.iv_34),
            findViewById(R.id.iv_41),
            findViewById(R.id.iv_42),
            findViewById(R.id.iv_43),
            findViewById(R.id.iv_44)
        )

        var i = 0
        imageViews.forEach{
            it.tag = i.toString()
            i++
        }
    }
}
