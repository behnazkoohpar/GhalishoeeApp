package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.CustomerCollectPhoneModel;

public class NewCollectPhoneBody {
    private String OouoOGhla;
    private CustomerCollectPhoneModel CustomerCollectPhone;

    public NewCollectPhoneBody(String requestOoghli, CustomerCollectPhoneModel customerCollectPhoneModel) {
        OouoOGhla = requestOoghli;
        CustomerCollectPhone=customerCollectPhoneModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerCollectPhoneModel getCustomerCollectPhone() {
        return CustomerCollectPhone;
    }

    public void setCustomerCollectPhone(CustomerCollectPhoneModel customerCollectPhone) {
        CustomerCollectPhone = customerCollectPhone;
    }
}
