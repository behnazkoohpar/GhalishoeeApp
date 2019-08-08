package com.koohpar.oghli.data.model.api.requestBody;

public class ListBaseRequestBody {
    private String OouoOGhla;

    public ListBaseRequestBody(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }
}
