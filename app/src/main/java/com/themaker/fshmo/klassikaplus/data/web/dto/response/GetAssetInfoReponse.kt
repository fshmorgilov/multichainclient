package com.themaker.fshmo.klassikaplus.data.web.dto.response
//todo serialized names
data class AssetInfo(
    val error: Any,
    val id: String,
    val result: GetAssetInfoResult
)

data class GetAssetInfoResult(
    val `open`: Boolean,
    val assetref: String,
    val details: Details,
    val issueqty: Int,
    val issueraw: Int,
    val issuetxid: String,
    val multiple: Int,
    val name: String,
    val restrict: Restrict,
    val units: Double
)

data class Restrict(
    val receive: Boolean,
    val send: Boolean
)

class Details(
)