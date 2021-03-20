package id.com.android.moviedb.injector.component

import android.app.Application
import dagger.Component
import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.controller.ControllerPreference
import id.com.android.moviedb.injector.module.ModuleApplication
import id.com.android.moviedb.injector.module.ModulePersistance
import id.com.android.moviedb.injector.scope.PerApplication
import id.com.android.moviedb.repository.RepositorySettings

@PerApplication
@Component(modules = [(ModuleApplication::class), (ModulePersistance::class)])
interface ComponentApplication {
    fun inject(application: Application)
    fun serviceApi()                    : ControllerEndpoint
    fun servicePresistance()            : ControllerPreference
    fun repositorySettings()            : RepositorySettings
}