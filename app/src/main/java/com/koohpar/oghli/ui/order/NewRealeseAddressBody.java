package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.CustomerReleaseAddressModel;

public class NewRealeseAddressBody {

    private String OouoOGhla;
    private CustomerReleaseAddressModel CustomerReleaseAddress;

    public NewRealeseAddressBody(String requestOoghli, CustomerReleaseAddressModel customerReleaseAddressModel) {
        OouoOGhla = requestOoghli;
        CustomerReleaseAddress = customerReleaseAddressModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerReleaseAddressModel getCustomerReleaseAddress() {
        return CustomerReleaseAddress;
    }

    public void setCustomerReleaseAddress(CustomerReleaseAddressModel customerReleaseAddress) {
        CustomerReleaseAddress = customerReleaseAddress;
    }
}
