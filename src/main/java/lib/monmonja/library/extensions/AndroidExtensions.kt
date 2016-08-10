package lib.monmonja.library.extensions

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.File
import java.io.IOException

/**
 * Created by almondjoseph on 8/8/2016.
 */
val DEFAULT_LOG_TAG = "monmonja-lib"
val Context.isDebugBuild:Boolean
    get() = 0 != (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)

fun Context.debug(log:String) {
    if (isDebugBuild) {
        Log.d(DEFAULT_LOG_TAG, log)
    }
}

fun AppCompatActivity.developerMode() {
    if (0 != (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
    }
}

fun File.createNoMedia () {
    if (this.isDirectory) {
        val mediaFile = File(this, ".nomedia")
        if (!mediaFile.exists()) {
            try {
                mediaFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    } else {
        throw Exception("Not a folder")
    }
}