package com.koohpar.oghli.ui.editOrder;

import com.koohpar.oghli.data.model.api.OrderDetailModel;

public class EditDetailRequestBody {
    private String OouoOGhla;
    private OrderDetailModel OrderDetail;

    public EditDetailRequestBody( OrderDetailModel orderDetail,String oouoOGhla) {
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
