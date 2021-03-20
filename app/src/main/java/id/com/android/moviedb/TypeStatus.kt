package id.com.android.moviedb

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.LongDef

object TypeStatus {

    const val INITIAL_STATUS            = -1L
    const val LOADING_PROGRESS          = -2L
    const val ERROR_CONNECTION          = -3L
    const val ERROR_DATA_EMPTY          = -5L
    const val SUCCESS_LOAD              = -6L
    const val ERROR_DATA_COMPLETE       = -17L


    @LongDef(ERROR_CONNECTION, ERROR_DATA_EMPTY,
        SUCCESS_LOAD, LOADING_PROGRESS,
        ERROR_DATA_COMPLETE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Status

    @SuppressLint("SwitchIntDef")
    fun getStatusMessage(context: Context?, @Status statusCode: Long): String {
        return when (statusCode) {
            ERROR_CONNECTION        -> context?.getString(R.string.DESC_STATUS_NETWORK)               ?: " "
            ERROR_DATA_EMPTY        -> context?.getString(R.string.DESC_STATUS_EMPTY)                 ?: " "
            LOADING_PROGRESS        -> context?.getString(R.string.SIGN_LOADING)                      ?: " "
            ERROR_DATA_COMPLETE     -> context?.getString(R.string.DESC_STATUS_DATA_COMPLETE)         ?: " "
            else                         -> ""
        }
    }

    @SuppressLint("SwitchIntDef")
    fun getStatusTitle(context: Context?, @Status statusCode: Long): String {
        return when (statusCode) {
            ERROR_CONNECTION        -> context?.getString(R.string.SIGN_ERROR_NETWORK)        ?: " "
            ERROR_DATA_EMPTY        -> context?.getString(R.string.SIGN_ERROR_NO_DATA)        ?: " "
            LOADING_PROGRESS        -> context?.getString(R.string.SIGN_LOADING)              ?: " "
            ERROR_DATA_COMPLETE     -> context?.getString(R.string.SIGN_DATA_COMPLETE)        ?: " "
            else                         -> ""
        }
    }

    @SuppressLint("SwitchIntDef")
    fun getStatusIcon(@Status statusCode: Long): Int {
        return when (statusCode) {
            ERROR_CONNECTION        -> R.drawable.ic_status_error
            ERROR_DATA_EMPTY        -> R.drawable.ic_error_data_black
            ERROR_DATA_COMPLETE     -> R.drawable.ic_status_data_complete
            else                         -> 0
        }
    }

}
