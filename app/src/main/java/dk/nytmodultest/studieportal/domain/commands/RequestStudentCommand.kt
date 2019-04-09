package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.data.StudentRequest
import dk.nytmodultest.studieportal.domain.mappers.StudentDataMapper
import dk.nytmodultest.studieportal.domain.model.Student

class RequestStudentCommand(private val id: Long): Command<Student>{
    override fun execute(): Student {
        val studentRequest = StudentRequest(id)
        return StudentDataMapper().convertFromDataModel(studentRequest.execute())
    }
}