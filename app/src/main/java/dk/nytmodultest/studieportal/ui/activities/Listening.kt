package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import dk.nytmodultest.studieportal.domain.model.*
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.method.ScrollingMovementMethod
import android.widget.LinearLayout
import com.google.gson.Gson
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestMultiChoiceCommand
import java.net.URL
import kotlin.Exception

class Listening : AppCompatActivity(){

    private inner class GetListeningExercise: AsyncTask<String, Int, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            val databaseGet: String
            //try{
            //use "10.0.2.2" instead of localhost (ottherwise you get an exception)
            databaseGet =  URL("http://10.0.2.2:8000/api/findQuestionsByExerciseId/1").readText()
            //} //catch (e: Exception) {
            //return e.toString()
            //return "Database error"
            //}

            //val article = Gson().fromJson(databaseGet, Exercise::class.java)
            //return article.toString()
            return databaseGet
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            //super.onPostExecute(result)
            val myresult = Gson().fromJson(result, Exercise::class.java)


            //act_textcontent.text = "${myresult}"
            val recyclerView = findViewById(R.id.activity_listening_recyclerView) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this@Listening, LinearLayout.VERTICAL, false)

            val questions = ArrayList<Questions>()
            for (q in myresult.questions){
                questions.add(q)
            }
            val adapter = ListeningAdapter(questions)
            recyclerView.adapter = adapter


            /*
            val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


            val users = ArrayList<User>()
            users.add(User("Belal Khan", "Ranchi, India"))
            users.add(User("Belal Khan", "Ranchi, India"))
            users.add(User("Belal Khan", "Ranchi, India"))
            users.add(User("Belal Khan", "Ranchi, India"))
            users.add(User("Belal Khan", "Ranchi, India"))

            val adapter = CustomAdapter(users)
            recyclerView.adapter = adapter
             */


        }



    }

    //TextView textView = (TextView) findViewById(R.id.text_view);
    //textView.setMovementMethod(new ScrollingMovementMethod());

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)


        //act_textcontent.setMovementMethod(ScrollingMovementMethod());
        GetListeningExercise().execute()

    }




}