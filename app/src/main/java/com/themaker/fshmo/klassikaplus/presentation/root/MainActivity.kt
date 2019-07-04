package com.themaker.fshmo.klassikaplus.presentation.root

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.navigation.NavigationView
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.R
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.data.preferences.Preferences
import com.themaker.fshmo.klassikaplus.presentation.base.MvpAppCompatActivity
import com.themaker.fshmo.klassikaplus.presentation.wallet.WalletFragment
import com.themaker.fshmo.klassikaplus.presentation.web_item.WebItemFragment
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainActivityView, WebItemCallback, MainNavigationCallback {

    @Inject
    internal lateinit var preferences: Preferences
    @InjectPresenter
    internal lateinit var presenter: MainActivityPresenter

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ----------------------")
        Log.i(TAG, "onCreate: STARTED")
        App.getInstance().component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        drawerLayout = findViewById(R.id.main_base_view_group)
        navigationView = findViewById(R.id.main_navigation)
        navigationView.setNavigationItemSelectedListener { this.onNavigationItemSelected(it) }
        preferences.setFirstTimeAppLaunch()
    }

    override fun onBackPressed() {
        Log.i(TAG, "onBackPressed: ")
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount > 1) {
            val fragments = supportFragmentManager.fragments
            for (fragment in fragments) {
                if (fragment is WebItemFragment)
                    supportFragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit()
                supportFragmentManager.popBackStack()
                Log.i(TAG, "onBackPressed: details fragment removed")
            }
        } else
            finish()
    }

    override fun launchItemWebViewFragment(item: Item) {
        Log.i(TAG, "launchItemWebViewFragment: displaying itemWebViewFragment with item: " + item.name)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, WebItemFragment.newInstance(item))
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.i(TAG, "onOptionsItemSelected: MainNavigation called") //this is not printed out
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showMainNavigation() {
        drawerLayout = findViewById(R.id.main_base_view_group)
        drawerLayout!!.openDrawer(GravityCompat.START)
        Log.d(TAG, "showMainNavigation: opening navigstion")
    }

    private fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout!!.closeDrawers()
        when (menuItem.itemId) {
            R.id.nav_catalog -> {}
//                .replace(R.id.main_frame, W)
//                .addToBackStack("Catalog")
//                .commit()
            R.id.nav_about -> {
            }
        }
        return true
    }

    companion object {

        private val TAG = MainActivity::class.java.name
        private const val WALLET_TAG = "WALLET"

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
