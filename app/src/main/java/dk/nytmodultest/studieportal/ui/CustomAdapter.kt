package dk.nytmodultest.studieportal.ui

import android.support.v7.widget.RecyclerView
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.model.Questions

class CustomAdapter(val userList: ArrayList<Questions>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            val user: Questions = userList[p1]

            p0?.textViewName?.text = user.question
            p0?.textViewAddress1.text = user.possibleAnswers[0].answerText
            p0?.textViewAddress2.text = user.possibleAnswers[1].answerText
            p0?.textViewAddress3.text = user.possibleAnswers[2].answerText
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        val textViewAddress1 = itemView.findViewById(R.id.textViewAddress1) as TextView
        val textViewAddress2 = itemView.findViewById(R.id.textViewAddress2) as TextView
        val textViewAddress3 = itemView.findViewById(R.id.textViewAddress3) as TextView

    }


}