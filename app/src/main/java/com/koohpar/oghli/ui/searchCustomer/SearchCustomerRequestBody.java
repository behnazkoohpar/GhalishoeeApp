package com.koohpar.oghli.ui.searchCustomer;

public class SearchCustomerRequestBody {

    private String Mobile;
    private String Phone;
    private String CustomerName;
    private String OouoOGhla;

    public SearchCustomerRequestBody(String mobile, String phone, String customerName, String oouoOGhla) {
        Mobile = mobile;
        Phone = phone;
        CustomerName = customerName;
        OouoOGhla = oouoOGhla;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }
}
