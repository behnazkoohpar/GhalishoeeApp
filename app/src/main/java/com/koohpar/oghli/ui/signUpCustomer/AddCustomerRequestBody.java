package com.koohpar.oghli.ui.signUpCustomer;

import com.koohpar.oghli.data.model.api.CustomerModel;

public class AddCustomerRequestBody {
    private String OouoOGhla;
    private CustomerModel customerModel;

    public AddCustomerRequestBody(String oouoOGhla, CustomerModel customerModel) {
        OouoOGhla = oouoOGhla;
        this.customerModel = customerModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }
}
