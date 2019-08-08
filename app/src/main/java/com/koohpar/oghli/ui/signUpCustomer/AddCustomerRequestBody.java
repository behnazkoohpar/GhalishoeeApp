package com.koohpar.oghli.ui.signUpCustomer;

import com.koohpar.oghli.data.model.api.Customer;

public class AddCustomerRequestBody {
    private String OouoOGhla;
    private Customer Customer;

    public AddCustomerRequestBody(String oouoOGhla, Customer customerModel) {
        OouoOGhla = oouoOGhla;
        this.Customer = customerModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        this.Customer = customer;
    }
}
