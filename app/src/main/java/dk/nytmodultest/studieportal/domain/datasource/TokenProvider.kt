package dk.nytmodultest.studieportal.domain.datasource

import dk.nytmodultest.studieportal.data.db.StudentDb
import dk.nytmodultest.studieportal.data.server.StudentServer
import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.domain.model.Student
import dk.nytmodultest.studieportal.extensions.firstResult

class TokenProvider(private val source: StudentDataSource = TokenProvider.SOURCE){
    companion object{
        val SOURCE = StudentServer()
    }

    fun login(email: String, password: String) : IdToken = source.login(email,password)

}

