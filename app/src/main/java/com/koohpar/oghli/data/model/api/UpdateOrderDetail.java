package com.koohpar.oghli.data.model.api;

public class UpdateOrderDetail {
    public String OrderDetailID;
    public String OrderStatusID;
    public String LakeDetailStatusCode;

    public String getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public String getOrderStatusID() {
        return OrderStatusID;
    }

    public void setOrderStatusID(String orderStatusID) {
        OrderStatusID = orderStatusID;
    }

    public String getLakeDetailStatusCode() {
        return LakeDetailStatusCode;
    }

    public void setLakeDetailStatusCode(String lakeDetailStatusCode) {
        LakeDetailStatusCode = lakeDetailStatusCode;
    }
}
