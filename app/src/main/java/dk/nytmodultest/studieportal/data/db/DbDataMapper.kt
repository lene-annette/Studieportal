package dk.nytmodultest.studieportal.data.db

import dk.nytmodultest.studieportal.domain.model.Student

class DbDataMapper {
    fun convertStudentFromDomain(student: Student) = with(student) {
        StudentInfo(firstName, lastName, userName, email, phoneNr,citizenship,last5yCountry,birthCountry,motherTongue)
    }

    fun convertStudentToDomain(studentInfo: StudentInfo) = with(studentInfo){
        Student(_id, firstName,lastName,userName,email,phoneNr,citizenship,last5yCountry,birthCountry,motherTongue)
    }
}