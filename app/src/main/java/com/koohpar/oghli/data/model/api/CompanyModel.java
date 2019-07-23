package com.koohpar.oghli.data.model.api;

public class CompanyModel {

    private String CompanyId;
    private String CompanyName;
    private String Phone;
    private String Fax;
    private String Manager;
    private String Address;
    private byte[] Logo;
    private String LogoImg;
    private String CompDesc;
    private int QuantityDecimal;
    private String ServerIP;
    private String PortNo;
    private String UserName;
    private String Password;

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public byte[] getLogo() {
        return Logo;
    }

    public void setLogo(byte[] logo) {
        Logo = logo;
    }

    public String getLogoImg() {
        return LogoImg;
    }

    public void setLogoImg(String logoImg) {
        LogoImg = logoImg;
    }

    public String getCompDesc() {
        return CompDesc;
    }

    public void setCompDesc(String compDesc) {
        CompDesc = compDesc;
    }

    public int getQuantityDecimal() {
        return QuantityDecimal;
    }

    public void setQuantityDecimal(int quantityDecimal) {
        QuantityDecimal = quantityDecimal;
    }

    public String getServerIP() {
        return ServerIP;
    }

    public void setServerIP(String serverIP) {
        ServerIP = serverIP;
    }

    public String getPortNo() {
        return PortNo;
    }

    public void setPortNo(String portNo) {
        PortNo = portNo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
