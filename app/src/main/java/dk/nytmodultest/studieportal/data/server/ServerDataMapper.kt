package dk.nytmodultest.studieportal.data.server

import dk.nytmodultest.studieportal.domain.model.Student

class ServerDataMapper {
    fun convertToDomain(student: StudentResult) = with(student){
        Student(firstName, lastName, userName, email, phoneNr, citizenship, last5yCountry, birthCountry, motherTongue)
    }
}