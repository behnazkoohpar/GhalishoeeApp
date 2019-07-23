package com.koohpar.oghli.data.model.api;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("userId")
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
