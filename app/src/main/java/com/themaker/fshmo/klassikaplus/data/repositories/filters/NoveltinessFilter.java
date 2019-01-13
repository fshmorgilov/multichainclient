package com.themaker.fshmo.klassikaplus.data.repositories.filters;

import com.themaker.fshmo.klassikaplus.data.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class NoveltinessFilter implements BaseFilter<List<Item>> {
    public List<Item> filter(List<Item> items) {
        List<Item> filteredList = new ArrayList<>();
        for (Item item : items) {
            if (item.getNovelty()) filteredList.add(item);
        }
        return filteredList;
    }
}
