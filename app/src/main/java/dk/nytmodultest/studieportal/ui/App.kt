package dk.nytmodultest.studieportal.ui

import android.app.Application
import dk.nytmodultest.studieportal.extensions.DelegatesExt

class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate(){
        super.onCreate()
        instance = this
    }
}