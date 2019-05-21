package dk.nytmodultest.studieportal.data.server

import dk.nytmodultest.studieportal.domain.model.Word

class WordsServer {
    fun getWords(studentId: Long, noOfWords: Int): List<Word>{
        return WordsRequest().getWords(studentId,noOfWords)
    }

    fun submitKnownWord(studentId: Long, wordId: Int){
        WordsRequest().submitKnownWord(studentId,wordId)
    }
}