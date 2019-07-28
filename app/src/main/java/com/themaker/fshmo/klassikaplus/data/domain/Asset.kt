package com.themaker.fshmo.klassikaplus.data.domain

data class Asset(
    val `open`: Boolean,
    val assetref: String,
    val issueqty: Int,
    val issueraw: Int,
    val issuetxid: String,
    val multiple: Int,
    val name: String
)
