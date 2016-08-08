package lib.monmonja.library.views.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import com.monmonja.dailyPictureQuotes.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find

/**
 * Created by almondjoseph on 7/8/2016.
 */
open class RecyclerViewFragment: Fragment() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view!!.backgroundColor =  ContextCompat.getColor(ctx, R.color.window_background)

        mRecyclerView = find<RecyclerView>(R.id.recycler_view)
        mSwipeRefreshLayout = find<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        setupRecyclerLayout()
    }

    open fun setupRecyclerLayout () {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3,
                R.color.refresh_progress_4)
    }

    open fun checkEmptyView () {
        if (mRecyclerView.adapter.itemCount == 0) {
            find<View>(R.id.empty_layout).visibility = View.VISIBLE
        } else {
            find<View>(R.id.empty_layout).visibility = View.GONE
        }
    }

}