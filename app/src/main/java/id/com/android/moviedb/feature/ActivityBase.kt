package id.com.android.moviedb.feature

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import id.com.android.moviedb.controller.ControllerApplication
import id.com.android.moviedb.injector.component.ComponentActivity
import id.com.android.moviedb.injector.component.DaggerComponentActivity
import id.com.android.moviedb.injector.module.ModuleActivity

@SuppressLint("Registered")
open class ActivityBase : AppCompatActivity() {


    var componentActivity : ComponentActivity? = null
        get() {
            checkNotNull(field) { return DaggerComponentActivity.builder().componentApplication(ControllerApplication.componentApplication).moduleActivity(ModuleActivity(this)).build() }
            return field
        }
    override fun onDestroy() {
        super.onDestroy()

    }


}