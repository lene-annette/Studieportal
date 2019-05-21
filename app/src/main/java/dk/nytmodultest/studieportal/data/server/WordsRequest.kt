package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import com.google.gson.Gson
import dk.nytmodultest.studieportal.Config
import dk.nytmodultest.studieportal.domain.model.Word
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class WordsRequest(val gson: Gson = Gson()) {
    companion object{
        private const val API = Config.API
        private const val SRC = "get-weighted-words/"
        private const val COMPLETE_URL = API + SRC
    }

    fun getWords(studentId: Long, noOfWords: Int): List<Word> {
        val wordListJson = URL("$COMPLETE_URL$studentId/$noOfWords").readText()
        return gson.fromJson(wordListJson, Array<Word>::class.java).asList()
    }

    fun submitKnownWord(studentId: Long, wordId: Int){
        val endpoint = API + "studentwords"

        val json = JSONObject()
        json.put("studentId",studentId)
        json.put("wordId",wordId)

        d("Lene","Response: $json")

        val response = URL(endpoint)
            .openConnection()
            .let{
                it as HttpURLConnection
            }.apply{
                setRequestProperty("Content-type","application/json; charset=UTF-8")
                requestMethod = "POST"

                doOutput = true
                val outputWriter = OutputStreamWriter(outputStream)
                outputWriter.write(json.toString())
                outputWriter.flush()
            }.let{
                if(it.responseCode == 200){
                    it.inputStream
                }else it.errorStream
            }.let{ streamToRead ->
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
    }
}