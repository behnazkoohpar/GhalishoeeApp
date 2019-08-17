package com.koohpar.oghli.ui.editOrder;

import com.koohpar.oghli.data.model.api.OrderDetailEdit;

public class EditDetailRequestBody {
    private String OouoOGhla;
    private OrderDetailEdit OrderDetail;

    public EditDetailRequestBody(OrderDetailEdit orderDetail, String oouoOGhla) {
        OouoOGhla = oouoOGhla;
        OrderDetail = orderDetail;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public OrderDetailEdit getOrderDetail() {
        return OrderDetail;
    }

    public void setOrderDetail(OrderDetailEdit orderDetail) {
        OrderDetail = orderDetail;
    }
}
