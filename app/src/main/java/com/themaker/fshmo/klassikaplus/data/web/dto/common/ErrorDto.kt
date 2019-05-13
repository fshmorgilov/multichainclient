package com.themaker.fshmo.klassikaplus.data.web.dto.common

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("code")
    @Expose
    val code: Int?,
    @SerializedName("description")
    @Expose
    val description: String?
) : Serializable {
    companion object {
        private const val serialVersionUID = 5865768659366165626L
    }

}