package com.colantoni.federico.projects.getyoursongs.fragment;

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
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.PlayerStateCallback;
import com.spotify.sdk.android.player.Spotify;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayerScreenFragment extends Fragment implements PlayerStateCallback, PlayerNotificationCallback, View.OnClickListener {

    private static final String BUNDLE_KEY_ACCESS_TOKEN = "BUNDLE_KEY_ACCESS_TOKEN";

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
    public void onResume() {

        super.onResume();

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
}
