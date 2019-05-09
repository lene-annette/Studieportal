package dk.nytmodultest.studieportal.ui.activities

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.Exercise
import dk.nytmodultest.studieportal.domain.model.Questions
import kotlinx.android.synthetic.main.list_layout.view.*




class ListeningAdapter(val questionList: ArrayList<Questions>, private val context: Context): RecyclerView.Adapter<ListeningAdapter.ViewHolder>(){

    var mineSvar = ArrayList<String>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.recycleview_listening, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        //var mineSvar = ArrayList<String>()


        val question: Questions = questionList[p1]
        p0.textViewQuestions.text = question.question
        p0.textViewOption1.text = question.possibleAnswers[0].answerText
        p0.textViewOption2.text = question.possibleAnswers[1].answerText
        p0.textViewOption3.text = question.possibleAnswers[2].answerText

        p0.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{radioGroup, i ->
            val spoergsmaalsindex = p0.adapterPosition.toString()
                //respectively: 0, 1, 2 ect.
            val mulighedsIndex = radioGroup.checkedRadioButtonId
            val mulighednavn = context.getResources().getResourceEntryName(mulighedsIndex)
                //respectively: radioButton1, radioButton2 and radioButton3
            mineSvar.add(spoergsmaalsindex)
            mineSvar.add(mulighednavn)
            Toast.makeText(context,mulighednavn,Toast.LENGTH_SHORT).show()
            Toast.makeText(context,"mineSvar: "+mineSvar.size,Toast.LENGTH_SHORT).show()

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