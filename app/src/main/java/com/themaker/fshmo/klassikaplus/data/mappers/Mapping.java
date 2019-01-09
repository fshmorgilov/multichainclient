package com.themaker.fshmo.klassikaplus.data.mappers;

/**
 * Базовый класс для маппинга моделей.
 */
public abstract class Mapping<From, To> {

    public abstract To map(From from);
}
