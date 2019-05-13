package com.themaker.fshmo.klassikaplus.data.web.dto.revision

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.themaker.fshmo.klassikaplus.data.web.dto.common.ErrorDto

import java.io.Serializable

class ResponseDto : Serializable {

    @SerializedName("data")
    @Expose
    var data: Int? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("errors")
    @Expose
    var errors: List<ErrorDto>? = null

    companion object {
        private const val serialVersionUID = -2518952915370601330L
    }

}
