package dk.nytmodultest.studieportal.data.server

import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.domain.model.Student

class ServerDataMapper {
    fun convertToDomain(student: StudentResult) = with(student){
        Student(id, firstName, lastName, userName, email, phoneNr, citizenship, last5yCountry, birthCountry, motherTongue)
    }

    fun convertToken(idToken: IdTokenResult) = with(idToken){
        IdToken(success,token,message)
    }
}