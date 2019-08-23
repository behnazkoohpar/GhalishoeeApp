package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.CustomerReleasePhoneModel;

public class NewRealesePhoneBody  {
    private String OouoOGhla;
    private CustomerReleasePhoneModel CustomerReleasePhone;

    public NewRealesePhoneBody(String requestOoghli, CustomerReleasePhoneModel customerReleasePhoneModel) {
        CustomerReleasePhone=customerReleasePhoneModel;
        OouoOGhla  =requestOoghli;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerReleasePhoneModel getCustomerReleasePhone() {
        return CustomerReleasePhone;
    }

    public void setCustomerReleasePhone(CustomerReleasePhoneModel customerReleasePhone) {
        CustomerReleasePhone = customerReleasePhone;
    }
}
