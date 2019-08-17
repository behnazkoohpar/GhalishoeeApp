package com.koohpar.oghli.ui.showOrder.body;

public class LakeOrderRequestBody {
    private String OouoOGhla;
    private int type;


    public LakeOrderRequestBody(String oouoOGhla, int type) {
        OouoOGhla = oouoOGhla;
        this.type = type;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
