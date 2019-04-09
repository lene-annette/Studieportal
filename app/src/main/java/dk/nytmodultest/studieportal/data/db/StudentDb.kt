package dk.nytmodultest.studieportal.data.db

import dk.nytmodultest.studieportal.domain.datasource.StudentDatasource
import dk.nytmodultest.studieportal.domain.model.Student
import dk.nytmodultest.studieportal.extensions.byId
import dk.nytmodultest.studieportal.extensions.clear
import dk.nytmodultest.studieportal.extensions.parseOpt
import dk.nytmodultest.studieportal.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class StudentDb(private val studentDbHelper: StudentDbHelper = StudentDbHelper.instance,
                private val dataMapper: DbDataMapper = DbDataMapper()): StudentDatasource {
    override fun requestStudentById(id: Long): Student? = studentDbHelper.use {
        val student = select(StudentTable.NAME).byId(id)
            .parseOpt {StudentInfo(HashMap(it))}
        if(student != null) dataMapper.convertStudentToDomain(student) else null
    }

    fun saveStudent(student: Student) = studentDbHelper.use {
        clear(StudentTable.NAME)

        with(dataMapper.convertStudentFromDomain(student)) {
        insert(StudentTable.NAME, *map.toVarargArray())
        }
    }
}