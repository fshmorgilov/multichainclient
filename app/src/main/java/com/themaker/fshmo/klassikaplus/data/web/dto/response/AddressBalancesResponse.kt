package com.themaker.fshmo.klassikaplus.data.web.dto.response

import com.google.gson.annotations.SerializedName

data class AddressBalancesResponse(
    @SerializedName("error")
    val error: GenericError,
    @SerializedName("id")
    val id: String,
    @SerializedName("result")
    val result: List<Result>
)

data class Result(
    @SerializedName("assetref")
    val assetReference: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("qty")
    val quantity: Int
)