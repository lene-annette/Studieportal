package dk.nytmodultest.studieportal.data

import com.google.gson.Gson
import java.net.URL

class StudentRequest(private val id: Long) {
    companion object {
        private const val API = "https://nytmodultest.dk/modultest-api/api/"
        private const val SRC = "students/"
        private const val COMPLETE_URL = API + SRC
    }

    fun execute():StudentResult {
        val studentJsonStr = URL(COMPLETE_URL + id).readText()
        return Gson().fromJson(studentJsonStr,StudentResult::class.java)

    }
}