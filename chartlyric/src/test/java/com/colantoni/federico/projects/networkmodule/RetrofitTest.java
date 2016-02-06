package com.colantoni.federico.projects.networkmodule;

import com.colantoni.federico.projects.networkmodule.model.searchlyric.SearchLyricRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyric.SearchLyricRequestEnvelope.RequestBody;
import com.colantoni.federico.projects.networkmodule.model.searchlyric.SearchLyricRequestEnvelope.RequestBody.SearchLyric;
import com.colantoni.federico.projects.networkmodule.model.searchlyric.SearchLyricResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectRequestEnvelope.RequestBody.SearchLyricDirect;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyrictext.SearchLyricTextRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyrictext.SearchLyricTextRequestEnvelope.RequestBody.SearchLyricText;
import com.colantoni.federico.projects.networkmodule.model.searchlyrictext.SearchLyricTextResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.retrofit.ChartLyricsAPI;
import com.colantoni.federico.projects.networkmodule.service.ChartLyricsService;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

public class RetrofitTest {

    private ChartLyricsAPI chartLyricsAPI = ChartLyricsService.createRetrofitService(ChartLyricsAPI.class);

    @Test
    public void testSearchLyric() throws Exception {

        SearchLyric searchLyric = new SearchLyric("Vasco Rossi", "Sally");

        RequestBody requestBody = new RequestBody(searchLyric);

        SearchLyricRequestEnvelope requestEnvelope = new SearchLyricRequestEnvelope(requestBody);

        final Call<SearchLyricResponseEnvelope> call = chartLyricsAPI.searchLyric(requestEnvelope);

        final Response<SearchLyricResponseEnvelope> response = call.execute();

        assertTrue(response.isSuccess());
        assertTrue(response.body().getResponseBody().getSearchLyricResponse().getSearchLyricResult().size() > 0);
    }

    @Test
    public void testSearchLyricDirect() throws Exception {

        SearchLyricDirect searchLyricDirect = new SearchLyricDirect("Vasco Rossi", "Vivere");

        SearchLyricDirectRequestEnvelope.RequestBody requestBody = new SearchLyricDirectRequestEnvelope.RequestBody(searchLyricDirect);

        SearchLyricDirectRequestEnvelope searchLyricDirectRequestEnvelope = new SearchLyricDirectRequestEnvelope(requestBody);

        final Call<SearchLyricDirectResponseEnvelope> call = chartLyricsAPI.searchLyricDirect(searchLyricDirectRequestEnvelope);

        final Response<SearchLyricDirectResponseEnvelope> response = call.execute();

        assertTrue(response.isSuccess());
        assertTrue(response.body().getResponseBody().getSearchLyricResponse().getSearchLyricResult().getLyricSong() != null);
    }

    @Test
    public void testSearchLyricText() throws Exception {

        SearchLyricText searchLyricDirect = new SearchLyricText("Once upon a time");

        SearchLyricTextRequestEnvelope.RequestBody requestBody = new SearchLyricTextRequestEnvelope.RequestBody(searchLyricDirect);

        SearchLyricTextRequestEnvelope searchLyricDirectRequestEnvelope = new SearchLyricTextRequestEnvelope(requestBody);

        final Call<SearchLyricTextResponseEnvelope> call = chartLyricsAPI.searchLyricText(searchLyricDirectRequestEnvelope);

        final Response<SearchLyricTextResponseEnvelope> response = call.execute();

        assertTrue(response.isSuccess());
        assertTrue(response.body().getResponseBody().getSearchLyricTextResponse().getSearchLyricTextResult().getSearchLyricResult().size() > 0);
    }
}
