package lib.monmonja.library.views.recycler

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import java.util.*

/**
 * Created by almondjoseph on 2/5/16.
 */
class StaggeredGridLayoutScrollListener(val loadMore: (s: String) -> Unit): RecyclerView.OnScrollListener() {
    var mCursor: String = ""
    var mIsLoading: Boolean = false
    var mLoadedCursor = ArrayList<String>()
    internal var mFirstVisibleItemsGrid = IntArray(2)

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = recyclerView!!.layoutManager.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        mFirstVisibleItemsGrid = (recyclerView.layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(mFirstVisibleItemsGrid)
        if (totalItemCount > 0 && totalItemCount - mFirstVisibleItemsGrid[0] <= mFirstVisibleItemsGrid[0] + visibleItemCount) {
            loadMore(mCursor)
        }
    }

    fun reset() {
        mLoadedCursor = ArrayList<String>()
        mCursor = ""
    }

    fun cursorLoadedOrLoading(cursor: String): Boolean {
        var toReturn = false
        if (mIsLoading) {
            toReturn = true
        }
        if (mLoadedCursor.contains(cursor)) {
            toReturn = true
        }

        if (!toReturn) {
            mIsLoading = true
            mLoadedCursor.add(cursor)

            return false
        } else {
            return true
        }

    }

}
