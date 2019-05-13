package com.themaker.fshmo.klassikaplus.presentation.base


import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    private var compositeDisposable: CompositeDisposable? = null

    protected fun disposeOnDestroy(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
    }

    override fun onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
            compositeDisposable = null
        }
        super.onDestroy()
    }
}