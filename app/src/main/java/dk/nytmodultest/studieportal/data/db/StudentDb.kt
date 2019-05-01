package dk.nytmodultest.studieportal.data.db

import dk.nytmodultest.studieportal.domain.datasource.StudentDataSource
import dk.nytmodultest.studieportal.domain.model.IdToken
import dk.nytmodultest.studieportal.domain.model.Student
import dk.nytmodultest.studieportal.extensions.clear
import dk.nytmodultest.studieportal.extensions.parseOpt
import dk.nytmodultest.studieportal.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class StudentDb(
    private val studentDbHelper: StudentDbHelper = StudentDbHelper.instance,
    private val dataMapper: DbDataMapper = DbDataMapper()): StudentDataSource{
    override fun login(email: String, password: String): IdToken {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestStudentById(id: Long) = studentDbHelper.use{
        val studentInfoRequest = "${StudentTable.STUDENTID} = ?"
        val studentInfo = select(StudentTable.NAME)
            .whereSimple(studentInfoRequest,id.toString())
            .parseOpt {StudentInfo(HashMap(it))}

        if(studentInfo != null) dataMapper.convertToDomain(studentInfo) else null
    }

    fun saveStudent(student: Student) = studentDbHelper.use{
        clear(StudentTable.NAME)
        with(dataMapper.convertFromDomain(student)){
            insert(StudentTable.NAME, *map.toVarargArray())
        }

    }
}