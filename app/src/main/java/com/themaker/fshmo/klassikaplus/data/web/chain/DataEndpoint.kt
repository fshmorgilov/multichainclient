package com.themaker.fshmo.klassikaplus.data.web.chain

import com.themaker.fshmo.klassikaplus.data.web.dto.response.AddressBalancesResponse
import com.themaker.fshmo.klassikaplus.data.web.dto.response.FundsResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface DataEndpoint {

    @GET("")
    fun getData(
        @Header("Authorization") authorizator: String,
        @Body body: String
    ): Single<FundsResponse>

    fun getBalancesData(
        @Header("Authorization") authorizator: String,
        @Body body: String
    ): Single<AddressBalancesResponse>

}