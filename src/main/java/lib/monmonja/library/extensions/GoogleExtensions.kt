package lib.monmonja.library.extensions

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.analytics.FirebaseAnalytics
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


val Context.firebaseAnalytics: FirebaseAnalytics
    get() = FirebaseAnalytics.getInstance(this)


fun FirebaseAnalytics.trackScreen (screenName:String) {
    val params = Bundle()
    params.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "screen")
    params.putString(FirebaseAnalytics.Param.ITEM_NAME, screenName)
    logEvent(FirebaseAnalytics.Event.VIEW_ITEM, params)
}

fun FirebaseAnalytics.customEvent (category:String, action:String) {
    val params = Bundle()
    params.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "screen")
    params.putString("action", action)
    params.putString("category", category)
    logEvent("ga_event", params)
}

fun Context.setupAds (adView: AdView) {
    if (defaultSharedPreferences.contains(PREF_UNLOCKED_ALL)) {
        adView.visibility = View.GONE
    } else {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        val request = AdRequest.Builder()

        // Add your debug devices here, adb logcat Ads:V *:S
        request.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        request.addTestDevice("2BFD7E4B4ADCEE4D0487E2B5F50B8164") // oneplus one - almond
        request.addTestDevice("7D029ED128C55FC1FD82213D1957E376") // mi3 - mom
        request.addTestDevice("10C5F1C7812038544A75DC84EDF4493B") // nexus 7 - almond
        request.addTestDevice("FF93ACE81707704AC4FB5298C39A9AA7") // xioami redmi2 - almond
        request.addTestDevice("A0F7DD16FC136B169B7FFE0B01AD1C3D") // nexus 9
        request.addTestDevice("F78F1162E69B755FD1D7F1B9A595FE2D") // nexus 6p

        adView.loadAd(request.build())
    }
}