package com.themaker.fshmo.klassikaplus.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val textError = catalog_error
    private val retryBtn = catalog_retry
    private val toolbar = catalog_toolbar as Toolbar?
    private lateinit var recycler: RecyclerView
    private lateinit var callback: WebItemCallback

    private var currentCategory: ItemCategory = ItemCategory.ZHAKET

    @InjectPresenter
    internal lateinit var presenter: CatalogPresenter

    private lateinit var glide: RequestManager

    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun onPostCreateView() {
        super.onPostCreateView()
        callback = activity as WebItemCallback
        glide = Glide.with(this)
//        configureToolbar(toolbar!!)
        presenter.provideDataset(currentCategory)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.catalog_recycler)
        with(recycler) {
            val catalogAdapter = CatalogAdapter(glide, dataset, callback::launchItemWebViewFragment)
            layoutManager = LinearLayoutManager(activity)
            catalogAdapter.setDataset(dataset)
            adapter = catalogAdapter
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
            R.id.category_selection -> {
                return true; TODO("open category selection")
            }
            else -> {
                super.onOptionsItemSelected(item); return false
            }
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
                toolbar?.visibility = View.VISIBLE
                textError.visibility = View.GONE
                retryBtn.visibility = View.GONE
            }
            State.Loading -> {
                recycler.visibility = View.GONE
                toolbar?.visibility = View.VISIBLE
                textError.visibility = View.GONE
                retryBtn.visibility = View.GONE
            }
            State.NetworkError -> {
                recycler.visibility = View.GONE
                toolbar?.visibility = View.GONE
                textError.visibility = View.VISIBLE
                retryBtn.visibility = View.VISIBLE
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
