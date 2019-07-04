package com.themaker.fshmo.klassikaplus.presentation.wallet

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.themaker.fshmo.klassikaplus.R
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment
import com.themaker.fshmo.klassikaplus.presentation.common.State

class WalletFragment : MvpBaseFragment(), WalletView {

    private val TAG = javaClass.name
    private lateinit var error: LinearLayout
    private lateinit var errorRetryBtn: TextView
    private lateinit var toolbar: Toolbar

    private val navigator by lazy { NavHostFragment.findNavController(this) }
    @InjectPresenter
    internal lateinit var presenter: WalletPresenter

    private fun setupActionBar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        toolbar.title = rootView.resources.getString(R.string.catalog)
        with(actionBar) {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun initializeView() {
        TODO()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                navigator.navigate()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item); return false
            }
        }
    }

    override fun showState(state: State) {
        when (state) {
            State.HasData -> {

                toolbar.visibility = View.VISIBLE
                error.visibility = View.GONE
                errorRetryBtn.visibility = View.GONE
            }
            State.Loading -> {

                toolbar.visibility = View.VISIBLE
                error.visibility = View.GONE
                errorRetryBtn.visibility = View.GONE
            }
            State.NetworkError -> {

                toolbar.visibility = View.VISIBLE
                error.visibility = View.VISIBLE
                errorRetryBtn.visibility = View.VISIBLE
            }
            else -> throw IllegalStateException()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.catalog_menu, menu)
    }

    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun setLayoutRes(): Int {
        return R.layout.wallet_layout
    }
}