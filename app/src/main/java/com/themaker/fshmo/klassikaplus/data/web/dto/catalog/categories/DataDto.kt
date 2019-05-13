package com.themaker.fshmo.klassikaplus.data.web.dto.catalog.categories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class DataDto(
    @SerializedName("categories")
    @Expose
    val items: Map<Int, String>?
) : Serializable {


    companion object {
        private const val serialVersionUID = 6985812391318078436L
    }

}