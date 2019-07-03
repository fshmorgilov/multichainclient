package com.themaker.fshmo.klassikaplus.data.web.chain

import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ResponseDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface DataEndpoint {

    @GET("")
    fun getData(
        @Header("Authorization") authorizator: String,
        @Body body: String
    ): Single<ResponseDto>
}