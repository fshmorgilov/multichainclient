package com.themaker.fshmo.klassikaplus.presentation.decoration

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class GridSpaceItemDecoration(private val verticalSpaceHeight: Int, private val horizontalSpaceWidth: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpaceHeight
        outRect.top = verticalSpaceHeight
        outRect.left = horizontalSpaceWidth
        outRect.right = horizontalSpaceWidth
    }
}