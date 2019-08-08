package com.koohpar.oghli.data.model.api;

public class ServiceAttrib2Model {
    private String ServiceAttrib2ID;
    private String ServiceID;
    private String ServiceAttribCode;
    private String ServiceAttribTitle;

    public ServiceAttrib2Model(String serviceAttrib2ID, String serviceID, String serviceAttribCode, String serviceAttribTitle) {
        ServiceAttrib2ID = serviceAttrib2ID;
        ServiceID = serviceID;
        ServiceAttribCode = serviceAttribCode;
        ServiceAttribTitle = serviceAttribTitle;
    }

    public String getServiceAttrib2ID() {
        return ServiceAttrib2ID;
    }

    public void setServiceAttrib2ID(String serviceAttrib2ID) {
        ServiceAttrib2ID = serviceAttrib2ID;
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
