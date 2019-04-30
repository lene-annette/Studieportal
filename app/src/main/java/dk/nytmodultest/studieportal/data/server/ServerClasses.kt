package dk.nytmodultest.studieportal.data.server

data class StudentResult(
    val firstName: String, val lastName: String,
    val userName: String, val email: String,
    val phoneNr: String, val citizenship: String,
    val last5yCountry: String, val birthCountry: String,
    val motherTongue: String)

data class IdTokenResult(
    val success: Boolean, val token: String = "none", val message:String
){
    constructor(
        success: Boolean? = null,
        token: String? = null,
        message: String? = null
    ) : this(
        success ?: false,
        token ?: "none",
        message ?: "something went wrong"
    )
}
