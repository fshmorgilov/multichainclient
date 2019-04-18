package com.themaker.fshmo.klassikaplus.presentation.catalog

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbCategory
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository
import com.themaker.fshmo.klassikaplus.presentation.common.State
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
internal class CatalogPresenter : MvpPresenter<CatalogView>() {

    private val TAG: String = javaClass.name

    @Inject
    lateinit var repository: CatalogRepository

    lateinit var categoryList: List<DbCategory>

    internal fun provideDataset(categoryId: Int) {
        viewState.showState(State.Loading)
        val subscribe = repository.provideByCategoryData(categoryId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::displayData,
                this::displayError
            )
        viewState.addSub(subscribe)   // FIXME: 2/7/2019 возможно, не стоит
        viewState.setDataset(ArrayList())
    }

    internal fun provideCategories() {
        if (categoryList.isEmpty()) {
            val disposable = repository.provideCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { categories -> viewState.setCategories(categories) },
                    { t: Throwable? -> displayError(throwable = t) }
                )
            viewState.addSub(disposable)
        }
    }

    private fun displayError(throwable: Throwable?) {
        throwable?.let { logError(it) }
        viewState.showState(State.NetworkError)
    }

    private fun logError(throwable: Throwable) {
        Log.e(TAG, "logError: error in dataset: " + throwable.message)
    }

    private fun displayData(items: List<Item>) {
        Log.i(TAG, "provideDataset: # of items: " + items.size)
        viewState.showState(State.HasData)
        viewState.setDataset(items)
    }

    init {
        App.getInstance().component.inject(this)
    }

}