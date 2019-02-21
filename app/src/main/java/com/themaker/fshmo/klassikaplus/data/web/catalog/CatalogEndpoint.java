package com.themaker.fshmo.klassikaplus.data.web.catalog;


import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.ResponseDto;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface CatalogEndpoint {

    @GET("catalog/novelties")
    Single<ResponseDto> getNovelty();

}