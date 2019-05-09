package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.data.server.ExerciseServer
import dk.nytmodultest.studieportal.domain.model.Exercise

class ExerciseProvider(private val source: ExerciseServer = ExerciseServer()) {

    fun requestUnfinishedExercises(examType: String, studentId: Long, questionType: String, noOfExercises: Int):
            Exercise = source.getUnfinishedExercises(examType,studentId,questionType,noOfExercises)
}