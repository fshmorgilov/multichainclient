package com.themaker.fshmo.klassikaplus.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class MvpBasePresenter<T : MvpView> : MvpPresenter<T>() {
    protected var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    protected var disposable: Disposable? = null

    protected fun addSub(subscription: Disposable) {
        compositeDisposable!!.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed)
            compositeDisposable!!.dispose()
    }


}
