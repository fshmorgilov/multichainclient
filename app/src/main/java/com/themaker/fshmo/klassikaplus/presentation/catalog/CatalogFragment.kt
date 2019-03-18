package com.themaker.fshmo.klassikaplus.presentation.catalog

import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseFragment
import com.themaker.fshmo.klassikaplus.presentation.common.State
import io.reactivex.disposables.Disposable

class CatalogFragment : MvpBaseFragment(), CatalogView {
    override fun onBackPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLayoutRes(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDataset(items: List<Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showState(state: State) {
        super.showState(state)
    }

    override fun addSub(subscription: Disposable) {
        super.addSub(subscription)
    }


}