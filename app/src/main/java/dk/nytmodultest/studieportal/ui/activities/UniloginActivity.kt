package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import dk.nytmodultest.studieportal.R

class UniloginActivity : AppCompatActivity() {
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
            d("Lene", "Applink-data: " + appLinkData)
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
