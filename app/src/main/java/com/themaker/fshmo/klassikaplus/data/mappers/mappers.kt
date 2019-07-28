package com.themaker.fshmo.klassikaplus.data.mappers

import com.themaker.fshmo.klassikaplus.data.domain.Balance
import com.themaker.fshmo.klassikaplus.data.web.dto.response.AddressBalanceResult

fun mapAddressBalanceResponseToDomain(result: AddressBalanceResult): Balance =
    Balance(
        assetName = result.name ?: "",
        assetReference = result.assetReference ?: "",
        sum = result.quantity ?: 0.0f
    )
