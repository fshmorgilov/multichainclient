package com.themaker.fshmo.klassikaplus.data.web.catalog

import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ResponseDto
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.categories.ResponseDto as CategoryResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogEndpoint {

    @GET("catalog/novelties/")
    fun getNovelty(): Single<ResponseDto>

    @GET("catalog/{categoryId}")
    fun getItemsByCategory(@Path("categoryId") category:Int): Single<ResponseDto>

    @GET("catalog/get_categories")
    fun getCategories():Single<CategoryResponseDto>
}