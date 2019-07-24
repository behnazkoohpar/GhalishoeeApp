package com.koohpar.oghli.ui.showOrder;

public class ShowOrdersRequestBody {
    private String OouoOGhla;

    private String OrdersID;

    public ShowOrdersRequestBody(String oouoOGhla, String ordersID) {
        OouoOGhla = oouoOGhla;
        OrdersID = ordersID;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public String getOrdersID() {
        return OrdersID;
    }

    public void setOrdersID(String ordersID) {
        OrdersID = ordersID;
    }
}
