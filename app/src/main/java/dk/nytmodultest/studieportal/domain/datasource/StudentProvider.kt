package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.domain.model.Student

class StudentProvider(private val sources: List<StudentDatasource> = StudentProvider.SOURCES){

    companion object {
        val SOURCES = listOf(StudentDb(), StudentServer())
    }

    fun requestStudent(id: Long): Student = requestToSources {
        it.requestStudent(id)
    }

    private fun <T : Any> requestToSources(f: (StudentDatasource) -> T?): T = sources.firstResult { f(it) }
}