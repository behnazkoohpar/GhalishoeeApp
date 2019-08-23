package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.CustomerCollectAddressModel;

public class NewCollectAddressBody {
    private String OouoOGhla;
    private CustomerCollectAddressModel CustomerCollectAddress;

    public NewCollectAddressBody(String requestOoghli, CustomerCollectAddressModel customerCollectAddressModel) {
        OouoOGhla = requestOoghli;
        CustomerCollectAddress = customerCollectAddressModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerCollectAddressModel getCustomerCollectAddress() {
        return CustomerCollectAddress;
    }

    public void setCustomerCollectAddress(CustomerCollectAddressModel customerCollectAddress) {
        CustomerCollectAddress = customerCollectAddress;
    }
}
