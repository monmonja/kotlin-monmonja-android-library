package lib.monmonja.library.extensions

import android.content.Context
import org.jetbrains.anko.alert
import org.jetbrains.anko.defaultSharedPreferences

/**
 * Created by almondjoseph on 18/8/2016.
 */
val PREF_RATE_APP_COUNTER: String = "pref_rate_app_counter"
val PREF_RATE_APP_DISABLE: String = "pref_rate_app_disable"

fun Context.rateAppDialog(title:String, message:String, storeUrl:String, counterBeforeShow:Int = 10) {
    var currentCounter = defaultSharedPreferences.getInt(PREF_RATE_APP_COUNTER, 0)
    if (!defaultSharedPreferences.contains(PREF_RATE_APP_DISABLE)) {
        if (currentCounter % counterBeforeShow == counterBeforeShow - 1) {
            alert(message, title) {
                positiveButton ("Happy to rate") {
                    ctx.gotoUrl(storeUrl)
                    dismiss()

                    defaultSharedPreferences
                        .edit().putBoolean(PREF_RATE_APP_DISABLE, true)
                        .apply()
                }
                negativeButton ("Later") {

                }
            }.show()
        }
        currentCounter += 1
        defaultSharedPreferences.edit()
                .putInt(PREF_RATE_APP_COUNTER, currentCounter)
                .apply()
    }
}