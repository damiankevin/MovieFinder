package id.com.android.moviedb.injector.component

import dagger.Component
import id.com.android.moviedb.feature.ActivityBase
import id.com.android.moviedb.feature.userlayer.activity.ui.MainActivity
import id.com.android.moviedb.feature.userlayer.activity.ui.SplashActivity
import id.com.android.moviedb.injector.module.ModuleActivity
import id.com.android.moviedb.injector.scope.PerActivity

@PerActivity
@Component(dependencies = [(ComponentApplication::class)], modules = [(ModuleActivity::class)])
interface ComponentActivity {
    fun inject (activityBase                : ActivityBase)
    fun inject (activitySplashActivity      : SplashActivity)
    fun inject (mainActivity                : MainActivity)
}
