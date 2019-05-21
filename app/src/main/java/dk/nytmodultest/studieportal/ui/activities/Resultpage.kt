package dk.nytmodultest.studieportal.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        findViewById<TextView>(R.id.displayText).text = textToDisplay.toString()




    }





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