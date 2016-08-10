package lib.monmonja.library.extensions

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.find

/**
 * Created by almondjoseph on 5/8/2016.
 */

val PREF_UNLOCKED_ALL: String = "pref_unlocked_all"
val IN_APP_UNLOCKED_ALL: String = "unlock_all"
val PLAY_SERVICES_RESOLUTION_REQUEST = 9000

fun Context.inAppRemoveAds() {
    defaultSharedPreferences.edit()
            .putBoolean(PREF_UNLOCKED_ALL, true)
            .apply()
}

fun AppCompatActivity.hasPlayServices(): Boolean {
    val googleAPI = GoogleApiAvailability.getInstance()
    val result = googleAPI.isGooglePlayServicesAvailable(this)
    if (result != ConnectionResult.SUCCESS) {
        if (googleAPI.isUserResolvableError(result)) {
            googleAPI.getErrorDialog(this, result, PLAY_SERVICES_RESOLUTION_REQUEST).show()
        }

        return false
    }

    return true
}