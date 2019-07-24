package com.koohpar.oghli.ui.listSum;

public class ListSumRequestBody {
    private String ServiceManID;

    private String Jam_Date;

    private String OouoOGhla;


    public ListSumRequestBody(String serviceManID, String jam_Date, String oouoOGhla) {
        ServiceManID = serviceManID;
        Jam_Date = jam_Date;
        OouoOGhla = oouoOGhla;
    }

    public String getServiceManID() {
        return ServiceManID;
    }

    public void setServiceManID(String serviceManID) {
        ServiceManID = serviceManID;
    }

    public String getJam_Date() {
        return Jam_Date;
    }

    public void setJam_Date(String jam_Date) {
        Jam_Date = jam_Date;
    }

    public String getOouoOGhla() {
        return OouoOGhla;
    }

    public void setOouoOGhla(String oouoOGhla) {
        OouoOGhla = oouoOGhla;
    }
}
