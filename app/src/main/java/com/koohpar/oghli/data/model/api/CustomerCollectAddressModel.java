package com.koohpar.oghli.data.model.api;

public class CustomerCollectAddressModel {
    private String CollectAddress;
    private String CustomerID;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCollectAddress() {
        return CollectAddress;
    }

    public void setCollectAddress(String collectAddress) {
        CollectAddress = collectAddress;
    }
}
