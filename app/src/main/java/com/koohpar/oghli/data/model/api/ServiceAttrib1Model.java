package com.koohpar.oghli.data.model.api;

public class ServiceAttrib1Model {
    private String ServiceAttrib1ID;
    private String ServiceID;
    private String ServiceAttribCode;
    private String ServiceAttribTitle;

    public ServiceAttrib1Model(String serviceAttrib1ID, String serviceID, String serviceAttribCode, String serviceAttribTitle) {
        ServiceAttrib1ID = serviceAttrib1ID;
        ServiceID = serviceID;
        ServiceAttribCode = serviceAttribCode;
        ServiceAttribTitle = serviceAttribTitle;
    }

    public String getServiceAttrib1ID() {
        return ServiceAttrib1ID;
    }

    public void setServiceAttrib1ID(String serviceAttrib1ID) {
        ServiceAttrib1ID = serviceAttrib1ID;
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
