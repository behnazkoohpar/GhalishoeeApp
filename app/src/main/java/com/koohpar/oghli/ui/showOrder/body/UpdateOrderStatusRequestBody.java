package com.koohpar.oghli.ui.showOrder.body;

import com.koohpar.oghli.data.model.api.UpdateOrder;

public class UpdateOrderStatusRequestBody {

    private String OouoOGhla;
    private UpdateOrder Order;

    public UpdateOrderStatusRequestBody( UpdateOrder order,String oouoOGhla) {
        OouoOGhla = oouoOGhla;
        Order = order;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public UpdateOrder getOrder() {
        return Order;
    }

    public void setOrder(UpdateOrder order) {
        Order = order;
    }
}
