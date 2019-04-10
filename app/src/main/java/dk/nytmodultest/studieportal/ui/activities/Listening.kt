package dk.nytmodultest.studieportal.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dk.nytmodultest.studieportal.R
import kotlinx.android.synthetic.main.activity_listening.*

class Listening : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)

        val profileName=intent.getStringExtra("Username")
        listeningTextView.text = "Hello ${profileName}!"

    }

}