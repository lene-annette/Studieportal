package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        doAsync{
            val result = RequestStudentCommand(1).execute()
            uiThread {
                d("Lene",result.toString())
            }
        }
    }
}
