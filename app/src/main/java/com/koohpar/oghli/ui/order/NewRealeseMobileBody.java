package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.CustomerReleaseMobileModel;

public class NewRealeseMobileBody  {
    private String OouoOGhla;
    private CustomerReleaseMobileModel CustomerReleaseMobile;

    public NewRealeseMobileBody(String requestOoghli, CustomerReleaseMobileModel customerReleaseMobileModel) {
        OouoOGhla = requestOoghli;
        CustomerReleaseMobile=customerReleaseMobileModel;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public CustomerReleaseMobileModel getCustomerReleaseMobile() {
        return CustomerReleaseMobile;
    }

    public void setCustomerReleaseMobile(CustomerReleaseMobileModel customerReleaseMobile) {
        CustomerReleaseMobile = customerReleaseMobile;
    }
}
