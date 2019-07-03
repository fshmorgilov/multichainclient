package com.themaker.fshmo.klassikaplus.data.repositories

import com.google.gson.Gson
import com.themaker.fshmo.klassikaplus.data.preferences.Preferences
import com.themaker.fshmo.klassikaplus.data.web.chain.ChainApi
import com.themaker.fshmo.klassikaplus.data.web.dto.requests.GenericRequest
import com.themaker.fshmo.klassikaplus.data.web.dto.response.FundsResponse
import io.reactivex.Single
import android.util.Base64
import android.util.Log
import com.themaker.fshmo.klassikaplus.data.web.dto.response.AddressBalancesResponse
import javax.inject.Inject

class RequestFactory @Inject constructor(private val chainApi: ChainApi, private val preferences: Preferences) {

    fun makeSendAssetRequest(fromWaller: String, toWallet: String, amount: Int): Single<FundsResponse> {
        val request = GenericRequest.Builder
            .addId("")
            .addParam(fromWaller)
            .addParam(toWallet)
            .addParam("asset1")
            .addParam(amount)
            .setMethod("sendassetfrom")
            .setChain("chain1")
            .build()
        Log.d(TAG, request.toString())
        return chainApi.data().getData(makeAuthToken(), Gson().toJson(request))
    }

    fun makeGetWalletBalanceRequest(wallet:String): Single<AddressBalancesResponse>{
        val request = GenericRequest.Builder
            .addId("id")
            .addParam(wallet)
            .setMethod("getaddressbalances")
            .setChain("chain1")
            .build()
        Log.d(TAG, request.toString())
        return chainApi.data().getBalancesData(makeAuthToken(), Gson().toJson(request))
    }

    fun makeAuthToken(): String {
        val base = preferences.proveideChainCredentials()
        val token = "Basic " + Base64.encode(base.toByteArray(), Base64.NO_WRAP)
        Log.d(TAG, "Authentication token: $token")
        return token
    }

    companion object{
        private val TAG = RequestFactory.javaClass.name
    }
}