package dk.nytmodultest.studieportal.data.server

import com.google.gson.Gson
import dk.nytmodultest.studieportal.Config
import dk.nytmodultest.studieportal.domain.model.Word
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
}