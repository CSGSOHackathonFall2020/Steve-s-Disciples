package com.StevesDisciples.SpotifyApi.remote;

import android.content.Context;
import android.util.Log;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

public class SpotifyRemote {
    /**
     *
     */
    private static final String CLIENT_ID = "506d2499036447adbf170c0fb14e552f";
    /**
     *
     */
    private static final String REDIRECT_URI = "https://google.com";
    /**
     *
     */
    private SpotifyAppRemote mSpotifyAppRemote;

    /**
     *
     * @param ctx
     */
    public void connectAppRemote(Context ctx) {
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(ctx, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");
                        playPlaylist();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    /**
     *
     */
    public void playPlaylist() {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
    }

    /**
     *
     * @return
     */
    public boolean isConnected() {
        return mSpotifyAppRemote.isConnected();
    }
}
