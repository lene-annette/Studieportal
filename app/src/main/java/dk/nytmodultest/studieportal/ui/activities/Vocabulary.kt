package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.VocabWord
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import com.google.gson.Gson

class Vocabulary : AppCompatActivity() {

    val donaldVocabURL = "http://192.168.8.100:8000/api/get-weighted-words/1/5"
    lateinit var vocabQuestionFromDb: List<VocabWord>
    lateinit var vocabAnswersToDb: List<VocabWord>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        doAsync {


            val exerciseJsonStr = URL(donaldVocabURL).readText()

            uiThread{


                //longToast(exerciseJsonStr)
                longToast(parseString(exerciseJsonStr))
            }

        }


    }


    fun parseString(rawJSONList: String): String{
        if (rawJSONList.length > 4) {
            val removeFirstAndLast = rawJSONList.drop(2).dropLast(2)
            val splitJSON = removeFirstAndLast.split("},{")
            val addCurly = ArrayList<String>()
            for (item in splitJSON){
                val withCurly = "{"+item+"}"
                addCurly.add(withCurly)
            }
            return addCurly[0]
        } else{
            return "blank"
        }



    }





}
