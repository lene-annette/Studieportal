package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.ExerciseProvider
import dk.nytmodultest.studieportal.domain.model.Question

class SubmitAnswersCommand(private val studentId: Long, private val questions: List<Question>,
                           private val answers: List<String>,
                           private val exerciseProvider: ExerciseProvider = ExerciseProvider()): Command<Any> {

    override fun execute(): Any {
        exerciseProvider.submitAnswers(studentId,questions,answers)
        return Any() //No clue what happens here TODO: research!
    }
}