package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import dk.nytmodultest.studieportal.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class ProfileActivity : AppCompatActivity() {

    private val userId: Long
            by DelegatesExt.preference(this, MainActivity.USER_ID, MainActivity.DEFAULT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        doAsync(){
            val result = RequestStudentCommand(userId).execute()
            uiThread{
                longToast("StudentByIdRequest performed")
                profileInfo.text = "Hello ${result.firstName}!"
            }
        }
    }
}
