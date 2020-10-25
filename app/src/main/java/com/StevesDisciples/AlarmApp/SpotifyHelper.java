package com.StevesDisciples.AlarmApp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SpotifyHelper {
    /***
     * The client id for our registered application
     */
    private static final String CLIENT_ID = "506d2499036447adbf170c0fb14e552f";
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "https://google.com";

    static void authenticate(Activity activity) {
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request);
    }


}
