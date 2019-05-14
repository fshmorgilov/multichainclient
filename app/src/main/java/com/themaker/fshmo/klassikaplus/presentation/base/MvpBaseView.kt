package com.themaker.fshmo.klassikaplus.presentation.base

import com.arellomobile.mvp.MvpView
import com.themaker.fshmo.klassikaplus.presentation.common.State

interface MvpBaseView : MvpView {

    fun showState(state:State)
}