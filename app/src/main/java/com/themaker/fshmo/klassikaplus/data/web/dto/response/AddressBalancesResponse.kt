package com.themaker.fshmo.klassikaplus.data.web.dto.response

import com.google.gson.annotations.SerializedName

data class AddressBalancesResponse(
    @SerializedName("error")
    val error: GenericError,
    @SerializedName("id")
    val id: String,
    @SerializedName("result")
    val result: List<AddressBalanceResult>?
)

data class AddressBalanceResult(
    @SerializedName("assetref")
    val assetReference: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("qty")
    val quantity: Float?
)