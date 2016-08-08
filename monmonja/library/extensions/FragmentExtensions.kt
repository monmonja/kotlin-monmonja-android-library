package lib.monmonja.library.extensions

import android.app.Fragment
import android.app.FragmentManager
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.monmonja.tides.R

/**
 * Created by almondjoseph on 8/8/2016.
 */


fun goToFragment(fragmentManager: FragmentManager, fragment: Fragment, replace:Boolean = true) {
    val fragmentTag = fragment.javaClass.simpleName
    var addFragment:Boolean = false
    if (replace) {
        val replaceFragment = fragmentManager.findFragmentByTag(fragmentTag)
        if (replaceFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, replaceFragment, fragmentTag)
                    .commit()
        } else {
            addFragment = true
        }

    } else {
        val fragmentPopped = fragmentManager.popBackStackImmediate(fragmentTag, 0)
        if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) {
            addFragment = true
        }
    }

    if (addFragment) {
        fragmentManager
                .beginTransaction()
                .add(R.id.frame_layout, fragment, fragmentTag)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }
}

fun AppCompatActivity.goToFragment(fragment: Fragment) {
    goToFragment(fragmentManager, fragment)
}

fun FragmentActivity.goToFragment(fragment: Fragment) {
    goToFragment(fragmentManager, fragment)
}