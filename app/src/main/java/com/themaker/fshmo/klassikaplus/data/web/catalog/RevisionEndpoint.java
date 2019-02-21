package com.themaker.fshmo.klassikaplus.data.web.catalog;

import com.themaker.fshmo.klassikaplus.data.web.dto.ResponseDto;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface RevisionEndpoint {


    @GET("revision/get_revision")
    ResponseDto checkRevision();
    // FIXME: 2/20/2019 переделать в ResponseDto
}
