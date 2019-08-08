package com.koohpar.oghli.data.model.api;

public class OrderTypeModel {
    private String FromNumber;
    private int OrderTypeCode;
    private String OrderTypeName;
    private String ToNumber;
    private boolean YearCheck;

    public OrderTypeModel(String fromNumber, int orderTypeCode, String orderTypeName, String toNumber, boolean yearCheck) {
        FromNumber = fromNumber;
        OrderTypeCode = orderTypeCode;
        OrderTypeName = orderTypeName;
        ToNumber = toNumber;
        YearCheck = yearCheck;
    }

    public String getFromNumber() {
        return FromNumber;
    }

    public void setFromNumber(String fromNumber) {
        FromNumber = fromNumber;
    }

    public int getOrderTypeCode() {
        return OrderTypeCode;
    }

    public void setOrderTypeCode(int orderTypeCode) {
        OrderTypeCode = orderTypeCode;
    }

    public String getOrderTypeName() {
        return OrderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        OrderTypeName = orderTypeName;
    }

    public String getToNumber() {
        return ToNumber;
    }

    public void setToNumber(String toNumber) {
        ToNumber = toNumber;
    }

    public boolean isYearCheck() {
        return YearCheck;
    }

    public void setYearCheck(boolean yearCheck) {
        YearCheck = yearCheck;
    }
}
