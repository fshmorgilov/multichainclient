package com.themaker.fshmo.klassikaplus.data.mappers

import android.util.Log
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem

class DbToDomainItemMapper : Mapping<DbItem, Item>() {

    override fun map(dbItem: DbItem): Item {
        val item = Item()
        item.id = dbItem.id
        item.setExtId(dbItem.extId)
        item.icon = dbItem.icon
        item.price = dbItem.price
        item.name = dbItem.name
        item.novelty = dbItem.novelty
        item.pageAlias = dbItem.pageAlias
        item.category = dbItem.category
        Log.d(TAG, "map: Item parsed: $item")
        return item
    }

    companion object {

        private val TAG = DbToDomainItemMapper::class.java.name
    }
}
