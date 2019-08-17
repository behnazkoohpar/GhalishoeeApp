package com.koohpar.oghli.ui.showOrder.body;

import com.koohpar.oghli.data.model.api.OrderDetailModel;

public class DeleteOrderRequestBody {
    private String OouoOGhla;
    private OrderDetailModel OrderDetail;

    public DeleteOrderRequestBody(String oouoOGhla, OrderDetailModel orderDetail) {
        OouoOGhla = oouoOGhla;
        OrderDetail = orderDetail;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public OrderDetailModel getOrderDetail() {
        return OrderDetail;
    }

    public void setOrderDetail(OrderDetailModel orderDetail) {
        OrderDetail = orderDetail;
    }
}
