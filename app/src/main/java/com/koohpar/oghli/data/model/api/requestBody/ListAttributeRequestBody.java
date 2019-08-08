package com.koohpar.oghli.data.model.api.requestBody;

public class ListAttributeRequestBody {
    private String OouoOGhla;
    private String ServicesID;

    public ListAttributeRequestBody(String oouoOGhla, String servicesID) {
        OouoOGhla = oouoOGhla;
        ServicesID = servicesID;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }

    public String getServicesID() {
        return ServicesID;
    }

    public void setServicesID(String servicesID) {
        ServicesID = servicesID;
    }
}
