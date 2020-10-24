package com.StevesDisciples.AlarmApp;

import android.content.Context;
import android.util.Log;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class Spotify {
    /***
     * The client id for our registered application
     */
    private static final String CLIENT_ID = "506d2499036447adbf170c0fb14e552f";
    /***
     *
     */
    private SpotifyAppRemote spotifyAppRemote;

    /***
     * This method attempts the integrate Spotify with the user
     *
     * @param ctx - Current context of the application
     */
    public void connect(Context ctx) {
        ConnectionParams connectionParams = new ConnectionParams.Builder(CLIENT_ID).build();
        SpotifyAppRemote.connect(ctx, connectionParams, new Connector.ConnectionListener() {
            @Override
            public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                spotifyAppRemote = spotifyAppRemote;
                Log.d("Spotify.connect", "Connected!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("Spotify.connect", "Failed to connect");

                // display error on user interface
            }
        });
    }

    /***
     * Method returns true or false depeneding on whether the user has integrated with Spotify or not.
     *
     * @return whether Spotify is currently connected or not
     */
    public boolean isConnected() {
        return spotifyAppRemote.isConnected();
    }

}
