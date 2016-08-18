package lib.monmonja.library.extensions

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity

/**
 * Created by almondjoseph on 18/8/2016.
 */
fun Fragment.gotoUrl(url: String) {
    val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}
fun Context.gotoUrl(url: String) {
    (this as AppCompatActivity).startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}