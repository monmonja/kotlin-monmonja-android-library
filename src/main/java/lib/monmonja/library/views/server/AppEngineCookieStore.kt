package lib.monmonja.library.views.server


import android.content.Context
import org.jetbrains.anko.defaultSharedPreferences
import java.net.CookieStore
import java.net.HttpCookie
import java.net.URI
import java.util.ArrayList
import java.util.HashMap

class AppEngineCookieStore(private val mContext: Context) : CookieStore {
    private val map = HashMap<URI, List<HttpCookie>>()

    override fun add(uri: URI, cookie: HttpCookie) {
        var cookies: MutableList<HttpCookie>? = map[uri] as MutableList<HttpCookie>?
        if (cookies == null) {
            cookies = ArrayList<HttpCookie>()
            map.put(uri, cookies)
        }
        cookies.add(cookie)

        if (cookie.name == COOKIE_NAME_SESSION) {
            mContext.defaultSharedPreferences.edit().putString(PREF_COOKIE_ID, cookie.value).apply()
        }
    }

    override fun get(uri: URI): List<HttpCookie> {
        var cookies: MutableList<HttpCookie>? = map[uri] as MutableList<HttpCookie>?
        if (cookies == null) {
            cookies = ArrayList<HttpCookie>()

            // add session from pref
            val sessionFromPref = mContext.defaultSharedPreferences.getString(PREF_COOKIE_ID, "")
            if (!sessionFromPref.isEmpty()) {
                cookies.add(HttpCookie(COOKIE_NAME_SESSION, sessionFromPref))
            }
            map.put(uri, cookies)
        }

        return cookies
    }

    /** todo check where this is called  */
    override fun getCookies(): List<HttpCookie> {
        val values = map.values
        val result = ArrayList<HttpCookie>()
        for (value in values) {
            result.addAll(value)
        }

        return result
    }

    override fun getURIs(): List<URI> {
        val keys = map.keys
        return ArrayList(keys)

    }

    override fun remove(uri: URI, cookie: HttpCookie): Boolean {
        val cookies = map[uri] as MutableList<HttpCookie>? ?: return false
        return cookies.remove(cookie)
    }

    override fun removeAll(): Boolean {
        map.clear()
        return true
    }

    companion object {

        var COOKIE_NAME_SESSION = "session"
        var PREF_COOKIE_ID = "PREF_COOKIE_ID"
    }
}