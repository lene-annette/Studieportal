package dk.nytmodultest.studieportal.data.server

import com.google.gson.Gson
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class UniloginRequest(private val user:String, private val timestamp: String, val auth:String, val gson: Gson = Gson()) {
    companion object {
        // private const val API = "https://nytmodultest.dk/modultest-api/api/"
        private const val API = RequestInfo.API
        private const val SRC = "student-unilogin"
        private const val COMPLETE_URL = API + SRC
    }
    fun execute(): IdTokenResult {
        val json = JSONObject()
        json.put("user",user)
        json.put("timestamp",timestamp)
        json.put("auth",auth)
        val body = json.toString()

        val response = URL(COMPLETE_URL)
            .openConnection()
            .let{
                it as HttpURLConnection
            }.apply {
                setRequestProperty("Content-type","application/json; charset=utf-8")
                requestMethod = "POST"

                doOutput = true
                val outputWriter = OutputStreamWriter(outputStream)
                outputWriter.write(body)
                outputWriter.flush()
            }.let{
                if (it.responseCode == 200){
                    it.inputStream
                } else it.errorStream
            }.let{streamToRead ->
                BufferedReader(InputStreamReader(streamToRead)).use{
                    val response = StringBuffer()
                    var inputLine = it.readLine()
                    while(inputLine != null){
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    response.toString()
                }

            }
        return gson.fromJson(response, IdTokenResult::class.java)
    }
}