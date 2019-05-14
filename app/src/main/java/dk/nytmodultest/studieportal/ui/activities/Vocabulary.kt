package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dk.nytmodultest.studieportal.R

class Vocabulary : AppCompatActivity() {

    lateinit var vocabQuestionFromDb: ArrayList<String>
    lateinit var vocabAnswersToDb: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
    }



}
