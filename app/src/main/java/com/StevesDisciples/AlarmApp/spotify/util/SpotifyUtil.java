package com.StevesDisciples.AlarmApp.spotify.util;

public class SpotifyUtil {
    private String token;

    public SpotifyUtil(String givenToken) {
        token = givenToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}