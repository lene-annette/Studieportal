package dk.nytmodultest.studieportal.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.Exercise

class Resultpage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultpage)

        val resultString = intent.getStringExtra("result")
        val listOfClicks = intent.getStringArrayListExtra("listOfClicks")

        val gson = Gson()
        val result: Exercise = gson.fromJson(resultString, Exercise::class.java)

        val correctAnswers: ArrayList<String> = ArrayList()
        for (item in result.questions){
            correctAnswers.add(item.correctAnswer)
        }

        val textToDisplay = displayResults(correctAnswers, listOfClicks)//correctAnswers.toString() + "\n\n" + listOfClicks.toString()//result + "\n\n" + listOfClicks.toString()
        //findViewById<TextView>(R.id.displayText).text = textToDisplay.toString()


        val recyclerView = findViewById<RecyclerView>(R.id.activity_resultpage_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@Resultpage, LinearLayout.VERTICAL, false)
        val adapter = ResultPageAdapter(textToDisplay, this@Resultpage)
        recyclerView.adapter = adapter



    }

    /*
                activity_listening_instruction.text = result.studentInstructions
                val recyclerView = findViewById<RecyclerView>(R.id.activity_listening_recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this@Listening,LinearLayout.VERTICAL, false)

                val questions = ArrayList<Question>()
                for(q in result.questions){
                    questions.add(q)
                }



                val adapter = ListeningAdapter(questions, this@Listening)
                recyclerView.adapter = adapter

    */





    fun displayResults(correctAnswers: ArrayList<String>, listOfClicks: ArrayList<String>):
            ArrayList<ArrayList<String>>{
        val output = ArrayList<ArrayList<String>>()
        for (item in correctAnswers) {
            val eachResult: ArrayList<String> = ArrayList()
            val index = correctAnswers.indexOf(item)
            if (item == listOfClicks[index]) {
                eachResult.add(item)
                eachResult.add(listOfClicks[index])
                eachResult.add(R.drawable.checkmark.toString())
            }else{
                eachResult.add(item)
                eachResult.add(listOfClicks[index])
                eachResult.add(R.drawable.wrong.toString())
            }
            output.add(eachResult)
        }
        return output
    }



}