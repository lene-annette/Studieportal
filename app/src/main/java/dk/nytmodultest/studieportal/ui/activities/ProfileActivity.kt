package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        doAsync(){
            val result = RequestStudentCommand(1).execute()
            uiThread{
                longToast("StudentByIdRequest performed")
                profileInfo.text = "Hello ${result.firstName}!"
            }
        }
    }
}
