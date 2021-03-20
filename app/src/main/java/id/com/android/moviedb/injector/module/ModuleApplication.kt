package id.com.android.moviedb.injector.module

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import id.com.android.moviedb.controller.ControllerEndpoint
import id.com.android.moviedb.controller.ControllerRest
import id.com.android.moviedb.injector.scope.PerApplication

@Module
class ModuleApplication(private val application: Application) {

    @Provides
    @PerApplication
    internal fun provideAppContext(): Application {
        return application
    }

    @Provides
    @PerApplication
    internal fun provideAppResources(): Resources {
        return application.resources
    }

    @Provides
    @PerApplication
    internal fun provideAppRest(): ControllerEndpoint {
        return ControllerRest().newInstance(application)
    }


}