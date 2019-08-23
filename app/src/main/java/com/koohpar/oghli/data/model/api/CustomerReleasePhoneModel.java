package com.koohpar.oghli.data.model.api;

public class CustomerReleasePhoneModel {
    private String ReleasePhone;
    private String CustomerID;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getReleasePhone() {
        return ReleasePhone;
    }

    public void setReleasePhone(String releasePhone) {
        ReleasePhone = releasePhone;
    }
}
