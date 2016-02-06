package com.colantoni.federico.projects.networkmodule.retrofit;

import com.colantoni.federico.projects.networkmodule.model.searchlyric.SearchLyricRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyric.SearchLyricResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyrictext.SearchLyricTextRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyrictext.SearchLyricTextResponseEnvelope;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChartLyricsAPI {

    String ACTION_CHART_LYRIC = "/apiv1.asmx";

    @Headers({"Content-Type: application/soap+xml", "Accept-Charset: utf-8"})
    @POST(ACTION_CHART_LYRIC)
    Call<SearchLyricResponseEnvelope> searchLyric(@Body SearchLyricRequestEnvelope requestEnvelope);

    @Headers({"Content-Type: application/soap+xml", "Accept-Charset: utf-8"})
    @POST(ACTION_CHART_LYRIC)
    Call<SearchLyricDirectResponseEnvelope> searchLyricDirect(@Body SearchLyricDirectRequestEnvelope requestEnvelope);

    @Headers({"Content-Type: application/soap+xml", "Accept-Charset: utf-8"})
    @POST(ACTION_CHART_LYRIC)
    Call<SearchLyricTextResponseEnvelope> searchLyricText(@Body SearchLyricTextRequestEnvelope requestEnvelope);

    //    @Headers({"Content-Type: application/soap+xml", "Accept-Charset: utf-8"})
    //    @POST(ACTION_CHART_LYRIC)
    //    Call<GetLyricResponse> getLyric(@Query("lyricId") String lyricId, @Query("lyricCheckSum") String lyricCheckSum);

    //    @Headers({"Content-Type: application/soap+xml", "Accept-Charset: utf-8"}))
    //    @POST(ACTION_CHART_LYRIC)
    //    Call<GetLyricResponse> addLyric(@Query("trackId") int trackId, @Query("trackCheckSum") String trackCheckSum, @Query("lyric") String lyric, @Query("email") String email);
}
