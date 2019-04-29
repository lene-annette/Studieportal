package dk.nytmodultest.studieportal.ui.activities

import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import dk.nytmodultest.studieportal.R

class Listening : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)
    }

    /*
    doAsync(){
            val result = RequestStudentCommand(1).execute()
            uiThread{
                longToast("StudentByIdRequest performed")
                profileInfo.text = "Hello ${result.firstName}!"
            }
        }
    */

}