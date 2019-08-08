package com.koohpar.oghli.data.model.api;

public class ServiceAttrib3Model {
    private String ServiceAttrib3ID;
    private String ServiceID;
    private String ServiceAttribCode;
    private String ServiceAttribTitle;

    public ServiceAttrib3Model(String serviceAttrib3ID, String serviceID, String serviceAttribCode, String serviceAttribTitle) {
        ServiceAttrib3ID = serviceAttrib3ID;
        ServiceID = serviceID;
        ServiceAttribCode = serviceAttribCode;
        ServiceAttribTitle = serviceAttribTitle;
    }

    public String getServiceAttrib3ID() {
        return ServiceAttrib3ID;
    }

    public void setServiceAttrib3ID(String serviceAttrib3ID) {
        ServiceAttrib3ID = serviceAttrib3ID;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public String getServiceAttribCode() {
        return ServiceAttribCode;
    }

    public void setServiceAttribCode(String serviceAttribCode) {
        ServiceAttribCode = serviceAttribCode;
    }

    public String getServiceAttribTitle() {
        return ServiceAttribTitle;
    }

    public void setServiceAttribTitle(String serviceAttribTitle) {
        ServiceAttribTitle = serviceAttribTitle;
    }
}
