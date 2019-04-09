package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.data.db.StudentDb
import dk.nytmodultest.studieportal.data.server.StudentServer
import dk.nytmodultest.studieportal.domain.model.Student
import dk.nytmodultest.studieportal.extensions.firstResult

class StudentProvider(private val sources: List<StudentDatasource> = StudentProvider.SOURCES){

    companion object {
        val SOURCES = listOf(StudentDb(), StudentServer())
    }

    fun requestStudentById(id: Long): Student = requestToSources {
        val res = it.requestStudentById(id)
        if(res != null) res else null
    }

    private fun <T : Any> requestToSources(f: (StudentDatasource) -> T?): T = sources.firstResult { f(it) }
}