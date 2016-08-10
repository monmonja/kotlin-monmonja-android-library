package lib.src.main.java.lib.monmonja.library.views.recycler

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import org.jetbrains.anko.dip

/**
 * Created by almondjosephmendoza on 2/3/2016.
 */
class SpacesItemDecoration(context: Context, fullSize:Int = 4) : RecyclerView.ItemDecoration() {

    private val mFullSpan: Int
    private val mHalfSpan: Int

    init {
        mFullSpan = context.dip(fullSize)
        mHalfSpan = mFullSpan / 2
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        if (view.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            val spanIndex = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
            if (spanIndex == 0) {
                outRect.left = mFullSpan
                outRect.right = mHalfSpan
            } else {
                outRect.left = mHalfSpan
                outRect.right = mFullSpan
            }
            outRect.bottom = mFullSpan
        }  else {
            outRect.left = mFullSpan
            outRect.right = mFullSpan
            outRect.bottom = mFullSpan
        }
    }

}
