package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.WordsProvider

class SubmitKnownWordCommand(private val studentId: Long, private val wordId: Int,
                             private val wordsProvider: WordsProvider = WordsProvider()): Command<Any> {

    override fun execute(): Any {
        wordsProvider.submitKnownWord(studentId,wordId)
        return Any() //No clue what happens here TODO: research!
    }
}