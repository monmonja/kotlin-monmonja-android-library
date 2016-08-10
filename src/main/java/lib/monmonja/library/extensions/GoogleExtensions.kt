package lib.src.main.java.lib.monmonja.library.extensions

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.find

/**
 * Created by almondjoseph on 5/8/2016.
 */

val PREF_UNLOCKED_ALL: String = "pref_unlocked_all"
val IN_APP_UNLOCKED_ALL: String = "unlock_all"


fun Context.inAppRemoveAds() {
    defaultSharedPreferences.edit()
            .putBoolean(PREF_UNLOCKED_ALL, true)
            .apply()
}
