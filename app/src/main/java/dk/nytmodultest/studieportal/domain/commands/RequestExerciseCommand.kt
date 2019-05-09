package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.ExerciseProvider
import dk.nytmodultest.studieportal.domain.model.Exercise


class RequestExerciseCommand(private val examType: String, private val studentId: Long,
                             private val questionType: String, private val noOfExercises: Int,
                             private val exerciseProvider: ExerciseProvider = ExerciseProvider()): Command<Exercise> {


    override fun execute(): Exercise = exerciseProvider.requestUnfinishedExercises(examType,studentId,questionType,noOfExercises)


}