package com.koohpar.oghli.data.model.api;

import com.google.gson.annotations.SerializedName;

public class BranchResponse {

    @SerializedName("BranchName")
    private String BranchName;
    @SerializedName("BranchID")
    private String BranchId;
    @SerializedName("BranchCode")
    private String BranchCode;

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }
}
