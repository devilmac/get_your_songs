package com.colantoni.federico.projects.getyoursongs.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.colantoni.federico.projects.getyoursongs.R;
import com.colantoni.federico.projects.networkmodule.helper.HelperClass;
import com.colantoni.federico.projects.networkmodule.service.ChartLyricsMainService;

import butterknife.Bind;
import butterknife.ButterKnife;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 745;

    public static boolean isPermissionGranted = false;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        checkDrawOverlayPermission();
    }

    @Override
    public void onTrimMemory(int level) {

        super.onTrimMemory(level);

        switch (level) {

            case TRIM_MEMORY_UI_HIDDEN: {

                if (isPermissionGranted) {

                    showChartLyricsMagnet();
                }

                break;
            }

            default:
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        hideChartLyricsMagnet();
    }

    @Override
    protected void onDestroy() {

        stopService(new Intent(this, ChartLyricsMainService.class));

        super.onDestroy();
    }

    private void launchMainService() {

        final Intent startChartLyricsService = new Intent(this, ChartLyricsMainService.class);

        startChartLyricsService.setAction(HelperClass.ACTION_START_CHARTLYRICS_SERVICE);

        startService(startChartLyricsService);
    }

    private void showChartLyricsMagnet() {

        final Intent startChartLyricsService = new Intent(this, ChartLyricsMainService.class);

        startChartLyricsService.setAction(HelperClass.ACTION_SHOW_MAGNET_SERVICE);

        startService(startChartLyricsService);
    }

    private void hideChartLyricsMagnet() {

        final Intent startChartLyricsService = new Intent(this, ChartLyricsMainService.class);

        startChartLyricsService.setAction(HelperClass.ACTION_DESTROY_MAGNET_SERVICE);

        startService(startChartLyricsService);
    }

    private void checkDrawOverlayPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(this)) {

                final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            }
            else {

                isPermissionGranted = true;

                launchMainService();
            }
        }
        else {

            launchMainService();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (Settings.canDrawOverlays(this)) {

                    isPermissionGranted = true;

                    launchMainService();
                }
            }
        }
    }
}
