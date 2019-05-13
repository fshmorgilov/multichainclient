package com.themaker.fshmo.klassikaplus.presentation.root

import com.themaker.fshmo.klassikaplus.data.domain.Item

interface WebItemCallback {

    fun launchItemWebViewFragment(item: Item)
}
