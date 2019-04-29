package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val ID_TOKEN = "idToken"
        const val DEFAULT_TOKEN = "none"
    }

    private var token: String
        by DelegatesExt.preference(this,ID_TOKEN,DEFAULT_TOKEN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(token.equals(DEFAULT_TOKEN)){
            setContentView(R.layout.activity_main)
        }else{
            startActivity(Intent(this, ProfileActivity::class.java))
        }



        loginBtn.setOnClickListener {
            token = "I'm a token!"
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
