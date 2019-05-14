package com.themaker.fshmo.klassikaplus.data.mappers

import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ItemDto

class DtoToDbItemMapper : Mapping<ItemDto, DbItem>() {
    override fun map(itemDto: ItemDto): DbItem {
        return DbItem().apply {
            id = itemDto.id
            extId = itemDto.extId!!
            name = itemDto.name
            description = itemDto.description
            vendorCode = itemDto.vendorCode
            novelty = itemDto.isNovelty
            pageAlias = itemDto.pageAlias
            icon = itemDto.icon
            basePrice = itemDto.basePrice!!
            discount = itemDto.discount.toDouble()
            price = itemDto.price
            category = itemDto.category
            categoryId = itemDto.categoryId
        }
    }
}
