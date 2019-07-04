package com.themaker.fshmo.klassikaplus.presentation.wallet

import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseView
import com.themaker.fshmo.klassikaplus.presentation.common.State

interface WalletView : MvpBaseView {
    override fun showState(state: State)

}
