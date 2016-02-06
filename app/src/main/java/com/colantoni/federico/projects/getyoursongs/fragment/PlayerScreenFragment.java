package com.colantoni.federico.projects.getyoursongs.fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.colantoni.federico.projects.getyoursongs.R;
import com.colantoni.federico.projects.getyoursongs.util.Helper;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectRequestEnvelope;
import com.colantoni.federico.projects.networkmodule.model.searchlyricdirect.SearchLyricDirectResponseEnvelope;
import com.colantoni.federico.projects.networkmodule.receiver.ChartLyricsBroadcastReceiver;
import com.colantoni.federico.projects.networkmodule.retrofit.ChartLyricsAPI;
import com.colantoni.federico.projects.networkmodule.service.ChartLyricsService;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.PlayerStateCallback;
import com.spotify.sdk.android.player.Spotify;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerScreenFragment extends Fragment implements PlayerStateCallback, PlayerNotificationCallback, View.OnClickListener, ChartLyricsBroadcastReceiver.ChartLyricsBroadcastReceiverListener {

    private static final String BUNDLE_KEY_ACCESS_TOKEN = "BUNDLE_KEY_ACCESS_TOKEN";

    public ChartLyricsBroadcastReceiver chartLyricsBroadcastReceiver;

    @Bind(R.id.songTitle)
    TextView songTitle;

    @Bind(R.id.songArtist)
    TextView songArtist;

    @Bind(R.id.songLyric)
    TextView songLyric;

    @Bind(R.id.skipPrev)
    Button skipPrev;

    @Bind(R.id.skipNext)
    Button skipNext;

    private Player mPlayer;

    public PlayerScreenFragment() {

    }

    public static PlayerScreenFragment newInstance(String responseToken) {

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_ACCESS_TOKEN, responseToken);

        PlayerScreenFragment playerScreenFragment = new PlayerScreenFragment();
        playerScreenFragment.setArguments(bundle);

        return playerScreenFragment;
    }

    @Override
    public void onPlayerState(PlayerState playerState) {

        Log.d("SPOTIFY", "Current track: " + playerState.trackUri);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player_screen, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        chartLyricsBroadcastReceiver = new ChartLyricsBroadcastReceiver();
        chartLyricsBroadcastReceiver.setListener(this);
    }

    @Override
    public void onResume() {

        super.onResume();

        IntentFilter intentFilter = new IntentFilter("com.spotify.music.metadatachanged");
        getActivity().registerReceiver(chartLyricsBroadcastReceiver, intentFilter);

        skipPrev.setOnClickListener(this);
        skipNext.setOnClickListener(this);

        Config playerConfig = new Config(getActivity(), getArguments().getString(BUNDLE_KEY_ACCESS_TOKEN), Helper.CLIENT_ID, Config.DeviceType.SMARTPHONE);

        Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {

            @Override
            public void onInitialized(Player player) {

                mPlayer = player;

                mPlayer.addPlayerNotificationCallback(PlayerScreenFragment.this);
                mPlayer.getPlayerState(PlayerScreenFragment.this);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Override
    public void onDestroy() {

        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

        //        switch (eventType) {
        //
        //            case TRACK_CHANGED: {

        Log.d("SPOTIFY", eventType.name() + " - Current track: " + playerState.trackUri);
        //            }
        //        }
    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

        Log.d("SPOTIFY", errorType.name() + ": " + s);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        switch (viewId) {

            case R.id.skipPrev: {

                if (mPlayer != null) {

                    mPlayer.skipToPrevious();

                    return;
                }
            }

            case R.id.skipNext: {

                if (mPlayer != null) {

                    mPlayer.skipToNext();

                    return;
                }
            }

            default:
        }
    }

    @Override
    public void metadataChanged(String trackId, String artistName, String albumName, String trackName, int trackLengthInSec) {

        songArtist.setText(artistName);
        songTitle.setText(trackName);

        // TODO: 06/02/16 get lyric from ChartLyrics

        ChartLyricsAPI chartLyricsAPI = ChartLyricsService.createRetrofitService(ChartLyricsAPI.class);

        SearchLyricDirectRequestEnvelope.RequestBody.SearchLyricDirect searchLyricDirect = new SearchLyricDirectRequestEnvelope.RequestBody.SearchLyricDirect(artistName, trackName);

        SearchLyricDirectRequestEnvelope.RequestBody requestBody = new SearchLyricDirectRequestEnvelope.RequestBody(searchLyricDirect);

        SearchLyricDirectRequestEnvelope searchLyricDirectRequestEnvelope = new SearchLyricDirectRequestEnvelope(requestBody);

        final Call<SearchLyricDirectResponseEnvelope> call = chartLyricsAPI.searchLyricDirect(searchLyricDirectRequestEnvelope);

        call.enqueue(new Callback<SearchLyricDirectResponseEnvelope>() {

            @Override
            public void onResponse(Call<SearchLyricDirectResponseEnvelope> call, Response<SearchLyricDirectResponseEnvelope> response) {

                songLyric.setText(response.body().getResponseBody().getSearchLyricResponse().getSearchLyricResult().getLyric());
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
