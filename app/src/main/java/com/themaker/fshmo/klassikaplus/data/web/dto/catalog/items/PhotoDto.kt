package com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items

import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("image")
    @Expose
    val image: String?

) : Serializable {


    companion object {
        private const val serialVersionUID = -9032062017948970785L
    }

}