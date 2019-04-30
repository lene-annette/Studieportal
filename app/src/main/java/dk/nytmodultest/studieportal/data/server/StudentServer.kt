package dk.nytmodultest.studieportal.data.server

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
        return dataMapper.convertToken(result)
    }
}