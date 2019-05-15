package dk.nytmodultest.studieportal.domain.model

import com.google.gson.annotations.SerializedName

data class Student(val id: Long, val firstName: String, val lastName: String, val userName: String, val email:String,
                   val phoneNr: String, val citizenship:String, val last5yCountry: String, val birthCountry: String,
                   val motherTongue: String)

data class IdToken(val success: Boolean, val token: String, val message: String)

data class Exercise (
    val id : Long,
    val subExamId : Int,
    val media : String,
    val headline : String,
    val mediaType : String,
    val examType : String,
    val focus : String,
    val examinatorInstructions : String,
    val studentInstructions : String,
    val time : String,
    val minutes : Int,
    val assessment : String,
    val passed : Int,
    val moduleNr : Double,
    val use : String,
    val access : String,
    val school : String,
    val createdAt : String,
    val updatedAt : String,
    val questions : List<Question>
)

data class Question (

    val id : Int,
    val exerciseId : Int,
    val question : String,
    val minPoints : Int,
    val maxPoints : Int,
    val guideLines : String,
    val media : String,
    val mediaType : String,
    val correctAnswer : String,
    val type : String,
    val createdAt : String,
    val updatedAt : String,
    val possibleAnswers : List<PossibleAnswer>
)

data class PossibleAnswer (

    val answerText : String
)


data class User (
    val name: String,
    val address: String
)

data class VocabWorkList (
    @SerializedName("wordList")val wordList : List<VocabWord>
)

data class VocabWord (

    @SerializedName("id") val id : Int,
    @SerializedName("word") val word : String,
    @SerializedName("meaning") val meaning : String,
    @SerializedName("synonym") val synonym : String,
    @SerializedName("english") val english : String,
    @SerializedName("pronunciation") val pronunciation : String,
    @SerializedName("image") val image : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("updatedAt") val updatedAt : String
)


data class postVocabWord (

    @SerializedName("wordId") val wordId : Int,
    @SerializedName("studentId") val studentId : Int
)