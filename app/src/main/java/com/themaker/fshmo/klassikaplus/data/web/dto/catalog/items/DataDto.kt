package com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items

import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("items")
    @Expose
    val items: List<ItemDto>?
) : Serializable {
    companion object {
        private const val serialVersionUID = 6985802591318078436L
    }

}