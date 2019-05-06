package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.UiThread
import android.util.Log.d
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.LoginCommand
import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.extensions.Decoder
import dk.nytmodultest.studieportal.extensions.DelegatesExt
import dk.nytmodultest.studieportal.extensions.UniloginExtension
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    companion object{
        const val ID_TOKEN = "idToken"
        const val DEFAULT_TOKEN = "none"
        const val USER_ID = "userId"
        const val DEFAULT_ID: Long = 0
        var UniUrl = "empty"
    }

    private var idToken: String
        by DelegatesExt.preference(this,ID_TOKEN,DEFAULT_TOKEN)

    private var userId: Long
        by DelegatesExt.preference(this, USER_ID, DEFAULT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(idToken.equals(DEFAULT_TOKEN)){
            setContentView(R.layout.activity_main)
        }else{
            startActivity(Intent(this, ProfileActivity::class.java))
        }



        loginBtn.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPsw.text.toString()

            doAsync() {
                val result: IdToken = LoginCommand(email, password).execute()
                uiThread {
                    if (result.success) {
                        idToken = result.token
                        d("Lene", "Token: " + idToken)
                        userId = Decoder(idToken).decode()
                        finish()
                        startActivity(Intent(it, ProfileActivity::class.java))
                    } else {
                        longToast("Wrong username or password")
                    }
                }
            }
        }

        uniBtn.setOnClickListener {
            val res = UniloginExtension().toUnilogin()
            UniUrl = res
            startActivity(Intent(this, UniloginRedirectActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
