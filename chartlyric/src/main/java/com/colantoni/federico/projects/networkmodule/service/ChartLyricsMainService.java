package com.colantoni.federico.projects.networkmodule.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colantoni.federico.projects.networkmodule.R;
import com.colantoni.federico.projects.networkmodule.helper.BorderedCircleTransform;
import com.colantoni.federico.projects.networkmodule.helper.HelperClass;
import com.colantoni.federico.projects.networkmodule.model.chartlyrics.searchlyricdirect.SearchLyricDirectRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.chartlyrics.searchlyricdirect.SearchLyricDirectResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.model.spotify.GetTrackResponse;
import com.colantoni.federico.projects.networkmodule.receiver.ChartLyricsBroadcastReceiver;
import com.colantoni.federico.projects.networkmodule.retrofit.ChartLyricsAPI;
import com.colantoni.federico.projects.networkmodule.retrofit.SpotifyWebAPI;
import com.premnirmal.Magnet.IconCallback;
import com.premnirmal.Magnet.Magnet;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartLyricsMainService extends Service implements IconCallback, ChartLyricsBroadcastReceiver.ChartLyricsBroadcastReceiverListener {

    private ImageView chartLyricsIcon;

    private TextView trackTitle;

    private TextView trackArtist;

    private TextView trackLyric;

    private RelativeLayout chartLyricsView;

    private Magnet chartLyricsMagnet;

    private ChartLyricsBroadcastReceiver chartLyricsBroadcastReceiver;

    private RelativeLayout lyricLayout;

    @Override
    public void onCreate() {

        super.onCreate();

        chartLyricsView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.view_current_lyric, null, false);
        lyricLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.layout_lyric, null, false);

        trackTitle = (TextView) lyricLayout.findViewById(R.id.songTitle);
        trackArtist = (TextView) lyricLayout.findViewById(R.id.songArtist);
        trackLyric = (TextView) lyricLayout.findViewById(R.id.songLyric);
        chartLyricsIcon = (ImageView) chartLyricsView.findViewById(R.id.chartLyricsIcon);
    }

    @Override
    public void onDestroy() {

        chartLyricsView = null;

        unregisterReceiver(chartLyricsBroadcastReceiver);

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (HelperClass.ACTION_START_CHARTLYRICS_SERVICE.equals(intent.getAction())) {

            chartLyricsBroadcastReceiver = new ChartLyricsBroadcastReceiver();
            chartLyricsBroadcastReceiver.setListener(this);

            IntentFilter intentFilter = new IntentFilter(HelperClass.SPOTIFY_METADATACHANGED);
            registerReceiver(chartLyricsBroadcastReceiver, intentFilter);
        }
        else if (HelperClass.ACTION_SHOW_MAGNET_SERVICE.equals(intent.getAction())) {

            chartLyricsMagnet = new Magnet.Builder(this).setIconView(chartLyricsView).setRemoveIconShouldBeResponsive(false).setIconCallback(this).setShouldStickToWall(true).setShouldFlingAway(true).setInitialPosition(0, 400).build();
            chartLyricsMagnet.show();
        }
        else {

            if (chartLyricsMagnet != null) {

                chartLyricsMagnet.destroy();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onFlingAway() {

    }

    @Override
    public void onMove(float x, float y) {

    }

    @Override
    public void onIconClick(View icon, float iconXCoord, float iconYCoord) {

        ((RelativeLayout) icon).removeView(lyricLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.LEFT_OF, R.id.chartLyricsIcon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            params.addRule(RelativeLayout.START_OF, R.id.chartLyricsIcon);
        }

        ((RelativeLayout) icon).addView(lyricLayout, params);

        if (lyricLayout.getVisibility() == View.GONE) {

            lyricLayout.setVisibility(View.VISIBLE);
        }
        else {

            lyricLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onIconDestroyed() {

    }

    @Override
    public void metadataChanged(String trackId, String artistName, String albumName, String trackName, int trackLengthInSec) {

        trackArtist.setText(artistName);
        trackTitle.setText(trackName);

        ChartLyricsAPI chartLyricsAPI = ChartLyricsService.createRetrofitService(ChartLyricsAPI.class);

        SearchLyricDirectRequestEnvelope.RequestBody.SearchLyricDirect searchLyricDirect = new SearchLyricDirectRequestEnvelope.RequestBody.SearchLyricDirect(artistName, trackName);

        SearchLyricDirectRequestEnvelope.RequestBody requestBody = new SearchLyricDirectRequestEnvelope.RequestBody(searchLyricDirect);

        SearchLyricDirectRequestEnvelope searchLyricDirectRequestEnvelope = new SearchLyricDirectRequestEnvelope(requestBody);

        final Call<SearchLyricDirectResponseEnvelope> call = chartLyricsAPI.searchLyricDirect(searchLyricDirectRequestEnvelope);

        call.enqueue(new Callback<SearchLyricDirectResponseEnvelope>() {

            @Override
            public void onResponse(Call<SearchLyricDirectResponseEnvelope> call, Response<SearchLyricDirectResponseEnvelope> response) {

                if (response.isSuccess()) {

                    if (response.body() != null) {

                        trackLyric.setText(response.body().getResponseBody().getSearchLyricResponse().getSearchLyricResult().getLyric());

                        SpotifyWebAPI spotifyWebAPI = SpotifyService.createRetrofitService(SpotifyWebAPI.class);

                        String[] split = trackId.split(":");

                        final Call<GetTrackResponse> getTrackResponseCall = spotifyWebAPI.spotifyTrack(split[2]);

                        getTrackResponseCall.enqueue(new Callback<GetTrackResponse>() {

                            @Override
                            public void onResponse(Call<GetTrackResponse> call, Response<GetTrackResponse> response) {

                                if (response.isSuccess()) {

                                    String imageUrl = response.body().getAlbum().getImages()[1].getUrl();

                                    Picasso.with(ChartLyricsMainService.this).load(imageUrl).transform(new BorderedCircleTransform(ContextCompat.getColor(ChartLyricsMainService.this, android.R.color.holo_blue_dark))).placeholder(R.drawable.ic_chartlyrics).into(chartLyricsIcon);
                                }
                            }

                            @Override
                            public void onFailure(Call<GetTrackResponse> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchLyricDirectResponseEnvelope> call, Throwable t) {

            }
        });
    }

    @Override
    public void playbackStateChanged(boolean isPlaying, int positionInMs) {

    }
}
