package com.themaker.fshmo.klassikaplus.data.repositories

import android.util.Log
import com.themaker.fshmo.klassikaplus.App
import com.themaker.fshmo.klassikaplus.data.domain.Balance
import com.themaker.fshmo.klassikaplus.data.mappers.mapAddressBalanceResponseToDomain
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase
import com.themaker.fshmo.klassikaplus.data.web.dto.response.AddressBalanceResult
import com.themaker.fshmo.klassikaplus.data.web.dto.response.FundsResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CashRepository : BaseRepository() {

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

    fun provideCurrentBalance(wallets: Array<String>): Observable<AddressBalanceResult> {
        var observable = Observable.just(AddressBalanceResult(null, null, null))
        wallets.forEach {
            val observable1: Observable<AddressBalanceResult> = factory.makeGetWalletBalanceRequest(it)
                .flatMapObservable { }
            val observable2: Observable<AddressBalanceResult> = factory.makeGetWalletBalanceRequest(it)
                .flatMapObservable { }
            observable = Observable.mergeDelayError(
                observable1, observable2
            )
        }
        return observable
    }

    fun provideCurrentBalances(wallet: String): Single<Balance> {
        return factory.makeGetWalletBalanceRequest(wallet)
            .map { it.result }
            .map { mapAddressBalanceResponseToDomain(it[1]) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    companion object {

        private val TAG = CashRepository::class.java.name

        internal var INSTANCE: CashRepository = CashRepository()

    }
}
