package com.themaker.fshmo.klassikaplus.data.domain

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Item(

    var id: String,
    var extId: String,
    var name: String,
    var description: String,
    var vendorCode: String,
    var novelty: Boolean,
    var descriptionLong: String,
    var pageAlias: String,
    var icon: String,
    var photo: String,
    var manufacturer: String,
    var basePrice: Double,
    var discount: Double,
    var discountable: Boolean,
    var price: Double,
    var category: String,
    var categoryId: Int

) : Serializable, Parcelable {
    override fun toString(): String {
        return "Item{" +
                "id='" + id + '\''.toString() +
                ", extId='" + extId + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", description='" + description + '\''.toString() +
                ", icon='" + icon + '\''.toString() +
                ", price='" + price + '\''.toString() +
                '}'.toString()
    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        1 == source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readDouble(),
        source.readDouble(),
        1 == source.readInt(),
        source.readDouble(),
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(extId)
        writeString(name)
        writeString(description)
        writeString(vendorCode)
        writeInt((if (novelty) 1 else 0))
        writeString(descriptionLong)
        writeString(pageAlias)
        writeString(icon)
        writeString(photo)
        writeString(manufacturer)
        writeDouble(basePrice)
        writeDouble(discount)
        writeInt((if (discountable) 1 else 0))
        writeDouble(price)
        writeString(category)
        writeInt(categoryId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {
            override fun createFromParcel(source: Parcel): Item = Item(source)
            override fun newArray(size: Int): Array<Item?> = arrayOfNulls(size)
        }
    }
}

