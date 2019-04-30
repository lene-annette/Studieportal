package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.domain.model.Student

interface StudentDataSource {
    fun requestStudentById(id: Long): Student ?
    fun login(email: String, password: String): IdToken
}