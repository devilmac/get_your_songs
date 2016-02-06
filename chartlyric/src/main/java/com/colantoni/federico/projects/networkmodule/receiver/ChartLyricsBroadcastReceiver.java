package com.colantoni.federico.projects.networkmodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.colantoni.federico.projects.networkmodule.receiver.ChartLyricsBroadcastReceiver.BROADCAST_TYPES.METADATA_CHANGED;
import static com.colantoni.federico.projects.networkmodule.receiver.ChartLyricsBroadcastReceiver.BROADCAST_TYPES.PLAYBACK_STATE_CHANGED;
import static com.colantoni.federico.projects.networkmodule.receiver.ChartLyricsBroadcastReceiver.BROADCAST_TYPES.QUEUE_CHANGED;

public class ChartLyricsBroadcastReceiver extends BroadcastReceiver {

    private ChartLyricsBroadcastReceiverListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {

        // This is sent with all broadcasts, regardless of type. The value is taken from
        // System.currentTimeMillis(), which you can compare to in order to determine how
        // old the event is.
        long timeSentInMs = intent.getLongExtra("timeSent", 0L);

        String action = intent.getAction();

        switch (action) {

            case METADATA_CHANGED: {

                String trackId = intent.getStringExtra("id");
                String artistName = intent.getStringExtra("artist");
                String albumName = intent.getStringExtra("album");
                String trackName = intent.getStringExtra("track");
                int trackLengthInSec = intent.getIntExtra("length", 0);

                if (listener != null) {

                    listener.metadataChanged(trackId, artistName, albumName, trackName, trackLengthInSec);
                }

                break;
            }

            case PLAYBACK_STATE_CHANGED: {

                boolean playing = intent.getBooleanExtra("playing", false);
                int positionInMs = intent.getIntExtra("playbackPosition", 0);

                // Do something with extracted information
                if (listener != null) {

                    listener.playbackStateChanged(playing, positionInMs);
                }

                break;
            }

            case QUEUE_CHANGED: {

                // Sent only as a notification, your app may want to respond accordingly.

                break;
            }

            default:
        }
    }

    public ChartLyricsBroadcastReceiverListener getListener() {

        return listener;
    }

    public void setListener(ChartLyricsBroadcastReceiverListener listener) {

        this.listener = listener;
    }

    public interface ChartLyricsBroadcastReceiverListener {

        void metadataChanged(String trackId, String artistName, String albumName, String trackName, int trackLengthInSec);

        void playbackStateChanged(boolean isPlaying, int positionInMs);
    }

    static final class BROADCAST_TYPES {

        static final String SPOTIFY_PACKAGE = "com.spotify.music";

        static final String PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged";

        static final String QUEUE_CHANGED = SPOTIFY_PACKAGE + ".queuechanged";

        static final String METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged";
    }
}
