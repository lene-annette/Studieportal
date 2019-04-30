package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.StudentProvider
import dk.nytmodultest.studieportal.domain.datasource.TokenProvider
import dk.nytmodultest.studieportal.domain.model.IdToken


class LoginCommand( private val email: String, private val password: String, private val tokenProvider: TokenProvider = TokenProvider()):
    Command<IdToken>{

    override fun execute(): IdToken = tokenProvider.login(email,password)
}