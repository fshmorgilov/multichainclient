package com.themaker.fshmo.klassikaplus.data.repositories

import android.util.Log
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.data.domain.Item
import com.themaker.fshmo.klassikaplus.data.mappers.*
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem
import com.themaker.fshmo.klassikaplus.data.web.catalog.CatalogApi
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.DataDto
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ItemDto
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ResponseDto
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class CatalogRepository : BaseRepository() {

    @Inject
    lateinit var db: AppDatabase
    @Inject
    lateinit var api: CatalogApi

    init {
        App.getInstance().component.inject(this)
    }

    fun provideByCategoryData(category: Int?): Flowable<List<Item>> {
        val itemDtoDbItemMapper = ListMapping(DtoToDbItemMapper())
        return getItemsFromDbByCategory(category)
    }


    private fun getItemsByCategory(category: Int?): Flowable<List<ItemDto>?> {
        Log.i(TAG, "getItemsByCategory: Requested items by category: " + category!!)
        return api.catalog()
            .getItemsByCategory(category)
            .map { it.data }
            .map { it.items }
            .doOnSuccess { it?.let { this.storeItemsInDb(it) } }
            .toFlowable()
            .subscribeOn(Schedulers.io())
    }

    private fun storeItemsInDb(items: List<ItemDto>) {
        val itemDtoDbItemMapper = ListMapping(DtoToDbItemMapper())
        val dbItems = itemDtoDbItemMapper.map(items)
        db!!.itemDao().insertAll(dbItems)
        Log.i(TAG, "storeItemsInDb: items stored " + items.size)
    }

    private fun getItemsFromDbByCategory(categoryId: Int?): Flowable<List<Item>> {
        TODO()
        return Flowable.just(null)

    }

    companion object {

        private val TAG = CatalogRepository::class.java.name

        internal var INSTANCE: CatalogRepository

        init {
            INSTANCE = CatalogRepository()
        }
    }
}
