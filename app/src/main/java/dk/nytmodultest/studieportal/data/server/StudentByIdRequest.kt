package dk.nytmodultest.studieportal.data.server

import com.google.gson.Gson
import dk.nytmodultest.studieportal.Host
import java.net.URL

class StudentByIdRequest(private val id: Long, val gson: Gson = Gson()) {
    companion object {
        private const val API = Host.API//RequestInfo.API
        private const val SRC = "students/"
        private const val COMPLETE_URL = API + SRC
    }

    fun execute(): StudentResult {
        val studentJsonStr = URL(COMPLETE_URL + id).readText()
        return gson.fromJson(studentJsonStr, StudentResult::class.java)
    }
}