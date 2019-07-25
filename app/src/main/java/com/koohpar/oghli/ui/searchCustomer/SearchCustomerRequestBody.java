package com.koohpar.oghli.ui.searchCustomer;

public class SearchCustomerRequestBody {

    private String CustPhone;
    private String OouoOGhla;

    public SearchCustomerRequestBody(String mobile,  String oouoOGhla) {
        CustPhone = mobile;
        OouoOGhla = oouoOGhla;
    }

    public String getCustPhone() {
        return CustPhone;
    }

    public void setCustPhone(String custPhone) {
        CustPhone = custPhone;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }
}
