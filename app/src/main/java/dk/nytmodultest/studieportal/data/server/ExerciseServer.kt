package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import dk.nytmodultest.studieportal.domain.model.Exercise
import dk.nytmodultest.studieportal.domain.model.Question

class ExerciseServer {

    fun getUnfinishedExercises(examType: String,studentId: Long,questionType: String,noOfExercises: Int): Exercise{
        val result =  ExerciseRequest().getUnfinishedExercises(examType,studentId,questionType,noOfExercises)
        return result

    }

    fun submitAnswers(studentId: Long, questions: List<Question>, answers:List<String>){
        ExerciseRequest().submitAnswers(studentId,questions,answers)
    }


}