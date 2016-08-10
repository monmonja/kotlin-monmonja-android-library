package lib.monmonja.library.views.data

import android.support.v7.widget.RecyclerView

/**
 * Created by almondjoseph on 7/8/2016.
 */
abstract class BaseAdapter<T, VH: RecyclerView.ViewHolder>(): RecyclerView.Adapter<VH>() {
    val mData: MutableList<T> = mutableListOf()

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
        return mData.size
    }
}
