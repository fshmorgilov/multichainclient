package com.themaker.fshmo.klassikaplus.presentation.catalog

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.themaker.fshmo.klassikaplus.R
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.data.domain.ItemCategory
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment
import com.themaker.fshmo.klassikaplus.presentation.common.State
import com.themaker.fshmo.klassikaplus.presentation.decoration.GridSpaceItemDecoration
import com.themaker.fshmo.klassikaplus.presentation.root.WebItemCallback
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.catalog_fragment.*

class CatalogFragment : MvpBaseFragment(), CatalogView {

    private val TAG = javaClass.name
    private val dataset = ArrayList<Item>()
    private val callback: WebItemCallback = activity as WebItemCallback
    private val recycler = catalog_recycler
    private val textError = catalog_error
    private val retryBtn = catalog_retry
    private val errorViewList = listOf<View>(textError, retryBtn)
    private val toolbar = catalog_toolbar as Toolbar

    private var currentCategory: ItemCategory = ItemCategory.ZHAKET

    @InjectPresenter
    private lateinit var presenter: CatalogPresenter

    private var glide: RequestManager = Glide.with(this)

    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun onPostCreateView() {
        super.onPostCreateView()
        configureToolbar(toolbar)
        presenter.provideDataset(currentCategory)
        with(recycler) {
            val catalogAdapter = CatalogAdapter(glide, dataset, callback::launchItemWebViewFragment)
            catalogAdapter.setDataset(dataset)
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(GridSpaceItemDecoration(1, 1))
        }
    }

    override fun configureToolbar(toolbar: Toolbar) {
        super.configureToolbar(toolbar)
        toolbar.title = currentCategory.name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.catalog_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.category_selection -> { return true; TODO("open category selection") }
            else -> {super.onOptionsItemSelected(item); return false}
        }
    }

    override fun setDataset(items: List<Item>) {
        dataset.apply {
            if (isEmpty())
                addAll(items)
            else {
                clear(); addAll(items)
            }
            Log.i(TAG, "Setting dataset")
        }
    }

    override fun showError() {
//
    }

    override fun showState(state: State) {
        when (state) {
            State.HasData -> {
                recycler.visibility = View.VISIBLE
                toolbar.visibility = View.VISIBLE
                errorViewList.forEach { it.visibility = View.GONE }
            }
            State.Loading -> {
                recycler.visibility = View.GONE
                toolbar.visibility = View.VISIBLE
                errorViewList.forEach { it.visibility = View.GONE }
            }
            State.NetworkError -> {
                recycler.visibility = View.GONE
                toolbar.visibility = View.GONE
                errorViewList.forEach { it.visibility = View.VISIBLE }
            }
            else -> throw IllegalStateException()
        }
        super.showState(state)
    }

    override fun addSub(subscription: Disposable) {
        super.addSub(subscription)
    }

    override fun setLayoutRes(): Int {
        return R.layout.catalog_fragment
    }
}
