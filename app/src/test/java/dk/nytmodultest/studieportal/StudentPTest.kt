package dk.nytmodultest.studieportal

import dk.nytmodultest.studieportal.domain.datasource.StudentDataSource
import dk.nytmodultest.studieportal.domain.datasource.StudentProvider
import dk.nytmodultest.studieportal.domain.model.Student
import org.junit.Test
import org.junit.Assert.assertNotNull
import org.mockito.Mockito.*

class StudentPTest {
    @Test
    fun `data source returns a value`(){
      val ds = mock(StudentDataSource::class.java)
      `when`(ds.requestStudentById(0))
          .then{
              Student(0,"john","doe","johndoe", "john@doe.com","",
                  "earth","","","esperanto")
          }
      val provider = StudentProvider(listOf(ds))
      assertNotNull(provider.requestById(0))
    }

    @Test
    fun `empty database returns server value`(){
        val db = mock(StudentDataSource::class.java)
        val server = mock(StudentDataSource::class.java)
        `when`(server.requestStudentById(any(Long::class.java)))
            .then{
                Student(0,"john","doe","johndoe", "john@doe.com","",
                    "earth","","","esperanto")
            }
        val provider = StudentProvider(listOf(db,server))
        assertNotNull(provider.requestById(0))
    }
}