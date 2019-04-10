package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.data.Request
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val url = "https://nytmodultest.dk/modultest-api/api/students/1"

        doAsync(){
            val result = Request(url).run()
            uiThread{
                longToast("Request performed")
                profileInfo.text = "Hello ${result.firstName}!"
                //globalResult = result
            }
        }


        //listeningBtn.setOnClickListener {
        //    startActivity(Intent(this, Listening::class.java))
        //}

        // find the botton to the listening page
        val listeningBtn = findViewById(R.id.listeningBtn)as Button
        listeningBtn.setOnClickListener{
            startActivity(Intent(this, Listening::class.java))
        }

    }
}
