package com.koohpar.oghli.data.model.api;

public class UpdateOrder {
    public String OrdersID;
    public String OrderStatusID;
    public String CollectLakeStatusCode;
    public String ReleaseLakeStatusCode;

    public String getOrdersID() {
        return OrdersID;
    }

    public void setOrdersID(String ordersID) {
        OrdersID = ordersID;
    }

    public String getOrderStatusID() {
        return OrderStatusID;
    }

    public void setOrderStatusID(String orderStatusID) {
        OrderStatusID = orderStatusID;
    }

    public String getCollectLakeStatusCode() {
        return CollectLakeStatusCode;
    }

    public void setCollectLakeStatusCode(String collectLakeStatusCode) {
        CollectLakeStatusCode = collectLakeStatusCode;
    }

    public String getReleaseLakeStatusCode() {
        return ReleaseLakeStatusCode;
    }

    public void setReleaseLakeStatusCode(String releaseLakeStatusCode) {
        ReleaseLakeStatusCode = releaseLakeStatusCode;
    }
}
