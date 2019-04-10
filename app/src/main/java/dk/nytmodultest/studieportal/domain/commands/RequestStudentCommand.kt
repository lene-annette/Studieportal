package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.StudentProvider
import dk.nytmodultest.studieportal.domain.model.Student

class RequestStudentCommand( private val id: Long, private val studentProvider: StudentProvider = StudentProvider()):
        Command<Student>{

    override fun execute(): Student = studentProvider.requestById(id)
}