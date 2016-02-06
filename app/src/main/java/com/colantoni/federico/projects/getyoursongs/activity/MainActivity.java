package com.colantoni.federico.projects.getyoursongs.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.colantoni.federico.projects.getyoursongs.R;
import com.colantoni.federico.projects.getyoursongs.fragment.MainActivityFragment;
import com.colantoni.federico.projects.getyoursongs.fragment.PlayerScreenFragment;
import com.colantoni.federico.projects.getyoursongs.setting.SettingsActivity;
import com.colantoni.federico.projects.getyoursongs.util.Helper;
import com.premnirmal.Magnet.Magnet;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.ConnectionStateCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.MainFragmentListener, ConnectionStateCallback {

    private static final int LOGIN_SPOTIFY_REQUEST_CODE = 8573;

    private static final int MAGNET_REQUEST_CODE = 647;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private Magnet.Builder chartLyricBuilder;

    private Magnet chartLyricsMagnet;

    private MainActivityFragment mainActivityFragment;

    private FragmentManager fragmentManager;

    private PlayerScreenFragment playerScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);

        setSupportActionBar(toolbar);

        chartLyricBuilder = new Magnet.Builder(this).setIconView(R.mipmap.ic_launcher).setShouldStickToWall(true).setShouldFlingAway(true).setRemoveIconResId(R.drawable.trash).setRemoveIconShadow(R.drawable.bottom_shadow).setInitialPosition(-100, -200);

        fragmentManager = getSupportFragmentManager();

        mainActivityFragment = new MainActivityFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, mainActivityFragment).commit();

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
    }

    @Override
    protected void onResumeFragments() {

        super.onResumeFragments();

        checkDrawOverlayPermission();
    }

    @Override
    protected void onDestroy() {

        if (playerScreenFragment != null) {

            unregisterReceiver(playerScreenFragment.chartLyricsBroadcastReceiver);
        }

        chartLyricsMagnet.destroy();

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuItemId = item.getItemId();

        switch (menuItemId) {

            case R.id.action_settings:

                startActivity(new Intent(this, SettingsActivity.class));

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSnackBar(boolean isRetrofit2Enabled) {

    }

    @Override
    public void loginWithSpotifyButtonClick() {

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(Helper.CLIENT_ID, AuthenticationResponse.Type.TOKEN, Helper.REDIRECT_URI);

        builder.setScopes(new String[]{"user-library-read", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, LOGIN_SPOTIFY_REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (LOGIN_SPOTIFY_REQUEST_CODE == requestCode) {

            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            if (response.getType() == AuthenticationResponse.Type.TOKEN) {

                onLoggedIn();

                playerScreenFragment = PlayerScreenFragment.newInstance(response.getAccessToken());
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, playerScreenFragment).addToBackStack(PlayerScreenFragment.class.getCanonicalName()).commit();
            }
        }
        else if (MAGNET_REQUEST_CODE == requestCode) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (Settings.canDrawOverlays(this)) {

                    // continue here - permission was granted
                    chartLyricsMagnet = chartLyricBuilder.build();
                    chartLyricsMagnet.show();
                    chartLyricsMagnet.setPosition(200, 400, true);
                }
            }
        }
    }

    @Override
    public void onLoggedIn() {

        mainActivityFragment.changeButtonLabel(R.id.loginSpotify, "Logout from Spotify");

        Snackbar.make(coordinatorLayout, "Logged in with your Spotify Premium account!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoggedOut() {

        mainActivityFragment.changeButtonLabel(R.id.loginSpotify, getString(R.string.login_with_spotify));

        Snackbar.make(coordinatorLayout, "Logged out from your Spotify Premium account!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoginFailed(Throwable throwable) {

        Snackbar.make(coordinatorLayout, "Login with Spotify failed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onTemporaryError() {

    }

    @Override
    public void onConnectionMessage(String s) {

    }

    public void checkDrawOverlayPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(this)) {

                final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));

                startActivityForResult(intent, MAGNET_REQUEST_CODE);
            }
            else {

                // continue here - permission was granted
                chartLyricsMagnet = chartLyricBuilder.build();
                chartLyricsMagnet.show();
                chartLyricsMagnet.setPosition(200, 400, true);
            }
        }
        else {

            // continue here - permission was granted
            chartLyricsMagnet = chartLyricBuilder.build();
            chartLyricsMagnet.show();
            chartLyricsMagnet.setPosition(200, 400, true);
        }
    }
}
