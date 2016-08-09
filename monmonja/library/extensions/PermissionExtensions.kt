package lib.monmonja.library.extensions

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.support.v4.act


/**
 * Created by almondjoseph on 9/5/16.
 */
// Manifest.permission.GET_ACCOUNTS
fun AppCompatActivity.askOnePermission(permission: String, requestCode: Int, granted: () -> Unit): Unit {
    if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    } else {
        granted()
    }
}

fun Fragment.askOnePermission(permission: String, requestCode: Int, granted: () -> Unit): Unit {
    if (ActivityCompat.checkSelfPermission(act, permission) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(arrayOf(permission), requestCode)
    } else {
        granted()
    }
}

fun AppCompatActivity.grantedOnePermission(permission: String, requestCode: Int, permissionRequestCode:Int, afterGranted: () -> Unit) {
    if (requestCode == permissionRequestCode) {
        afterGranted()
    }
}

fun Fragment.grantedOnePermission(permission: String, requestCode: Int, grantResults: IntArray, permissionRequestCode:Int, afterGranted: () -> Unit) {
    for (grant in grantResults) {
        if (grant == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == permissionRequestCode) {
                afterGranted()
            }
        }
    }

}
