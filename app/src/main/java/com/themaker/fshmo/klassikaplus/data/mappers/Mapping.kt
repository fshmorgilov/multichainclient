package com.themaker.fshmo.klassikaplus.data.mappers

/**
 * Базовый класс для маппинга моделей.
 */
abstract class Mapping<From, To> {

    abstract fun map(from: From): To
}
