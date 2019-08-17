package com.koohpar.oghli.ui.showOrder.body;

import com.koohpar.oghli.data.model.api.UpdateOrder;
import com.koohpar.oghli.data.model.api.UpdateOrderDetail;

public class UpdateOrderDetailStatusRequestBody {
    private String OouoOGhla;
    private UpdateOrderDetail OrderDetail;

    public UpdateOrderDetailStatusRequestBody(String oouoOGhla, UpdateOrderDetail orderDetail) {
        OouoOGhla = oouoOGhla;
        OrderDetail = orderDetail;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public UpdateOrderDetail getOrderDetail() {
        return OrderDetail;
    }

    public void setOrderDetail(UpdateOrderDetail orderDetail) {
        OrderDetail = orderDetail;
    }
}
