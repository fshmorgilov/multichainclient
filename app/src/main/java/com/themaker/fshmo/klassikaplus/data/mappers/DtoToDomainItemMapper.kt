package com.themaker.fshmo.klassikaplus.data.mappers

import com.themaker.fshmo.klassikaplus.data.domain.Item

//class DtoToDomainItemMapper : Mapping<ItemDto, Item>() {
//    override fun map(itemDto: ItemDto): Item {
//        return Item().apply {
//            id = itemDto?.id
//            setExtId(itemDto?.extId)
//            icon = itemDto?.icon
//            basePrice = itemDto?.basePrice
//            description = itemDto?.description
//            name = itemDto?.name
//            vendorCode = itemDto?.vendorCode
//            novelty = itemDto?.isNovelty
//            pageAlias = itemDto?.pageAlias
//            discount = itemDto?.discount?.toDouble()
//            price = itemDto?.price
//            category = itemDto?.category
//            categoryId = itemDto?.categoryId
//        return Item(id = 9999, basePrice = 32.11, category = "",discount = 0, pageAlias = "", categoryId = 12312,description = "", icon = "",
//            descriptionLong = "",extId = "", discountable = true, novelty = true, photo = "",
//            price = 123.11, vendorCode = 123,manufacturer = "")
//        }
//    }
//}