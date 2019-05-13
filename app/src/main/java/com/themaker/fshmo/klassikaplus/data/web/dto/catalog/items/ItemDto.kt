package com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items

import java.io.Serializable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("ext_id")
    @Expose
    val extId: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("vendorCode")
    @Expose
    val vendorCode: String?,
    @SerializedName("novelty")
    @Expose
    val isNovelty: Boolean?,
    @SerializedName("pageAlias")
    @Expose
    val pageAlias: String?,
    @SerializedName("icon")
    @Expose
    val icon: String?,
    @SerializedName("manufacturer")
    @Expose
    val manufacturer: String?
) : Serializable {

    @SerializedName("base_price")
    @Expose
    var basePrice: Double? = null
    @SerializedName("discount")
    @Expose
    var discount: Int = 0
    @SerializedName("discountable")
    @Expose
    var isDiscountable: Boolean = false
    @SerializedName("price")
    @Expose
    var price: Double = 0.toDouble()
    @SerializedName("photos")
    @Expose
    var photos: List<PhotoDto>? = null
    @SerializedName("category")
    @Expose
    var category: String? = null
    @SerializedName("categoryId")
    @Expose
    var categoryId: Int? = null

    companion object {

        private const val serialVersionUID = 4022723505261094500L
    }
}