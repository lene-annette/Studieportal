package dk.nytmodultest.studieportal.ui.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import dk.nytmodultest.studieportal.R
import org.jetbrains.anko.startActivity
import java.util.*

class MemoryActivity : AppCompatActivity() {
    lateinit var iv11:ImageView; lateinit var iv12: ImageView; lateinit var iv13: ImageView
    lateinit var iv14: ImageView; lateinit var iv21: ImageView; lateinit var iv22: ImageView
    lateinit var iv23: ImageView; lateinit var iv24: ImageView; lateinit var iv31: ImageView
    lateinit var iv32: ImageView; lateinit var iv33: ImageView; lateinit var iv34: ImageView
    lateinit var iv41: ImageView; lateinit var iv42: ImageView; lateinit var iv43: ImageView
    lateinit var iv44: ImageView

    //array for the images
    var cardsArray: MutableList<Int> = mutableListOf(101,102,103,104,105,106,107,108,201,202,203,204,205,206,207,208)

    //actual images
    private val image101 = R.drawable.img101; private val image102 = R.drawable.img102; private val image103 = R.drawable.img103
    private val image104 = R.drawable.img104; private val image105 = R.drawable.img105; private val image106 = R.drawable.img106
    private val image107 = R.drawable.img107; private val image108 = R.drawable.img108; private val image201 = R.drawable.img201
    private val image202 = R.drawable.img202; private val image203 = R.drawable.img203; private val image204 = R.drawable.img204
    private val image205 = R.drawable.img205; private val image206 = R.drawable.img206; private val image207 = R.drawable.img207
    private val image208 = R.drawable.img208

    var firstCard = 0; var secondCard = 0
    var clickedFirst = 0; var clickedSecond = 0
    var cardNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        init()
        cardsArray.shuffle()

        iv11.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv11, theCard)
        }
        iv12.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv12, theCard)
        }
        iv13.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv13, theCard)
        }
        iv14.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv14, theCard)
        }
        iv21.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv21, theCard)
        }
        iv22.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv22, theCard)
        }
        iv23.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv23, theCard)
        }
        iv24.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv24, theCard)
        }
        iv31.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv31, theCard)
        }
        iv32.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv32, theCard)
        }
        iv33.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv33, theCard)
        }
        iv34.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv34, theCard)
        }
        iv41.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv41, theCard)
        }
        iv42.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv42, theCard)
        }
        iv43.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv43, theCard)
        }
        iv44.setOnClickListener {
            val tag = it.tag
            val theCard = Integer.parseInt(tag.toString())
            doStuff(iv44, theCard)
        }
    }

    private fun doStuff(iv: ImageView,card: Int){
        val imageSrc = when(cardsArray[card]){
            101 -> image101
            102 -> image102
            103 -> image103
            104 -> image104
            105 -> image105
            106 -> image106
            107 -> image107
            108 -> image108
            201 -> image201
            202 -> image202
            203 -> image203
            204 -> image204
            205 -> image205
            206 -> image206
            207 -> image207
            208 -> image208
            else -> R.drawable.back
        }
        iv.setImageResource(imageSrc)

        if(cardNumber == 1){
            firstCard = cardsArray[card]
            if(firstCard > 200){
                firstCard = firstCard - 100
            }
            cardNumber = 2
            clickedFirst = card

            iv.isEnabled = false

        }else if(cardNumber == 2){
            secondCard = cardsArray[card]
            if(secondCard > 200){
                secondCard = secondCard - 100
            }
            cardNumber = 1
            clickedSecond = card

            iv11.isEnabled = false
            iv12.isEnabled = false
            iv13.isEnabled = false
            iv14.isEnabled = false
            iv21.isEnabled = false
            iv22.isEnabled = false
            iv23.isEnabled = false
            iv24.isEnabled = false
            iv31.isEnabled = false
            iv32.isEnabled = false
            iv33.isEnabled = false
            iv34.isEnabled = false
            iv41.isEnabled = false
            iv42.isEnabled = false
            iv43.isEnabled = false
            iv44.isEnabled = false

            val handler = Handler()
            handler.postDelayed({
                calculate()
            },1000)
        }
    }

    private fun calculate(){
        if(firstCard == secondCard){
            val iv1 = when(clickedFirst){
                0 -> iv11
                1 -> iv12
                2 -> iv13
                3 -> iv14
                4 -> iv21
                5 -> iv22
                6 -> iv23
                7 -> iv24
                8 -> iv31
                9 -> iv32
                10 -> iv33
                11 -> iv34
                12 -> iv41
                13 -> iv42
                14 -> iv43
                else -> iv44
            }
            iv1.visibility = View.INVISIBLE

            val iv2 = when(clickedSecond){
                0 -> iv11
                1 -> iv12
                2 -> iv13
                3 -> iv14
                4 -> iv21
                5 -> iv22
                6 -> iv23
                7 -> iv24
                8 -> iv31
                9 -> iv32
                10 -> iv33
                11 -> iv34
                12 -> iv41
                13 -> iv42
                14 -> iv43
                else -> iv44
            }
            iv2.visibility = View.INVISIBLE
        } else {
            val back = R.drawable.back
            iv11.setImageResource(back)
            iv12.setImageResource(back)
            iv13.setImageResource(back)
            iv14.setImageResource(back)
            iv21.setImageResource(back)
            iv22.setImageResource(back)
            iv23.setImageResource(back)
            iv24.setImageResource(back)
            iv31.setImageResource(back)
            iv32.setImageResource(back)
            iv33.setImageResource(back)
            iv34.setImageResource(back)
            iv41.setImageResource(back)
            iv42.setImageResource(back)
            iv43.setImageResource(back)
            iv44.setImageResource(back)
        }
        iv11.isEnabled = true
        iv12.isEnabled = true
        iv13.isEnabled = true
        iv14.isEnabled = true
        iv21.isEnabled = true
        iv22.isEnabled = true
        iv23.isEnabled = true
        iv24.isEnabled = true
        iv31.isEnabled = true
        iv32.isEnabled = true
        iv33.isEnabled = true
        iv34.isEnabled = true
        iv41.isEnabled = true
        iv42.isEnabled = true
        iv43.isEnabled = true
        iv44.isEnabled = true

        checkEnd()
    }

    private fun checkEnd(){
        if(iv11.visibility == View.INVISIBLE &&
            iv12.visibility == View.INVISIBLE &&
            iv13.visibility == View.INVISIBLE &&
            iv14.visibility == View.INVISIBLE &&
            iv21.visibility == View.INVISIBLE &&
            iv22.visibility == View.INVISIBLE &&
            iv23.visibility == View.INVISIBLE &&
            iv24.visibility == View.INVISIBLE &&
            iv31.visibility == View.INVISIBLE &&
            iv32.visibility == View.INVISIBLE &&
            iv33.visibility == View.INVISIBLE &&
            iv34.visibility == View.INVISIBLE &&
            iv41.visibility == View.INVISIBLE &&
            iv42.visibility == View.INVISIBLE &&
            iv43.visibility == View.INVISIBLE &&
            iv44.visibility == View.INVISIBLE){

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

    private fun init(){
        iv11 = findViewById(R.id.iv_11)
        iv12 = findViewById(R.id.iv_12)
        iv13 = findViewById(R.id.iv_13)
        iv14 = findViewById(R.id.iv_14)
        iv21 = findViewById(R.id.iv_21)
        iv22 = findViewById(R.id.iv_22)
        iv23 = findViewById(R.id.iv_23)
        iv24 = findViewById(R.id.iv_24)
        iv31 = findViewById(R.id.iv_31)
        iv32 = findViewById(R.id.iv_32)
        iv33 = findViewById(R.id.iv_33)
        iv34 = findViewById(R.id.iv_34)
        iv41 = findViewById(R.id.iv_41)
        iv42 = findViewById(R.id.iv_42)
        iv43 = findViewById(R.id.iv_43)
        iv44 = findViewById(R.id.iv_44)

        iv11.tag = "0"
        iv12.tag = "1"
        iv13.tag = "2"
        iv14.tag = "3"
        iv21.tag = "4"
        iv22.tag = "5"
        iv23.tag = "6"
        iv24.tag = "7"
        iv31.tag = "8"
        iv32.tag = "9"
        iv33.tag = "10"
        iv34.tag = "11"
        iv41.tag = "12"
        iv42.tag = "13"
        iv43.tag = "14"
        iv44.tag = "15"
    }
}
