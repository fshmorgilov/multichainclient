package com.themaker.fshmo.klassikaplus.data.web.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FundsResponse(
    @SerializedName("error") @Expose
    val error: GenericError,
    @SerializedName("id") @Expose
    val id: String,
    @SerializedName("result") @Expose
    val txid: String
)