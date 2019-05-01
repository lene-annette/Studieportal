package dk.nytmodultest.studieportal.data.db

import dk.nytmodultest.studieportal.domain.model.Student

class DbDataMapper{
    fun convertToDomain(student: StudentInfo) = with(student){
        Student(id, firstName, lastName, userName, email, phoneNr, citizenship, last5yCountry, birthCountry, motherTongue)
    }

    fun convertFromDomain(student: Student) = with(student){
        StudentInfo(id, firstName,lastName,userName,email,phoneNr,citizenship,last5yCountry,birthCountry,motherTongue)
    }
}
