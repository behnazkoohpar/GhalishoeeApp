package com.koohpar.oghli.data.model.api;

public class OrderDetailModel {
    
    private String OrderDetailID;
    private String OrdersID;
    private String ServiceID;
    private String ServiceCode;
    private String ServiceName;
    private String ServiceAttrib1ID;
    private String ServiceAttrib1Name;
    private String ServiceAttrib2ID;
    private String ServiceAttrib2Name;
    private String ServiceAttrib3ID;
    private String ServiceAttrib3Name;
    private String ServiceAttrib4ID;
    private String ServiceAttrib4Name;
    private int OrderStatusID;
    private String OrderStatusName;
    private int LakeDetailStatusCode;
    private String LakeDetailStatusName;
    private float Lenght;
    private float Width;
    private float Quantity;
    private float UnitPrice;
    private float UnitPrice2;
    private boolean IsRofu;
    private float RofuPrice;
    private float DiscountPercent;
    private float DiscountPrice;
    private float _OrderPrice;
//    public float OrderPrice
//    {
//        get
//        {
//            return Quantity * UnitPrice + RofuPrice - DiscountPrice;
//        }
//        set
//        {
//            _OrderPrice = value;
//        }
//    }


    public String getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public String getOrdersID() {
        return OrdersID;
    }

    public void setOrdersID(String ordersID) {
        OrdersID = ordersID;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public String getServiceCode() {
        return ServiceCode;
    }

    public void setServiceCode(String serviceCode) {
        ServiceCode = serviceCode;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceAttrib1ID() {
        return ServiceAttrib1ID;
    }

    public void setServiceAttrib1ID(String serviceAttrib1ID) {
        ServiceAttrib1ID = serviceAttrib1ID;
    }

    public String getServiceAttrib1Name() {
        return ServiceAttrib1Name;
    }

    public void setServiceAttrib1Name(String serviceAttrib1Name) {
        ServiceAttrib1Name = serviceAttrib1Name;
    }

    public String getServiceAttrib2ID() {
        return ServiceAttrib2ID;
    }

    public void setServiceAttrib2ID(String serviceAttrib2ID) {
        ServiceAttrib2ID = serviceAttrib2ID;
    }

    public String getServiceAttrib2Name() {
        return ServiceAttrib2Name;
    }

    public void setServiceAttrib2Name(String serviceAttrib2Name) {
        ServiceAttrib2Name = serviceAttrib2Name;
    }

    public String getServiceAttrib3ID() {
        return ServiceAttrib3ID;
    }

    public void setServiceAttrib3ID(String serviceAttrib3ID) {
        ServiceAttrib3ID = serviceAttrib3ID;
    }

    public String getServiceAttrib3Name() {
        return ServiceAttrib3Name;
    }

    public void setServiceAttrib3Name(String serviceAttrib3Name) {
        ServiceAttrib3Name = serviceAttrib3Name;
    }

    public String getServiceAttrib4ID() {
        return ServiceAttrib4ID;
    }

    public void setServiceAttrib4ID(String serviceAttrib4ID) {
        ServiceAttrib4ID = serviceAttrib4ID;
    }

    public String getServiceAttrib4Name() {
        return ServiceAttrib4Name;
    }

    public void setServiceAttrib4Name(String serviceAttrib4Name) {
        ServiceAttrib4Name = serviceAttrib4Name;
    }

    public int getOrderStatusID() {
        return OrderStatusID;
    }

    public void setOrderStatusID(int orderStatusID) {
        OrderStatusID = orderStatusID;
    }

    public String getOrderStatusName() {
        return OrderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        OrderStatusName = orderStatusName;
    }

    public int getLakeDetailStatusCode() {
        return LakeDetailStatusCode;
    }

    public void setLakeDetailStatusCode(int lakeDetailStatusCode) {
        LakeDetailStatusCode = lakeDetailStatusCode;
    }

    public String getLakeDetailStatusName() {
        return LakeDetailStatusName;
    }

    public void setLakeDetailStatusName(String lakeDetailStatusName) {
        LakeDetailStatusName = lakeDetailStatusName;
    }

    public float getLenght() {
        return Lenght;
    }

    public void setLenght(float lenght) {
        Lenght = lenght;
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

    public float getUnitPrice2() {
        return UnitPrice2;
    }

    public void setUnitPrice2(float unitPrice2) {
        UnitPrice2 = unitPrice2;
    }

    public boolean isRofu() {
        return IsRofu;
    }

    public void setRofu(boolean rofu) {
        IsRofu = rofu;
    }

    public float getRofuPrice() {
        return RofuPrice;
    }

    public void setRofuPrice(float rofuPrice) {
        RofuPrice = rofuPrice;
    }

    public float getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        DiscountPercent = discountPercent;
    }

    public float getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        DiscountPrice = discountPrice;
    }

    public float get_OrderPrice() {
        return _OrderPrice;
    }

    public void set_OrderPrice(float _OrderPrice) {
        this._OrderPrice = _OrderPrice;
    }
}
