package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.CustomerCollectMobileModel;

public class NewCollectMobileBody {

    private String OouoOGhla;
    private CustomerCollectMobileModel CustomerCollectMobile;

    public NewCollectMobileBody(String requestOoghli, CustomerCollectMobileModel customerCollectMobileModel) {
        OouoOGhla = requestOoghli;
        CustomerCollectMobile=customerCollectMobileModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerCollectMobileModel getCustomerCollectMobile() {
        return CustomerCollectMobile;
    }

    public void setCustomerCollectMobile(CustomerCollectMobileModel customerCollectMobile) {
        CustomerCollectMobile = customerCollectMobile;
    }
}
