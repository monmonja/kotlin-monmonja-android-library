package lib.monmonja.library.views.data

import android.support.v7.widget.RecyclerView

/**
 * Created by almondjoseph on 7/8/2016.
 */
abstract class BaseAdapter<T, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    val mData: MutableList<T> = mutableListOf()
    /*
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        if (viewType == CONTENT_TYPE) {
            return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_my_view, parent, false))
        } else {
            // see https://firebase.google.com/docs/admob/android/native-express?hl=en-US for the view
            return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.adapter_ads, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == CONTENT_TYPE) {
            holder.bindForecast(mData.elementAt(position - getAdsPositionAddIndex(position)))
        } else {
            val adView = holder.itemView.find<NativeExpressAdView>(R.id.adView)
            val request = AdRequest.Builder().addTestDevice("F78F1162E69B755FD1D7F1B9A595FE2D").build()
            adView.loadAd(request)
        }
    }
     */
    var withNativeAds = false
    var adsAfterItemPosition = 10

    protected val ADS_TYPE: Int = 1
    protected val CONTENT_TYPE: Int = 0
    protected val EMPTY_VIEW_TYPE: Int = 2

    open fun getAdsPositionAddIndex (position:Int):Int {
        if (position >= adsAfterItemPosition * 2) {
            return 2
        } else if (position >= adsAfterItemPosition) {
            return 1
        } else {
            return 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (withNativeAds) {
            if (position == adsAfterItemPosition * 2 || position == adsAfterItemPosition) {
                return ADS_TYPE
            } else {
                return CONTENT_TYPE
            }
        } else {
            return super.getItemViewType(position)
        }
    }


    fun addItems(data: Array<T>) {
        mData.addAll(data)
    }

    fun addItems(data: List<T>) {
        mData.addAll(data)
    }

    fun addItem(item: T) {
        mData.add(item)
    }

    fun resetItems() {
        mData.clear()
    }

    override fun getItemCount(): Int {
        val total = mData.size
        if (withNativeAds) {
            if (total >= adsAfterItemPosition * 2) {
                return total + 2
            } else if (total >= adsAfterItemPosition) {
                return total + 1
            } else {
                return total
            }
        } else {
            return total
        }
    }
}
