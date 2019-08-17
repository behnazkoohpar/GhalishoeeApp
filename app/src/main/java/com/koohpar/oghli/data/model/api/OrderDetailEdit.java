package com.koohpar.oghli.data.model.api;

public class OrderDetailEdit {
    private String ServiceAttrib1ID;
    private String ServiceAttrib2ID;
    private String ServiceAttrib3ID;
    private String ServiceAttrib4ID;
    private float Lenght;
    private String OrderDetailID;
    private float Width;
    private float Quantity;
    private float UnitPrice;

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
