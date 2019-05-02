package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.Exercise
import dk.nytmodultest.studieportal.domain.model.Questions

class ListeningAdapter(val questionList: ArrayList<Questions>): RecyclerView.Adapter<ListeningAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.recycleview_listening, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val question: Questions = questionList[p1]
        p0.textViewQuestions.text = question.question
        p0.textViewOption1.text = question.possibleAnswers[0].answerText
        p0.textViewOption2.text = question.possibleAnswers[1].answerText
        p0.textViewOption3.text = question.possibleAnswers[2].answerText
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        //val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
        val textViewQuestions = itemView.findViewById(R.id.textViewQuestion) as TextView
        val textViewOption1 = itemView.findViewById(R.id.radioButton1) as TextView
        val textViewOption2 = itemView.findViewById(R.id.radioButton2) as TextView
        val textViewOption3 = itemView.findViewById(R.id.radioButton3) as TextView

    }



}