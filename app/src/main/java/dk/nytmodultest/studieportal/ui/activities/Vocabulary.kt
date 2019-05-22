package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.VocabWord
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import com.google.gson.Gson
import dk.nytmodultest.studieportal.domain.datasource.Host
import dk.nytmodultest.studieportal.domain.model.postVocabWord
import kotlinx.android.synthetic.main.activity_vocabulary.*
import java.lang.Exception

class Vocabulary : AppCompatActivity() {

    val DB_URL = Host().currentHostUrl//"http://192.168.8.100:8000"
    val getWords_Path = "/api/get-weighted-words/"
    val postWord_path = "/api/studentwords"
    val numOfVocabTofetch = 5

    lateinit var donaldVocabURL: String//"/api/get-weighted-words/1/5"
    val donaldPostURL = DB_URL + postWord_path
    var studentIDvocabulary = -1
    lateinit var vocabQuestionFromDb: ArrayList<VocabWord>
    lateinit var currentVordObj: VocabWord
    var know_count = 0
    var dontknow_count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
        studentIDvocabulary = intent.getStringExtra("username").toInt()
        //the number "5" below is
        donaldVocabURL = DB_URL + getWords_Path + studentIDvocabulary.toString()+ "/" +numOfVocabTofetch


        getQuestions()

        vocab_know.setOnClickListener{
            actionOnQuestion(vocabQuestionFromDb, true)
        }

        vocab_dontknow.setOnClickListener{
            actionOnQuestion(vocabQuestionFromDb, false)
        }

        vocab_english.setOnClickListener{
            vocab_english.text = currentVordObj.english
        }
    }


    fun getQuestions(){
        doAsync {
            val str = URL(donaldVocabURL).readText()
            uiThread {
                vocabQuestionFromDb = parseJSONlist(parseString(str))

                currentVordObj = vocabQuestionFromDb[0]
                vocab_danish.text = currentVordObj.word.toString()
                vocab_english.text = "???"

            }
        }
    }


    fun actionOnQuestion(questionList: ArrayList<VocabWord>, knownWord: Boolean){
        if (questionList.isNullOrEmpty()) {
            longToast("Nothing to practice at this moment. Try again later")
        } else {
            this.currentVordObj = questionList[0]
            if (knownWord) {
                try {
                    val gson = Gson()
                    val myPostObj = postVocabWord(currentVordObj.id, studentIDvocabulary)
                    val myPostJSON = gson.toJson(myPostObj, postVocabWord::class.java)
                    httpPostJson(myPostJSON)

                } catch (e: Exception) {
                    //longToast("Error")
                }
                know_count += 1
                vocab_knowCount.text = know_count.toString()
            }else {
                dontknow_count += 1
                vocab_dontKCount.text = dontknow_count.toString()
            }
            questionList.removeAt(0)
            if (questionList.isNullOrEmpty()) {

                getQuestions()

            } else {
                this.currentVordObj = questionList[0]
                vocab_danish.text = this.currentVordObj.word.toString()
                vocab_english.text = "???"
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
            longToast("database error: (Vocabulary.kt): your answer didnt react the database")
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
