package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.StudentProvider
import dk.nytmodultest.studieportal.domain.model.Student

class RequestStudentCommand(val id: Long, private val studentProvider: StudentProvider = StudentProvider()):
    Command<Student>{
    override fun execute() = studentProvider.requestStudentById(id)
}