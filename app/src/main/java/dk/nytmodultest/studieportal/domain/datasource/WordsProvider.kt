package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.data.server.WordsServer
import dk.nytmodultest.studieportal.domain.model.Word

class WordsProvider(private val source: WordsServer = WordsServer()) {
    fun getWords(studentId:Long, noOfWords: Int): List<Word> = source.getWords(studentId,noOfWords)
}