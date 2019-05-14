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
import com.google.gson.reflect.TypeToken
import dk.nytmodultest.studieportal.domain.model.VocabWorkList

class Vocabulary : AppCompatActivity() {

    val donaldVocabURL = "http://192.168.8.100:8000/api/get-weighted-words/1/5"
    lateinit var vocabQuestionFromDb: List<VocabWord>
    lateinit var vocabAnswersToDb: List<VocabWord>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        doAsync {


            //val exerciseJsonStr = URL(donaldVocabURL).readText()
            val chrJson = "{\n" +
                    "        \"id\": 1,\n" +
                    "        \"word\": \"Hund\",\n" +
                    "        \"meaning\": \"Kæledyr, der bl.a. bliver brugt til jagt\",\n" +
                    "        \"synonym\": \"Vovse\",\n" +
                    "        \"english\": \"Dog\",\n" +
                    "        \"pronunciation\": \"\",\n" +
                    "        \"image\": \"words/img101.png\",\n" +
                    "        \"createdAt\": \"2019-05-13T00:00:00.000Z\",\n" +
                    "        \"updatedAt\": \"2019-05-13T00:00:00.000Z\"\n" +
                    "    }"

            uiThread{

                val gson = Gson()
                longToast("Vocab request performed 1")
                //profileInfo.text = "Hello ${result.firstName}!"

                //List<Video> videos = gson.fromJson(json, new TypeToken<List<Video>>(){}.getType());

                val output = gson.fromJson(chrJson, VocabWord::class.java)

                //val output = gson.fromJson(exerciseJsonStr, TypeToken<List<VocabWord>>(){}.getType)

                //val topic: List<VocabWord> = gson.fromJson(exerciseJsonStr, VocabWord::class.java)
                longToast("Vocab")
            }

        }


    }





}
