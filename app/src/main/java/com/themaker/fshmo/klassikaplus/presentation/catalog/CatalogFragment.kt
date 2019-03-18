package com.themaker.fshmo.klassikaplus.presentation.catalog

import com.bumptech.glide.RequestManager
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment
import com.themaker.fshmo.klassikaplus.presentation.common.State
import io.reactivex.disposables.Disposable
import java.util.*

class CatalogFragment : MvpBaseFragment(), CatalogView {

    private val TAG = javaClass.name
    private val dataset = ArrayList<Item>()

    private lateinit var glide: RequestManager

    override fun onBackPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLayoutRes(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDataset(items: List<Item>) {
        dataset
    }

    override fun showError() {
//
    }

    override fun showState(state: State) {
        when (state) {
            State.HasData -> {
                TODO("NYI")
            }
            State.Loading -> {
                TODO("NYI")
            }
            State.NetworkError -> {
                TODO("NYI")
            }
        }
        super.showState(state)
    }

    override fun addSub(subscription: Disposable) {
        super.addSub(subscription)
    }


}