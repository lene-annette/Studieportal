package dk.nytmodultest.studieportal.ui.activities

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.Exercise
import dk.nytmodultest.studieportal.domain.model.Question




class ListeningAdapter(val questionList: ArrayList<Question>, private val context: Context): RecyclerView.Adapter<ListeningAdapter.ViewHolder>(){

    var answers = ArrayList<String>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.recycleview_listening, p0, false)

        for(i in 0 until questionList.size){
            answers.add("")
        }

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val question: Question = questionList[p1]
        p0.textViewQuestions.text = question.question
        p0.textViewOption1.text = question.possibleAnswers[0].answerText
        p0.textViewOption2.text = question.possibleAnswers[1].answerText
        p0.textViewOption3.text = question.possibleAnswers[2].answerText

        p0.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{radioGroup, i ->
            val questionIndex = p0.adapterPosition.toString()
                //respectively: 0, 1, 2 ect.
            val possibilityIndex = radioGroup.checkedRadioButtonId
            val possibilityName = context.getResources().getResourceEntryName(possibilityIndex)
                //respectively: radioButton1, radioButton2 and radioButton3

            //answers.add(questionIndex.toInt(),possibilityName)

            answers[questionIndex.toInt()] = possibilityName




            //answers.add(questionIndex)
            //answers.add(possibilityName)
            //Toast.makeText(context,possibilityName,Toast.LENGTH_SHORT).show()
            //Toast.makeText(context,"mineSvar: " + answers.size,Toast.LENGTH_SHORT).show()

        })

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val radioGroup = itemView.findViewById(R.id.radioGroup) as RadioGroup
        val textViewQuestions = itemView.findViewById(R.id.textViewQuestion) as TextView
        val textViewOption1 = itemView.findViewById(R.id.radioButton1) as TextView
        val textViewOption2 = itemView.findViewById(R.id.radioButton2) as TextView
        val textViewOption3 = itemView.findViewById(R.id.radioButton3) as TextView

    }



}