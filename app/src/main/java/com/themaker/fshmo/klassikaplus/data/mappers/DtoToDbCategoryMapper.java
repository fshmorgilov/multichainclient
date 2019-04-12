package com.themaker.fshmo.klassikaplus.data.mappers;

import com.themaker.fshmo.klassikaplus.data.persistence.model.DbCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DtoToDbCategoryMapper extends Mapping<Map<Integer, String>, List<DbCategory>> {
    @Override
    public List<DbCategory> map(Map<Integer, String> categories) {
        List<DbCategory> dbCategories = new ArrayList<>();
        for (Integer key : categories.keySet()) {
            DbCategory category = new DbCategory();
            category.setId(key);
            category.setName(categories.get(key));
            dbCategories.add(category);
        }
        return dbCategories;
    }
}
