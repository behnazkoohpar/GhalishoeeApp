package com.koohpar.oghli.ui.showOrder.body;

import com.koohpar.oghli.data.model.api.OrdersModel;

public class EditOrderRequestBody {
    private String OouoOGhla;
    private OrdersModel Orders;

    public EditOrderRequestBody(String oouoOGhla, OrdersModel ordersModel) {
        OouoOGhla = oouoOGhla;
        Orders = ordersModel;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public com.koohpar.oghli.data.model.api.OrdersModel getOrdersModel() {
        return Orders;
    }

    public void setOrdersModel(com.koohpar.oghli.data.model.api.OrdersModel ordersModel) {
        Orders = ordersModel;
    }
}
