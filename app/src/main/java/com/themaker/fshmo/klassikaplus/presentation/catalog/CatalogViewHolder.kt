package com.themaker.fshmo.klassikaplus.presentation.catalog

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.bumptech.glide.RequestManager
import com.themaker.fshmo.klassikaplus.data.domain.Item

class CatalogViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val TAG: String = CatalogViewHolder::class.java.name

    private val view = v
    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var price: TextView

    init {
        ButterKnife.bind(this, v)
    }

    fun bind(
        item: Item,
        glide: RequestManager,
        onItemClickListener : (Item) -> Unit
    ) {
        price.text = item.price?.toString()
        name.text = item.name
        item.icon.let { glide.load(it).into(image) }
        view.setOnClickListener { onItemClickListener(item) }
        Log.d(TAG, "Binded item: ${item.id}")
    }
}

