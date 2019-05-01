package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import dk.nytmodultest.studieportal.domain.model.*
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.CursorAdapter
import android.widget.LinearLayout
import com.google.gson.Gson
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.ui.CustomAdapter
import java.net.URL

class Listening : AppCompatActivity(){

    private inner class GetListeningExercise: AsyncTask<String, Int, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            val databaseGet: String
            databaseGet =  URL("http://10.0.2.2:8000/api/findQuestionsByExerciseId/1").readText()
            //try{
                //use "10.0.2.2" instead of localhost (ottherwise you get an exception)
                //databaseGet =  URL("http://10.0.2.2:8000/api/findQuestionsByExerciseId/1").readText()
            //} catch (e: Exception) {
                //return e.toString()
                //return "Database error"
            //}
            return databaseGet

            //val article = Gson().fromJson(databaseGet, Exercise::class.java)
            //return article
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            //super.onPostExecute(result)
            val myresult: Exercise = Gson().fromJson(result, Exercise::class.java)
            val media: String = myresult.media
            val studentInstructions: String = myresult.studentInstructions

            XMLmedia.text = media
            XMLstudentIns.text = studentInstructions


            //val recyclerView = findViewById(R.id.XML_RW_questions) as RecyclerView
            //recyclerView.layoutManager = LinearLayoutManager(this@Listening, LinearLayout.VERTICAL, false)
            //val adapter = CustomAdapter(myresult.questions)
            //recyclerView.adapter = adapter

        }

    }

    //TextView textView = (TextView) findViewById(R.id.text_view);
    //textView.setMovementMethod(new ScrollingMovementMethod());

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)
        //act_textcontent.setMovementMethod(ScrollingMovementMethod());
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)



        GetListeningExercise().execute()

    }




}