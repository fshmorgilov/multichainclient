package com.themaker.fshmo.klassikaplus.presentation.catalog

import androidx.annotation.NonNull
import com.arellomobile.mvp.MvpView
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.data.domain.ItemCategory
import com.themaker.fshmo.klassikaplus.presentation.base.MvpBaseView
import com.themaker.fshmo.klassikaplus.presentation.common.State
import io.reactivex.disposables.Disposable

interface CatalogView : MvpBaseView {

    fun setDataset(items: List<Item>)

    fun addSub(subscription: Disposable)

    fun setCategories(categories:List<ItemCategory>)
}