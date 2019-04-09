package dk.nytmodultest.studieportal.data.server

import dk.nytmodultest.studieportal.domain.model.Student

class ServerDataMapper {
    fun convertStudentToDomain(id: Long, student: StudentResult) = with(student) {
        Student(id, firstName,lastName,userName,email,phoneNr,citizenship,last5yCountry,birthCountry,motherTongue)
    }
}