package dk.nytmodultest.studieportal.domain.commands

public interface Command<out T>{
    fun execute(): T
}