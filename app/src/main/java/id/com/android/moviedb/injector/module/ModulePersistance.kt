package id.com.android.moviedb.injector.module

import android.app.Application
import dagger.Module
import dagger.Provides
import id.com.android.moviedb.controller.ControllerPreference
import id.com.android.moviedb.injector.scope.PerApplication
import id.com.android.moviedb.repository.RepositorySettings

@Module
class ModulePersistance {

    @Provides
    @PerApplication
    internal fun provideControllerPreference(application: Application): ControllerPreference {
        return ControllerPreference(application)
    }


    @Provides
    @PerApplication
    internal fun providePreferenceSettings(controllerPreference: ControllerPreference): RepositorySettings {
        return RepositorySettings(controllerPreference)
    }



}
