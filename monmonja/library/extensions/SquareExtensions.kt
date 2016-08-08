package lib.monmonja.library.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by almondjoseph on 8/8/2016.
 */
fun ImageView.loadImage (url:String){
    Picasso.with(context).load(url)
            .into(this)
}