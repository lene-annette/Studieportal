package dk.nytmodultest.studieportal.domain.model

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
    val transcript : String,
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

data class Word (
    val id: Int,
    val word: String,
    val meaning: String,
    val synonym: String,
    val english: String,
    val pronunciation: String,
    val image: String
)
