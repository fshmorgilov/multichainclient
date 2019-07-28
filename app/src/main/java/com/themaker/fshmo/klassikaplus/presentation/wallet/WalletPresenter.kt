package com.themaker.fshmo.klassikaplus.presentation.wallet

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.data.repositories.CashRepository
import com.themaker.fshmo.klassikaplus.data.repositories.UserRepository
import com.themaker.fshmo.klassikaplus.presentation.base.BasePresenter
import com.themaker.fshmo.klassikaplus.presentation.common.State
import javax.inject.Inject

@InjectViewState
class WalletPresenter @Inject constructor(
    private val cashRepository: CashRepository,
    private val userRepository: UserRepository
) : BasePresenter<WalletView>() {

    private val TAG = javaClass.name

    fun provideCurrentBalance() {

        val subscription = cashRepository.provideCurrentBalances(userRepository.provideWalletNumber())
            .subscribe(
                {
                    viewState.displayAddressBalances(it)
                    viewState.showState(State.HasData)
                },
                {
                    logError(it)
                    viewState.showState(State.NoData)
                }
            )
        disposeOnDestroy(subscription)
    }


    private fun logError(throwable: Throwable) {
        Log.e(TAG, "logError: error in dataset: " + throwable.message)
    }
}