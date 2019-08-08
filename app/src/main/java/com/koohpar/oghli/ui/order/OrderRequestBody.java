package com.koohpar.oghli.ui.order;

import com.koohpar.oghli.data.model.api.OrdersModel;

public class OrderRequestBody {
    private String OouoOGhla;
    private OrdersModel Orders;

    public OrderRequestBody(String oouoOGhla, OrdersModel orders) {
        OouoOGhla = oouoOGhla;
        Orders = orders;
    }

    public OrdersModel getOrders() {
        return Orders;
    }

    public void setOrders(OrdersModel orders) {
        Orders = orders;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }
}
