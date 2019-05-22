package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.data.db.StudentDb
import dk.nytmodultest.studieportal.data.server.StudentServer
import dk.nytmodultest.studieportal.domain.model.Student
import dk.nytmodultest.studieportal.extensions.firstResult

class StudentProvider(private val sources: List<StudentDataSource> = StudentProvider.SOURCES){
    companion object {
        val SOURCES by lazy{ listOf(StudentDb(), StudentServer())}
    }

    fun requestById(id: Long): Student = sources.firstResult{requestSource(it,id) }

    fun requestSource(source: StudentDataSource, id: Long): Student? {
        val res = source.requestStudentById(id)
        return if(res != null) res else null
    }
}