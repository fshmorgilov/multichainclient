package com.themaker.fshmo.klassikaplus.data.web.catalog;


import com.themaker.fshmo.klassikaplus.data.domain.ItemCategory;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.ResponseDto;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CatalogEndpoint {

    @GET("catalog/novelties")
    Single<ResponseDto> getNovelty();

    @GET("catalog/{category}") //todo category
    Single<ResponseDto> getItemsByCategory(@Path("category") ItemCategory category);
}