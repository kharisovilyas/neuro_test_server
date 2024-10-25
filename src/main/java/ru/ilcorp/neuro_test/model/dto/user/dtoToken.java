package ru.ilcorp.neuro_test.model.dto.user;

public class dtoToken {
    private String refreshToken;
    private String accessToken;

    public dtoToken() {}

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
