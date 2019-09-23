package com.koohpar.oghli.data.model.api;

public class RofuAttribModel {

    private String RofuAttribID;

    private String RofuAttribCode;

    private String RofuAttribTitle;

    public RofuAttribModel(String rofuAttribID, String rofuAttribCode, String rofuAttribTitle) {
        RofuAttribID = rofuAttribID;
        RofuAttribCode = rofuAttribCode;
        RofuAttribTitle = rofuAttribTitle;
    }

    public String getRofuAttribID() {
        return RofuAttribID;
    }

    public void setRofuAttribID(String rofuAttribID) {
        RofuAttribID = rofuAttribID;
    }

    public String getRofuAttribCode() {
        return RofuAttribCode;
    }

    public void setRofuAttribCode(String rofuAttribCode) {
        RofuAttribCode = rofuAttribCode;
    }

    public String getRofuAttribTitle() {
        return RofuAttribTitle;
    }

    public void setRofuAttribTitle(String rofuAttribTitle) {
        RofuAttribTitle = rofuAttribTitle;
    }
}
