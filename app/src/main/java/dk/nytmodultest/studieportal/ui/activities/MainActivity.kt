package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.UiThread
import android.util.Log.d
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.LoginCommand
import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    companion object{
        const val ID_TOKEN = "idToken"
        const val DEFAULT_TOKEN = "none"
    }

    private var idToken: String
        by DelegatesExt.preference(this,ID_TOKEN,DEFAULT_TOKEN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(idToken.equals(DEFAULT_TOKEN)){
            setContentView(R.layout.activity_main)
        }else{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        
        loginBtn.setOnClickListener {
            doAsync() {
                val result: IdToken = LoginCommand("donald@trump.com","123").execute()
                uiThread{
                    idToken = result.token
                }
            }
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
