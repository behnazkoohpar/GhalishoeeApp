package com.koohpar.oghli.data.model.api;

import com.google.gson.annotations.SerializedName;

public class DetailHomeResponse {
    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
