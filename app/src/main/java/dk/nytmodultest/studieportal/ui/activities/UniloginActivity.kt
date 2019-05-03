package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.UniloginCommand
import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.extensions.Decoder
import dk.nytmodultest.studieportal.extensions.DelegatesExt
import dk.nytmodultest.studieportal.extensions.GetUniParams
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class UniloginActivity : AppCompatActivity() {
    private var idToken: String
            by DelegatesExt.preference(this, MainActivity.ID_TOKEN, MainActivity.DEFAULT_TOKEN)
    private var userId: Long
            by DelegatesExt.preference(this, MainActivity.USER_ID, MainActivity.DEFAULT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unilogin)

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent){
        val appLinkAction = intent.action
        val appLinkData: Uri? = intent.data
        if(Intent.ACTION_VIEW == appLinkAction){
            val gp = GetUniParams(appLinkData.toString())
            val user = gp.getParam("user")
            val timestamp = gp.getParam("timestamp")
            val auth = gp.getParam("auth")

            doAsync(){
                val result: IdToken = UniloginCommand(user, timestamp, auth).execute()
                uiThread {
                    if(result.success){
                        idToken = result.token
                        userId = Decoder(idToken).decode()
                        finish()
                        startActivity(Intent(it,ProfileActivity::class.java))
                    }else{
                        longToast("Something went wrong when logging in with Unilogin")
                    }
                }
            }
        }
    }
}

//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    ...
//    handleIntent(intent)
//}
//
//override fun onNewIntent(intent: Intent) {
//    super.onNewIntent(intent)
//    handleIntent(intent)
//}
//
//private fun handleIntent(intent: Intent) {
//    val appLinkAction = intent.action
//    val appLinkData: Uri? = intent.data
//    if (Intent.ACTION_VIEW == appLinkAction) {
//        appLinkData?.lastPathSegment?.also { recipeId ->
//            Uri.parse("content://com.recipe_app/recipe/")
//                .buildUpon()
//                .appendPath(recipeId)
//                .build().also { appData ->
//                    showRecipe(appData)
//                }
//        }
//    }
//}
