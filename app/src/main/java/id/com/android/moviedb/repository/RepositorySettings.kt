package id.com.android.moviedb.repository

import id.com.android.moviedb.controller.ControllerPreference

class RepositorySettings(controllerPreferences: ControllerPreference?) {

    companion object {
        var controllerPreference : ControllerPreference?   = null
        val STATUS_LOGIN                           = "status_login"
    }

    init {
        controllerPreference = controllerPreferences
    }

}