package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import com.google.gson.Gson
import dk.nytmodultest.studieportal.domain.model.Exercise
import java.net.URL

class ExerciseRequest(val gson: Gson = Gson()){
    companion object{
        private const val API = RequestInfo.API
        private const val SRC = "unfinished-exercises-by-type/"
        private const val COMPLETE_URL = API + SRC
    }
    fun getUnfinishedExercises(examType: String,studentId: Long,questionType: String,noOfExercises: Int): Exercise {
        val url = "$COMPLETE_URL$examType/$studentId/$questionType/$noOfExercises"
        var exerciseJsonStr = URL(url).readText()

        //temporary solution for returned list. TODO : Needs to be handled differently!
        exerciseJsonStr = exerciseJsonStr.drop(1)
        exerciseJsonStr = exerciseJsonStr.dropLast(1)

        val result = gson.fromJson(exerciseJsonStr, Exercise::class.java)

        return result
    }
}