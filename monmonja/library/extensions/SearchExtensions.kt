package lib.monmonja.library.extensions

import android.app.SearchManager
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.monmonja.smsquotes.R
import org.jetbrains.anko.act

/**
 * Created by almondjoseph on 8/8/2016.
 */
val EXTRA_SEARCH_QUERY = "EXTRA_SEARCH_QUERY"

fun AppCompatActivity.setupSearch (menu: Menu, searchActivity: AppCompatActivity) {
    // Retrieve the SearchView and plug it into SearchManager
    val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
    val searchManager = act.getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
    searchView.setSearchableInfo(searchManager.getSearchableInfo(act.componentName))
    searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            val intent: Intent = Intent(act, searchActivity.javaClass)
            intent.putExtra(EXTRA_SEARCH_QUERY, query)
            startActivity(intent)
            ActivityCompat.invalidateOptionsMenu(act)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}
