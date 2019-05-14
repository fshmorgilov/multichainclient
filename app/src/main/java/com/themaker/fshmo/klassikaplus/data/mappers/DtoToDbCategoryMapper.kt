package com.themaker.fshmo.klassikaplus.data.mappers

import android.util.Log
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbCategory

import java.util.ArrayList

class DtoToDbCategoryMapper : Mapping<Map<Int, String>, List<DbCategory>>() {

    override fun map(categories: Map<Int, String>): List<DbCategory> {
        val dbCategories = ArrayList<DbCategory>()
        for (key in categories.keys) {
            val category = DbCategory()
            category.id = key
            category.name = categories[key]
            dbCategories.add(category)
            Log.d(TAG, "map: category id: " + category.id + " name: " + category.name)
        }
        return dbCategories
    }

    companion object {

        private val TAG = DtoToDbCategoryMapper::class.java.name
    }
}
