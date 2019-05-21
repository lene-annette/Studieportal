package dk.nytmodultest.studieportal.ui.activities

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
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
    var fronts: MutableList<TextView> = mutableListOf()
    var backs: MutableList<TextView> = mutableListOf()
    private val wordTable = mutableMapOf<String,String>()
    private val cards = mutableListOf<String>()

    var firstCard = ""; var secondCard = ""
    lateinit var firstView: TextView; lateinit var secondView: TextView
    lateinit var firstBack: TextView; lateinit var secondBack: TextView
    var cardNumber = 1

    private lateinit var mSetRightOut: AnimatorSet; private lateinit var mSetLeftIn: AnimatorSet

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
                backs.forEach {
                    it.setOnClickListener{
                        val tv: TextView = it as TextView
                        val card = Integer.parseInt(tv.tag.toString())

                        flip(tv,card)
                    }
                }
            }
        }
    }

    private fun flip(back: TextView, card: Int){
        val distance = getCameraDistance()
        val src = cards[card]

        var front = fronts[card]

        back.cameraDistance = distance
        front.cameraDistance = distance


        if(src.contains(".png") || src.contains(".jpg")){
            //showImage
            doAsync{
                val bitmap = Picasso.with(front.context).load("${Config.BACKEND}images/$src").get()
                uiThread{
                    val d: Drawable = BitmapDrawable(resources,bitmap)
                    front.background = d
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
            front.setBackgroundResource(R.drawable.sound)
        }else{
            front.text = src
            front.setBackgroundResource(android.R.color.transparent)
        }

        mSetRightOut.setTarget(back)
        mSetLeftIn.setTarget(front)
        mSetRightOut.start()
        mSetLeftIn.start()



        if(cardNumber == 1){
            firstCard = src
            firstView = front
            firstBack = back
            cardNumber = 2

               front.isEnabled = false
        }else if(cardNumber == 2){
            secondCard = src
            secondView = front
            secondBack = back
            cardNumber = 1

            fronts.forEach{
                it.isEnabled = false
            }

            val handler = Handler()
            handler.postDelayed({

                compare()
            },1000)
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
            firstBack.visibility = View.INVISIBLE
            secondBack.visibility = View.INVISIBLE

            checkEnd()
        }else{
            var mOut = AnimatorInflater.loadAnimator(this,R.animator.out_animation) as AnimatorSet
            var mIn = AnimatorInflater.loadAnimator(this,R.animator.in_animation) as AnimatorSet


            mSetRightOut.setTarget(firstView)
            mSetLeftIn.setTarget(firstBack)
            mSetRightOut.start()
            mSetLeftIn.start()

            mOut.setTarget(secondView)
            mIn.setTarget(secondBack)
            mOut.start()
            mIn.start()
        }
        fronts.forEach{
            it.isEnabled = true
        }
    }

    private fun checkEnd(){
        var end = true
        fronts.forEach{
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
        fronts = mutableListOf(
            findViewById(R.id.iv_11_front),
            findViewById(R.id.iv_12_front),
            findViewById(R.id.iv_13_front),
            findViewById(R.id.iv_14_front),
            findViewById(R.id.iv_21_front),
            findViewById(R.id.iv_22_front),
            findViewById(R.id.iv_23_front),
            findViewById(R.id.iv_24_front),
            findViewById(R.id.iv_31_front),
            findViewById(R.id.iv_32_front),
            findViewById(R.id.iv_33_front),
            findViewById(R.id.iv_34_front),
            findViewById(R.id.iv_41_front),
            findViewById(R.id.iv_42_front),
            findViewById(R.id.iv_43_front),
            findViewById(R.id.iv_44_front)
        )

        backs = mutableListOf(
            findViewById(R.id.iv_11_back),
            findViewById(R.id.iv_12_back),
            findViewById(R.id.iv_13_back),
            findViewById(R.id.iv_14_back),
            findViewById(R.id.iv_21_back),
            findViewById(R.id.iv_22_back),
            findViewById(R.id.iv_23_back),
            findViewById(R.id.iv_24_back),
            findViewById(R.id.iv_31_back),
            findViewById(R.id.iv_32_back),
            findViewById(R.id.iv_33_back),
            findViewById(R.id.iv_34_back),
            findViewById(R.id.iv_41_back),
            findViewById(R.id.iv_42_back),
            findViewById(R.id.iv_43_back),
            findViewById(R.id.iv_44_back)
        )

        var i = 0
        fronts.forEach{
            it.tag = i.toString()
            i++
        }

        var j = 0
        backs.forEach{
            it.tag = j.toString()
            j++
        }

        mSetRightOut = AnimatorInflater.loadAnimator(this,R.animator.out_animation) as AnimatorSet
        mSetLeftIn = AnimatorInflater.loadAnimator(this, R.animator.in_animation) as AnimatorSet
    }

    private fun getCameraDistance(): Float {
        var distance = 8000
        return getResources().displayMetrics.density * distance
    }
}
