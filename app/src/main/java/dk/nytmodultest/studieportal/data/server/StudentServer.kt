package dk.nytmodultest.studieportal.data.server

import android.util.Log.d
import dk.nytmodultest.studieportal.data.db.StudentDb
import dk.nytmodultest.studieportal.domain.datasource.StudentDataSource
import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.domain.model.Student

class StudentServer(
    private val dataMapper: ServerDataMapper = ServerDataMapper(),
    private val studentDb: StudentDb = StudentDb()): StudentDataSource {

    override fun requestStudentById(id: Long): Student? {
        val result = StudentByIdRequest(id).execute()
        val converted = dataMapper.convertToDomain(result)
        studentDb.saveStudent(converted)
        return studentDb.requestStudentById(id)
    }

    override fun login(email: String, password: String): IdToken {
        val result = LoginRequest(email, password).execute()
        //This works, but produce an compiler warning. Code able to run anyway
        if(result.token == null) result.token = "none"
        return dataMapper.convertToken(result)
    }

    fun unilogin(user: String, timestamp: String, auth: String): IdToken {
        val result = UniloginRequest(user, timestamp, auth).execute()
        if(result.token == null ) result.token = "none"
        return dataMapper.convertToken(result)
    }
}