package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import com.google.gson.Gson
import dk.nytmodultest.studieportal.Host
import dk.nytmodultest.studieportal.domain.model.Exercise
import dk.nytmodultest.studieportal.domain.model.Question
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class ExerciseRequest(val gson: Gson = Gson()){
    companion object{
        private const val API = Host.API//RequestInfo.API
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

    fun submitAnswers(studentId: Long, questions: List<Question>, answers: List<String>){
        val endpoint = API + "multiple-questionresponses"
        //var answeredQuestions: MutableList<QuestionResponse> = mutableListOf()
        var answeredQuestions: MutableList<JSONObject> = mutableListOf()

        for((idx,question) in questions.withIndex()){
            val response = JSONObject()
            var auto = false
            var comments = ""
            if(question.type == "multiple choice"){
                auto = true
            }

            response.put("questionId",question.id)
            response.put("studentId",studentId)
            response.put("correctAnswer",question.correctAnswer)
            response.put("minPoints",question.minPoints)
            response.put("maxPoints",question.maxPoints)
            response.put("response",answers[idx])
            response.put("comments", comments)
            response.put("autoCorrect", auto)

//            var questionResponse = QuestionResponse(question.id,studentId,question.correctAnswer,question.minPoints,
//                question.maxPoints,answers[idx],comments,auto)

            answeredQuestions.add(response)
        }

        val json = JSONObject()
        json.put("answeredQuestions",answeredQuestions)
        var body = json.toString().replace("\\","")

        //This is specific to make this json object work with backend, (as with the above escape characters)
        //apparently body.toString() adds a lot of characters, like "" and \\, confusing the endpoint. 
        body = body.removeRange(21,22)
        body = body.removeRange(body.length-2,body.length-1)

        d("Lene","ExerciseRequest, body: $body")

        val response = URL(endpoint)
            .openConnection()
            .let{
                it as HttpURLConnection
            }.apply{
                setRequestProperty("Content-type","application/json; charset=UTF-8")
                requestMethod = "POST"

                doOutput = true
                val outputWriter = OutputStreamWriter(outputStream)
                outputWriter.write(body.toString())
                outputWriter.flush()
            }.let{
                if(it.responseCode == 200){
                    it.inputStream
                }else it.errorStream
            }.let{ streamToRead ->
                BufferedReader(InputStreamReader(streamToRead)).use{
                    val response = StringBuffer()
                    var inputLine = it.readLine()
                    while(inputLine != null){
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    response.toString()
                }
            }

        d("Lene","SubmitAnswers, ExerciseRequest: $response")

    }


}