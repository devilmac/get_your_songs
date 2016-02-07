package com.colantoni.federico.projects.networkmodule.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SpotifyService {

    public static final String API_BASE_URL = "https://api.spotify.com/";

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private SpotifyService() {

    }

    public static <S> S createRetrofitService(Class<S> serviceClass) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);
        httpClient.readTimeout(16, TimeUnit.SECONDS);
        httpClient.connectTimeout(16, TimeUnit.SECONDS);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(JacksonConverterFactory.create()).client(httpClient.build());

        final Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}
