package com.themaker.fshmo.klassikaplus.data.mappers;

import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;

public class DbToDomainMapper extends Mapping<DbItem, Item> {
    @Override
    public Item map(DbItem dbItem) {
        Item item = new Item();
        item.setId(dbItem.getId());
        item.setIcon(dbItem.getIcon());
        item.setPrice(dbItem.getPrice());
        item.setName(dbItem.getName());
        return item;
    }
}
