package lib.monmonja.library.extensions

import android.content.Context
import android.view.View
import com.google.gson.Gson
import com.monmonja.dailyPictureQuotes.DailyImageApplication
import com.monmonja.dailyPictureQuotes.R
import com.monmonja.dailyPictureQuotes.adapter.QuoteImageJsonResult
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response
import com.thetransactioncompany.jsonrpc2.client.ConnectionConfigurator
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session
import me.zhanghai.android.materialprogressbar.MaterialProgressBar
import org.jetbrains.anko.*
import java.net.HttpURLConnection

/**
 * Created by almondjoseph on 8/8/2016.
 */
fun JSONRPC2Session.initRpcServer (apiSecretKey: String) {
    this.options.trustAllCerts(true)
    this.options.acceptCookies(true)

    class BasicAuthenticator: ConnectionConfigurator {
        override fun configure(connection: HttpURLConnection?) {
            connection!!.addRequestProperty("API_KEY", apiSecretKey)
        }
    }
    this.connectionConfigurator = BasicAuthenticator()
}

fun JSONRPC2Session.request(ctx: Context, method:String, params: Map <String, Any>, mapClass:Class<Any>, success: (result:Any) -> Unit) = async() {
    val request: JSONRPC2Request = JSONRPC2Request(method, params, "req-001")
    try {
        val response: JSONRPC2Response? = send(request)
        if (response != null) {
            val gson: Gson = Gson()
            val videoResult: Any = gson.fromJson(response.toJSONObject().toJSONString(), mapClass)

            uiThread {
                ctx.debug("done" + response.toJSONObject().toJSONString())
                success(videoResult)
            }
        }
    } catch (e:Exception) {
        ctx.toast("Network error found, please try again later or check your internet")
    }
}