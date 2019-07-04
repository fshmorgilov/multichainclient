package com.themaker.fshmo.klassikaplus.presentation.wallet

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.themaker.fshmo.klassikaplus.presentation.base.BasePresenter

@InjectViewState
class WalletPresenter: BasePresenter<WalletView>() {

    private val TAG = javaClass.name

    private fun logError(throwable: Throwable) {
        Log.e(TAG, "logError: error in dataset: " + throwable.message)
    }

}