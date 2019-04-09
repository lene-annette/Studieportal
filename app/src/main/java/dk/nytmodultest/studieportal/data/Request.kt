package dk.nytmodultest.studieportal.data

import android.util.Log.d
import com.google.gson.Gson
import java.net.URL

class Request(private val url: String) {
    fun run():StudentResult {
        val studentJsonStr = URL(url).readText()
        d("Lene", studentJsonStr)
        return Gson().fromJson(studentJsonStr,StudentResult::class.java)

    }
}