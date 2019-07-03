package com.themaker.fshmo.klassikaplus.data.repositories

import android.util.Log
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase
import com.themaker.fshmo.klassikaplus.data.web.dto.response.FundsResponse
import io.reactivex.Single
import javax.inject.Inject

class CatalogRepository : BaseRepository() {

    @Inject
    lateinit var db: AppDatabase
    @Inject
    lateinit var factory: RequestFactory
    init {
        App.getInstance().component.inject(this)
    }

    fun getItemsByCategory(category: Int?): Single<FundsResponse> {
        Log.i(TAG, "getItemsByCategory: Requested items by category: " + category!!)
        return factory.makeSendAssetRequest("mock", "mock", 1)
        TODO("")
    }

    companion object {

        private val TAG = CatalogRepository::class.java.name

        internal var INSTANCE: CatalogRepository

        init {
            INSTANCE = CatalogRepository()
        }
    }
}
