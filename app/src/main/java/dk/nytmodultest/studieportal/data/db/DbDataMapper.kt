package dk.nytmodultest.studieportal.data.db

import dk.nytmodultest.studieportal.domain.model.Student

class DbDataMapper{
    fun convertToDomain(student: StudentInfo) = with(student){
        Student(firstName, lastName, userName, email, phoneNr, citizenship, last5yCountry, birthCountry, motherTongue)
    }

    fun convertFromDomain(student: Student) = with(student){
        StudentInfo(firstName,lastName,userName,email,phoneNr,citizenship,last5yCountry,birthCountry,motherTongue)
    }
}
