package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestMultiChoiceCommand

class Listening : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)

        doAsync(){
            val result = RequestMultiChoiceCommand().execute()
            uiThread{
                act_textcontent.text = "${result}"
            }
        }
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