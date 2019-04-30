package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import com.google.gson.Gson
import java.net.URL

class StudentByIdRequest(private val id: Long, val gson: Gson = Gson()) {
    companion object {
        // private const val API = "https://nytmodultest.dk/modultest-api/api/"
        private const val API = "http://10.25.0.133:8000/api/"
        private const val SRC = "students/"
        private const val COMPLETE_URL = API + SRC
    }

    fun execute(): StudentResult {
        val studentJsonStr = URL(COMPLETE_URL + id).readText()
        return gson.fromJson(studentJsonStr, StudentResult::class.java)
    }
}