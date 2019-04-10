package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dk.nytmodultest.studieportal.R
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        getUsers()

        /*
        val url = "https://nytmodultest.dk/modultest-api/api/students/1"

        doAsync(){
            val result = Request(url).run()
            uiThread{
                longToast("Request performed")
                profileInfo.text = "Hello ${result.firstName}!"
                //globalResult = result
            }
        }
        */



        // find the botton to the listening page
        val listeningBtn = findViewById(R.id.listeningBtn)as Button
        val listeningIntent = Intent(this, Listening::class.java)
        listeningIntent.putExtra("Username", "john doe")
        listeningBtn.setOnClickListener{
            startActivity(listeningIntent)
        }

    }

    fun getUsers() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://nytmodultest.dk/modultest-api/api/students/1"//"https://api.github.com/search/users?q=eyehunt"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                //val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                var str_user: String = ""
                //for (i in 0 until jsonArray.length()) {
                //    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                str_user = str_user + "\n" + jsonObj.get("firstName")
                //}
                profileInfo!!.text = "Hello ${str_user}!"//"response : $str_user "
            },
            Response.ErrorListener { textView!!.text = "That didn't work!" })
        queue.add(stringReq)
    }
}
