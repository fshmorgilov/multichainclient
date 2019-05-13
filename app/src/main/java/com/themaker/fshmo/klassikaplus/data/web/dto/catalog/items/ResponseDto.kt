package com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items

import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.themaker.fshmo.klassikaplus.data.web.dto.common.ErrorDto

data class ResponseDto(
    @SerializedName("data")
    @Expose
    val data: DataDto?,
    @SerializedName("status")
    @Expose
    val status: String?,
    @SerializedName("errors")
    @Expose
    val errors: List<ErrorDto>?
) : Serializable {
    companion object {
        private const val serialVersionUID = -2518952915370601330L
    }

}