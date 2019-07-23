package com.koohpar.oghli.data.model.api;

import com.google.gson.annotations.SerializedName;

public class CityResponse {
    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
