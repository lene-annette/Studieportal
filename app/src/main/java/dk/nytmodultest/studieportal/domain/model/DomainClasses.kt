package dk.nytmodultest.studieportal.domain.model

data class Student(val id: Long, val firstName: String, val lastName: String, val userName: String, val email:String,
                   val phoneNr: String, val citizenship:String, val last5yCountry: String, val birthCountry: String,
                   val motherTongue: String)

data class IdToken(val success: Boolean, val token: String, val message: String){
//        constructor(
//        success: Boolean? = null,
//        token: String? = null,
//        message: String? = null
//    ) : this(
//        success ?: false,
//        token ?: "none",
//        message ?: "something went wrong"
//    )
}