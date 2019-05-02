package dk.nytmodultest.studieportal.ui.activities

import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.google.gson.Gson
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.Exercise
import kotlinx.android.synthetic.main.activity_chrdbcall.*
import java.net.URL

class ChrDBcall : AppCompatActivity() {


    private inner class GetListeningExercise: AsyncTask<String, Int, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            val databaseGet: String
            try{
                //use "10.0.2.2" instead of localhost (ottherwise you get an exception)
                databaseGet =  URL("http://10.0.2.2:8000/api/findQuestionsByExerciseId/1").readText()
            } catch (e: Exception) {
                //return e.toString()
                return "Database error"
            }

            val article = Gson().fromJson(databaseGet, Exercise::class.java)
            return article.toString()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            //super.onPostExecute(result)
            val myresult = result
            act_textcontent.text = "${myresult}"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrdbcall)
        act_textcontent.setMovementMethod(ScrollingMovementMethod());
        GetListeningExercise().execute()

    }

}