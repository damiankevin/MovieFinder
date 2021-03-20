package id.com.android.moviedb.controller

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import id.com.android.moviedb.injector.component.ComponentApplication
import id.com.android.moviedb.injector.component.DaggerComponentApplication
import id.com.android.moviedb.injector.module.ModuleApplication
import id.com.android.moviedb.injector.module.ModulePersistance

class ControllerApplication : MultiDexApplication() {

    companion object {
        lateinit var componentApplication : ComponentApplication
    }

    override fun onCreate() {
        super.onCreate()
        componentApplication = DaggerComponentApplication.builder()
            .moduleApplication(ModuleApplication(this))
            .modulePersistance(ModulePersistance())
            .build()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    fun getComponentApplication(): ComponentApplication? {
        return componentApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}