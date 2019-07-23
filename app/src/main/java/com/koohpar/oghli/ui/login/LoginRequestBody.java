package com.koohpar.oghli.ui.login;

public class LoginRequestBody {
    private String UserName;

    private String Password;

    private String OouoOGhla;

    public LoginRequestBody(String userName, String password, String oouoOGhla) {
        UserName = userName;
        OouoOGhla = oouoOGhla;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }
}
