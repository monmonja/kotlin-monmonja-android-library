package lib.monmonja.library.extensions

/**
 * Created by almondjoseph on 16/8/2016.
 */

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.FileNotFoundException

/**
 * Created by almondjoseph on 9/8/2016.
 */

@Throws(FileNotFoundException::class)
fun getBitmapAndScaleWithHeight(context: Context, sourceImageUri: Uri, height: Int): Bitmap {
    val selectedBitmap: Bitmap?
    val bmpFactoryOptions = BitmapFactory.Options()
    bmpFactoryOptions.inJustDecodeBounds = true
    if (sourceImageUri.toString().contains("content://")) {
        val imageStream = context.contentResolver.openInputStream(sourceImageUri)
        BitmapFactory.decodeStream(imageStream, null, bmpFactoryOptions)
    } else {
        BitmapFactory.decodeFile(sourceImageUri.path, bmpFactoryOptions)
    }

    bmpFactoryOptions.inSampleSize = Math.ceil((bmpFactoryOptions.outHeight / height.toFloat()).toDouble()).toInt()
    bmpFactoryOptions.inJustDecodeBounds = false

    if (sourceImageUri.toString().contains("content://")) {
        val imageStream = context.contentResolver.openInputStream(sourceImageUri)
        selectedBitmap = BitmapFactory.decodeStream(imageStream, null, bmpFactoryOptions)
    } else {
        selectedBitmap = BitmapFactory.decodeFile(sourceImageUri.path, bmpFactoryOptions)
    }
    return selectedBitmap
}