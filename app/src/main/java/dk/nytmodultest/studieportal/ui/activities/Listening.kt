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
                    val listOfClicks = (recyclerView.adapter as ListeningAdapter).answers.toString()
                    d("Lene","Listening list of clicks (answers?): $listOfClicks")

                    //Toast.makeText(this@Listening,listOfClicks,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}