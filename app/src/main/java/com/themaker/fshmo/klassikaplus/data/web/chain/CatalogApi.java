package com.themaker.fshmo.klassikaplus.data.web.chain;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class CatalogApi {

    private static final String url_dev = "http://192.168.37.128:7430/";

    private static final int TIMEOUT_SECONDS = 2;

    private final DataEndpoint dataEndpoint;
    private final OkHttpClient client;

    private static CatalogApi api;

    public static CatalogApi getInstance() {
        if (api == null) {
            api = new CatalogApi();
        }
        return api;
    }

    private CatalogApi() {
        final Retrofit retrofit;
        client = buildOkHttpClient();
        retrofit = buildRetrofit();
        dataEndpoint = retrofit.create(DataEndpoint.class);
    }

    @NonNull
    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(url_dev)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient buildOkHttpClient() {
        final HttpLoggingInterceptor networkLoggingInterceptor = new HttpLoggingInterceptor();
        networkLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(networkLoggingInterceptor)
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    public DataEndpoint data() {
        return dataEndpoint;
    }

}