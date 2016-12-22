package lib.monmonja.library.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.support.v4.act
import java.util.*


/**
 * Created by almondjoseph on 9/5/16.
 */
// Manifest.permission.GET_ACCOUNTS
var permissionGrantedFnc: HashMap<Int, (() -> Unit)?> = hashMapOf()

fun AppCompatActivity.askOnePermission(permission: String, requestCode: Int, granted: () -> Unit): Unit {
    permissionGrantedFnc.put(requestCode, granted)
    if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    } else {
        granted()
    }
}

fun Fragment.askOnePermission(permission: String, requestCode: Int, granted: () -> Unit): Unit {
    permissionGrantedFnc.put(requestCode, granted)
    if (ActivityCompat.checkSelfPermission(act, permission) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(arrayOf(permission), requestCode)
    } else {
        granted()
    }
}

fun Context.grantPermission(requestCode: Int) {
    if (permissionGrantedFnc.contains(requestCode)) {
        permissionGrantedFnc[requestCode]?.invoke()
    }
}

fun AppCompatActivity.grantedOnePermission(requestCode: Int, permissionRequestCode:Int, afterGranted: () -> Unit) {
    if (requestCode == permissionRequestCode) {
        afterGranted()
    }
}

fun Fragment.grantedOnePermission(requestCode: Int, grantResults: IntArray, permissionRequestCode:Int, afterGranted: () -> Unit) {
    for (grant in grantResults) {
        if (grant == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == permissionRequestCode) {
                afterGranted()
            }
        }
    }

}
