package com.colantoni.federico.projects.networkmodule.retrofit;

import com.colantoni.federico.projects.networkmodule.model.spotify.GetTrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpotifyWebAPI {

    String ACTION_TRACK = "v1/tracks/{id}";

    @GET(ACTION_TRACK)
    Call<GetTrackResponse> spotifyTrack(@Path("id") String trackId);
}
