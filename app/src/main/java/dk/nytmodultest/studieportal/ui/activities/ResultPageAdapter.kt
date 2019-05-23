package dk.nytmodultest.studieportal.ui.activities



import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import dk.nytmodultest.studieportal.R

class ResultPageAdapter(val textToDisplay:  ArrayList<ArrayList<String>>, private val context: Context): RecyclerView.Adapter<ResultPageAdapter.ViewHolder>(){

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val eachItem = textToDisplay[p1]
        p0.textViewQuestion.text = eachItem[0]
        p0.textViewCorrectanswer.text = eachItem[1]
        p0.textViewYourAnswer.text = eachItem[2]
        p0.imageViewCheckmark.setImageResource(eachItem[3].toInt())
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.recycleview_resultpage, p0, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return textToDisplay.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewQuestion = itemView.findViewById(R.id.textViewQuestion) as TextView
        val textViewCorrectanswer = itemView.findViewById(R.id.textViewCorrectanswer) as TextView
        val textViewYourAnswer = itemView.findViewById(R.id.textViewYouranswer) as TextView
        val imageViewCheckmark = itemView.findViewById(R.id.imageViewCheckmark) as ImageView
    }

}
