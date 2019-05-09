package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import dk.nytmodultest.studieportal.domain.model.Exercise

class ExerciseServer {

    fun getUnfinishedExercises(examType: String,studentId: Long,questionType: String,noOfExercises: Int): Exercise{
        val result =  ExerciseRequest().getUnfinishedExercises(examType,studentId,questionType,noOfExercises)
        d("Lene","Result: ${result.studentInstructions}")
        return result

    }


}