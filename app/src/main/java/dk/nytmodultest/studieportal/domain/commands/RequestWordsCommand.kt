package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.WordsProvider
import dk.nytmodultest.studieportal.domain.model.Word

class RequestWordsCommand(private val studentId: Long, private val noOfWords: Int,
                            private val wordsProvider: WordsProvider = WordsProvider()
): Command<List<Word>> {

    override fun execute(): List<Word> = wordsProvider.getWords(studentId,noOfWords)
}