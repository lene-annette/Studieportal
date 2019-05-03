package dk.nytmodultest.studieportal.domain.commands

import dk.nytmodultest.studieportal.domain.datasource.TokenProvider
import dk.nytmodultest.studieportal.domain.model.IdToken

class UniloginCommand(private val user: String, private val timestamp: String, private val auth: String,
                      private val tokenProvider: TokenProvider = TokenProvider()):Command<IdToken>{
    override fun execute(): IdToken = tokenProvider.unilogin(user, timestamp, auth)

}