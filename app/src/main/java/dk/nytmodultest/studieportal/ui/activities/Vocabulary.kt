package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.VocabWord
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import com.google.gson.Gson
import dk.nytmodultest.studieportal.domain.model.postVocabWord
import kotlinx.android.synthetic.main.activity_vocabulary.*
import kotlinx.coroutines.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.toast
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URLConnection
import java.util.concurrent.TimeUnit

class Vocabulary : AppCompatActivity() {

    val donaldVocabURL = "http://192.168.8.100:8000/api/get-weighted-words/1/5"
    val donaldPostURL = "http://192.168.8.100:8000/api/studentwords"
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
                vocab_danish.text = currentVordObj.word.toString()
                vocab_english.text = currentVordObj.english

                //longToast("You are done!")


            }
        }


        vocab_know.setOnClickListener{
            actionOnQuestion(vocabQuestionFromDb, vocabAnswersToDb, true)

        }

        vocab_dontknow.setOnClickListener{
            actionOnQuestion(vocabQuestionFromDb, vocabAnswersToDb, false)
        }

        //longToast("hello")




    }


    

    fun actionOnQuestion(questionList: ArrayList<VocabWord>, answerList: ArrayList<String>, knownWord: Boolean){
        if (questionList.isNullOrEmpty()) {
            //longToast(this.vocabAnswersToDb.toString())
        } else {
            val currentVordObj = questionList[0]
            if (knownWord) {
                answerList.add(currentVordObj.id.toString())
            }
            questionList.removeAt(0)
            if (questionList.isNullOrEmpty()) {


                val url = URL("http://192.168.8.100:8000/api/studentwords");
                //lateinit var client: URLConnection
                try {

                    val gson = Gson()
                    val myPostObj = postVocabWord(6,1)
                    val myPostJSON = gson.toJson(myPostObj, postVocabWord::class.java)

                    httpPostJson(myPostJSON)

                } catch (e: Exception) {
                    longToast("Fejl ved database kald")
                }

            } else {
                val nextVordObj = questionList[0]
                vocab_danish.text = nextVordObj.word.toString()
                vocab_english.text = nextVordObj.english
                //longToast(answerList.toString())
            }
        }
    }

    fun httpPostJson(JSONbody: String) {
        try {
            Fuel.post(donaldPostURL)
                .jsonBody(JSONbody).response { request, response, result ->
                //longToast("virkede det?: " + response.statusCode.toString())
            }
        } catch (e: Exception) {
            longToast("post forsøg fejlet :(")
        } finally {
        }
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
