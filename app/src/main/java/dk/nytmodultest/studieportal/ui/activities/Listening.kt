package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import dk.nytmodultest.studieportal.domain.model.*
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.Gson
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestExerciseCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class Listening : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)

        doAsync{
            val result = RequestExerciseCommand("listening",ProfileActivity.ONLINE_USER,"multiple choice",1).execute()
            uiThread {
                activity_listening_instruction.text = result.studentInstructions
                val recyclerView = findViewById<RecyclerView>(R.id.activity_listening_recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this@Listening,LinearLayout.VERTICAL, false)

                val questions = ArrayList<Question>()
                for(q in result.questions){
                    questions.add(q)
                }
                val adapter = ListeningAdapter(questions, this@Listening)
                recyclerView.adapter = adapter

                activity_listening_confirmBtn.setOnClickListener{
                    val listOfClicks = (recyclerView.adapter as ListeningAdapter).mineSvar.toString()
                    Toast.makeText(this@Listening,listOfClicks,Toast.LENGTH_SHORT).show()
                }
            }
        }

        //act_textcontent.setMovementMethod(ScrollingMovementMethod());
        //GetListeningExercise().execute()

    }





//    private inner class GetListeningExercise: AsyncTask<String, Int, String>(){
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//        }
//
//        override fun doInBackground(vararg params: String?): String {
//            val databaseGet: String
//            databaseGet =  URL("http://10.25.0.133:8000/api/findQuestionsByExerciseId/1").readText()
//            return databaseGet
//        }
//
//        override fun onProgressUpdate(vararg values: Int?) {
//            super.onProgressUpdate(*values)
//        }
//
//        override fun onPostExecute(result: String?) {
//            //super.onPostExecute(result)
//            val myresult = Gson().fromJson(result, Exercise::class.java)
//
//            activity_listening_instruction.text = myresult.studentInstructions
//
//            //act_textcontent.text = "${myresult}"
//            val recyclerView = findViewById(R.id.activity_listening_recyclerView) as RecyclerView
//            recyclerView.layoutManager = LinearLayoutManager(this@Listening, LinearLayout.VERTICAL, false)
//
//            val questions = ArrayList<Question>()
//            for (q in myresult.questions){
//                questions.add(q)
//            }
//            val adapter = ListeningAdapter(questions, this@Listening)
//            recyclerView.adapter = adapter
//
//            activity_listening_confirmBtn.setOnClickListener{
//                val minListeAfKlicks = (recyclerView.adapter as ListeningAdapter).mineSvar.toString()
//                Toast.makeText(this@Listening,minListeAfKlicks,Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//
//
//
//    fun message(str: String) {
//        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
//    }






}