package com.themaker.fshmo.klassikaplus.presentation.catalog

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.themaker.fshmo.klassikaplus.R
import com.themaker.fshmo.klassikaplus.data.domain.Item
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

    @InjectPresenter
    private lateinit var presenter: CatalogPresenter

    private var glide: RequestManager = Glide.with(this)

    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun onPostCreateView() {
        super.onPostCreateView()
        with(recycler){
            adapter = CatalogAdapter(glide, dataset, callback::launchItemWebViewFragment)
            layoutManager = LinearLayoutManager(context)
            setDataset(dataset)
            addItemDecoration(GridSpaceItemDecoration(1,1))
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
                recycler.visibility =View.VISIBLE
                errorViewList.forEach { it.visibility = View.GONE }
            }
            State.Loading -> {
                recycler.visibility =View.GONE
                errorViewList.forEach { it.visibility = View.GONE }
            }
            State.NetworkError -> {
                recycler.visibility =View.GONE
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
