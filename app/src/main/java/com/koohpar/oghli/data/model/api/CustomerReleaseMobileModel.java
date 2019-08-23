package com.koohpar.oghli.data.model.api;

public class CustomerReleaseMobileModel {
    private String ReleaseMobile;
    private String CustomerID;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getReleaseMobile() {
        return ReleaseMobile;
    }

    public void setReleaseMobile(String releaseMobile) {
        ReleaseMobile = releaseMobile;
    }
}
