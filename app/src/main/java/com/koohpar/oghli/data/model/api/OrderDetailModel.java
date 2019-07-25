package com.koohpar.oghli.data.model.api;

public class OrderDetailModel {


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
    private String OrderDetailID;
    private float Width;
    private float Quantity;
    private String RofuAttrib1ID;
    private String RofuAttrib1Name;
    private String RofuAttrib2ID;
    private String RofuAttrib2Name;
    private String RofuDesc;
    private float UnitPrice;
    private float UnitPrice2;
    private boolean IsRofu;
    private float RofuPrice;
    private float DiscountPercent;
    private float DiscountPrice;
    private float _OrderPrice;

    public float getOrderPrice() {
        return Quantity * UnitPrice + RofuPrice - DiscountPrice;
    }


    public OrderDetailModel(String ordersID, String serviceID, String serviceCode, String serviceName, String serviceAttrib1ID, String serviceAttrib1Name, String serviceAttrib2ID, String serviceAttrib2Name, String serviceAttrib3ID, String serviceAttrib3Name, String serviceAttrib4ID, String serviceAttrib4Name, int orderStatusID, String orderStatusName, int lakeDetailStatusCode, String lakeDetailStatusName, float lenght, String orderDetailID, float width, float quantity, String rofuAttrib1ID, String rofuAttrib1Name, String rofuAttrib2ID, String rofuAttrib2Name, String rofuDesc, float unitPrice, float unitPrice2, boolean isRofu, float rofuPrice, float discountPercent, float discountPrice, float _OrderPrice) {
        OrdersID = ordersID;
        ServiceID = serviceID;
        ServiceCode = serviceCode;
        ServiceName = serviceName;
        ServiceAttrib1ID = serviceAttrib1ID;
        ServiceAttrib1Name = serviceAttrib1Name;
        ServiceAttrib2ID = serviceAttrib2ID;
        ServiceAttrib2Name = serviceAttrib2Name;
        ServiceAttrib3ID = serviceAttrib3ID;
        ServiceAttrib3Name = serviceAttrib3Name;
        ServiceAttrib4ID = serviceAttrib4ID;
        ServiceAttrib4Name = serviceAttrib4Name;
        OrderStatusID = orderStatusID;
        OrderStatusName = orderStatusName;
        LakeDetailStatusCode = lakeDetailStatusCode;
        LakeDetailStatusName = lakeDetailStatusName;
        Lenght = lenght;
        OrderDetailID = orderDetailID;
        Width = width;
        Quantity = quantity;
        RofuAttrib1ID = rofuAttrib1ID;
        RofuAttrib1Name = rofuAttrib1Name;
        RofuAttrib2ID = rofuAttrib2ID;
        RofuAttrib2Name = rofuAttrib2Name;
        RofuDesc = rofuDesc;
        UnitPrice = unitPrice;
        UnitPrice2 = unitPrice2;
        IsRofu = isRofu;
        RofuPrice = rofuPrice;
        DiscountPercent = discountPercent;
        DiscountPrice = discountPrice;
        this._OrderPrice = _OrderPrice;
    }

    public String getRofuAttrib1ID() {
        return RofuAttrib1ID;
    }

    public void setRofuAttrib1ID(String rofuAttrib1ID) {
        RofuAttrib1ID = rofuAttrib1ID;
    }

    public String getRofuAttrib1Name() {
        return RofuAttrib1Name;
    }

    public void setRofuAttrib1Name(String rofuAttrib1Name) {
        RofuAttrib1Name = rofuAttrib1Name;
    }

    public String getRofuAttrib2ID() {
        return RofuAttrib2ID;
    }

    public void setRofuAttrib2ID(String rofuAttrib2ID) {
        RofuAttrib2ID = rofuAttrib2ID;
    }

    public String getRofuAttrib2Name() {
        return RofuAttrib2Name;
    }

    public void setRofuAttrib2Name(String rofuAttrib2Name) {
        RofuAttrib2Name = rofuAttrib2Name;
    }

    public String getRofuDesc() {
        return RofuDesc;
    }

    public void setRofuDesc(String rofuDesc) {
        RofuDesc = rofuDesc;
    }

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

    public void set_OrderPrice(float _OrderPrice) {
        this._OrderPrice = _OrderPrice;
    }
}
