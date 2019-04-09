package dk.nytmodultest.studieportal.data.db

import dk.nytmodultest.studieportal.domain.datasource.StudentDatasource
import dk.nytmodultest.studieportal.domain.model.Student
import org.jetbrains.anko.db.select

class StudentDb(private val studentDbHelper: StudentDbHelper = StudentDbHelper.instance,
                private val dataMapper: DbDataMapper = DbDataMapper()): StudentDatasource {
    override fun requestStudentById(id: Long): Student? = studentDbHelper.use {
        val student = select(StudentTable.NAME).byId(id)
            .parseOpt {StudentInfo(HashMap(it))}
        if(student != null) dataMapper.convertStudentToDomain(student) else null
    }
}