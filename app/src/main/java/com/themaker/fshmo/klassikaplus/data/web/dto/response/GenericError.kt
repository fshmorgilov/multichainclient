package com.themaker.fshmo.klassikaplus.data.web.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GenericError(
    @SerializedName("code") @Expose
    val code: Int,
    @SerializedName("message") @Expose
    val message: String
)