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
import kotlinx.android.synthetic.main.activity_vocabulary.*
import kotlinx.coroutines.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class Vocabulary : AppCompatActivity() {

    val donaldVocabURL = "http://192.168.8.100:8000/api/get-weighted-words/1/5"
    lateinit var vocabQuestionFromDb: ArrayList<VocabWord>
    val vocabAnswersToDb = ArrayList<String>()
    lateinit var currentVordObj: VocabWord


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        doAsync {
            val str = URL(donaldVocabURL).readText()
            uiThread {
                vocabQuestionFromDb = parseJSONlist(parseString(str))

                currentVordObj = vocabQuestionFromDb[0]
                vocab_danish.text = currentVordObj.id.toString()
                vocab_english.text = currentVordObj.english

                //while(vocabQuestionFromDb.size > 0){
                //    currentVordObj = vocabQuestionFromDb[0]
                //    vocab_danish.text = currentVordObj.word
                //    vocab_english.text = currentVordObj.english
                //}

                longToast("You are done!")


            }
        }


        vocab_know.setOnClickListener{
            if (!vocabQuestionFromDb.isNullOrEmpty()) {
                val currentVordObj = vocabQuestionFromDb[0]
                vocabAnswersToDb.add(currentVordObj.id.toString())
                vocabAnswersToDb.add("know")
                longToast(vocabAnswersToDb.toString())
            } else {
                longToast("array er null eller tom 1")
            }

        }

        vocab_dontknow.setOnClickListener{
            if (!vocabQuestionFromDb.isNullOrEmpty()) {
                val currentVordObj = vocabQuestionFromDb[0]
                vocabAnswersToDb.add(currentVordObj.id.toString())
                vocabAnswersToDb.add("dontknow")
                longToast(vocabAnswersToDb.toString())
            } else {
                longToast("array er null eller tom 2")
            }
        }

        longToast("hello")




    }


    fun parseJSONlist(JSONArray: ArrayList<String>): ArrayList<VocabWord> {
        val gson = Gson()
        val output = ArrayList<VocabWord>()
        for (item in JSONArray) {
            val VWobject = gson.fromJson(item, VocabWord::class.java)
            output.add(VWobject)
        }
        return output
    }

    fun parseString(rawJSONList: String): ArrayList<String>{
        if (rawJSONList.length > 4) {
            val removeFirstAndLast = rawJSONList.drop(2).dropLast(2)
            val splitJSON = removeFirstAndLast.split("},{")
            val addCurly = ArrayList<String>()
            for (item in splitJSON){
                val withCurly = "{"+item+"}"
                addCurly.add(withCurly)
            }
            return addCurly
        } else{
            return ArrayList<String>()
        }



    }





}
