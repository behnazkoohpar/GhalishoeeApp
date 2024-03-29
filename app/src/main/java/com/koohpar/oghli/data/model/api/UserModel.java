package com.koohpar.oghli.data.model.api;

public class UserModel {
   
    private String ServiceManID;
    private String ServiceManName;
    private String UserId;
    private String uName;
    private String UserName;
    private String Password;
    private byte[] UserImage;

    public String getServiceManID() {
        return ServiceManID;
    }

    public void setServiceManID(String serviceManID) {
        ServiceManID = serviceManID;
    }

    public String getServiceManName() {
        return ServiceManName;
    }

    public void setServiceManName(String serviceManName) {
        ServiceManName = serviceManName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
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

    public byte[] getUserImage() {
        return UserImage;
    }

    public void setUserImage(byte[] userImage) {
        UserImage = userImage;
    }
}
