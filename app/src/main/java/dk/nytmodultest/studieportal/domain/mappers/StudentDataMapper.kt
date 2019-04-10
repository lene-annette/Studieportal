package dk.nytmodultest.studieportal.domain.mappers

import dk.nytmodultest.studieportal.data.server.StudentResult
import dk.nytmodultest.studieportal.domain.model.Student

class StudentDataMapper {

    fun convertFromDataModel(student: StudentResult):Student =
            Student(student.firstName,student.lastName,student.userName,student.email,
                student.phoneNr,student.citizenship,student.last5yCountry,student.birthCountry,student.motherTongue)

}