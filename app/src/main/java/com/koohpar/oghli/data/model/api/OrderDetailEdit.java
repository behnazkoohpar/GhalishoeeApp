package com.koohpar.oghli.data.model.api;

public class OrderDetailEdit {
    private String ServiceAttrib1ID;// = "00000000-0000-0000-0000-000000000000";
    private String ServiceAttrib2ID;// = "00000000-0000-0000-0000-000000000000";
    private String ServiceAttrib3ID;// = "00000000-0000-0000-0000-000000000000";
    private String ServiceAttrib4ID;// = "00000000-0000-0000-0000-000000000000";
    private float Lenght;// = 0;
    private String OrderDetailID ;//= "00000000-0000-0000-0000-000000000000";
    private float Width ;//= 0;
    private float Quantity;// = 0;
    private float UnitPrice;

    private float DiscountPrice ;//= 0;
    private float DiscountPercent;// = 0;
    private float RofuPrice ;//= 0;
    private boolean IsRofu;//				= False / True
    private String RofuAttrib1ID;// = "00000000-0000-0000-0000-000000000000";
    private String RofuAttrib2ID ;//= "00000000-0000-0000-0000-000000000000";
    private String RofuDesc;// = "";
    private String OrderStatusID;


    public float getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        DiscountPrice = discountPrice;
    }

    public float getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        DiscountPercent = discountPercent;
    }

    public float getRofuPrice() {
        return RofuPrice;
    }

    public void setRofuPrice(float rofuPrice) {
        RofuPrice = rofuPrice;
    }

    public boolean isRofu() {
        return IsRofu;
    }

    public void setRofu(boolean rofu) {
        IsRofu = rofu;
    }

    public String getRofuAttrib1ID() {
        return RofuAttrib1ID;
    }

    public void setRofuAttrib1ID(String rofuAttrib1ID) {
        RofuAttrib1ID = rofuAttrib1ID;
    }

    public String getRofuAttrib2ID() {
        return RofuAttrib2ID;
    }

    public void setRofuAttrib2ID(String rofuAttrib2ID) {
        RofuAttrib2ID = rofuAttrib2ID;
    }

    public String getRofuDesc() {
        return RofuDesc;
    }

    public void setRofuDesc(String rofuDesc) {
        RofuDesc = rofuDesc;
    }

    public String getOrderStatusID() {
        return OrderStatusID;
    }

    public void setOrderStatusID(String orderStatusID) {
        OrderStatusID = orderStatusID;
    }

    public String getServiceAttrib1ID() {
        return ServiceAttrib1ID;
    }

    public void setServiceAttrib1ID(String serviceAttrib1ID) {
        ServiceAttrib1ID = serviceAttrib1ID;
    }

    public String getServiceAttrib2ID() {
        return ServiceAttrib2ID;
    }

    public void setServiceAttrib2ID(String serviceAttrib2ID) {
        ServiceAttrib2ID = serviceAttrib2ID;
    }

    public String getServiceAttrib3ID() {
        return ServiceAttrib3ID;
    }

    public void setServiceAttrib3ID(String serviceAttrib3ID) {
        ServiceAttrib3ID = serviceAttrib3ID;
    }

    public String getServiceAttrib4ID() {
        return ServiceAttrib4ID;
    }

    public void setServiceAttrib4ID(String serviceAttrib4ID) {
        ServiceAttrib4ID = serviceAttrib4ID;
    }

    public float getLenght() {
        return Lenght;
    }

    public void setLenght(float lenght) {
        Lenght = lenght;
    }

    public String getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public float getWidth() {
        return Width;
    }

    public void setWidth(float width) {
        Width = width;
    }

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }
}
