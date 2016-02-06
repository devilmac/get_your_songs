package com.colantoni.federico.projects.networkmodule.service;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;

public class ChartLyricsService {

    public static final String API_BASE_URL = "http://api.chartlyrics.com/";

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private ChartLyricsService() {

    }

    public static <S> S createRetrofitService(Class<S> serviceClass) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);
        httpClient.readTimeout(16, TimeUnit.SECONDS);
        httpClient.connectTimeout(16, TimeUnit.SECONDS);

        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create(serializer)).client(httpClient.build());

        final Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    public static void changeLoggingInterceptorLevel(HttpLoggingInterceptor.Level newLevel) {

        logging.setLevel(newLevel);
    }
}
