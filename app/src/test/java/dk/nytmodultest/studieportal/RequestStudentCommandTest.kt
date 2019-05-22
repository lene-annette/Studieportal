package dk.nytmodultest.studieportal

import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import dk.nytmodultest.studieportal.domain.datasource.StudentProvider
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestStudentCommandTest {
    @Test
    fun `provider is called when command is executed`(){
        val studentProvider = mock(StudentProvider::class.java)
        val command = RequestStudentCommand(2,studentProvider)

        runBlocking { command.execute() }

        verify(studentProvider).requestById(2)
    }
}