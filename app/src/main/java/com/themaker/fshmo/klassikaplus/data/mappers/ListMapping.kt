package com.themaker.fshmo.klassikaplus.data.mappers

import java.util.ArrayList

class ListMapping<TFrom, TTo>(private val mapping: Mapping<TFrom, TTo>) {

    fun map(list: List<TFrom>): List<TTo> {
        val arrayList = ArrayList<TTo>(list.size)
        for (from in list) {
            arrayList.add(mapping.map(from))
        }
        return arrayList
    }
}