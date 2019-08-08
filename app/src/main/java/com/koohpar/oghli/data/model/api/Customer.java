package com.koohpar.oghli.data.model.api;

public class Customer {

    private String CustomerID;
    private String CustomerGroupID;
    private String CustomerGroupName;
    private String CustNo;
    private String PreName;
    private String CustName;
    private String Address;
    private String Phone;
    private String Mobile;
    private String FaxNo;
    private String BirthDate;
    private String Status;
    private boolean BlackList;
    private int CustSex;
    private byte SaleStatus;
    private String EshterakNo;
    private float Credit;
    private String CompanyName;
    private String EconomNo;
    private String RegistNo;
    private String PostCode;
    private String ProvinceID;
    private String ProvinceName;
    private String CityID;
    private String CityName;
    private String Email;
    private String WebSite;
    private String CustDesc;
    private String CityTelCode;
    private float CreditChkd;
    private String Semat;
    private int UserCount;
    private byte CallStatus;
    private byte Ranking;
    private boolean CallWithEmailSend;
    private boolean CallWithTel;
    private boolean CallWithFax;
    private boolean CallWithLetter;
    private String CrmIndustryID;
    private String CrmIndustryTitle;
    private byte CustTypeCode;
    private String _CustTypeName;

    public String getCustTypeName() {
        return CustTypeCode == 1 ? "مشتری بالقوه" : "مشتری بالفعل";

    }

    private String CreatedBy;
    private String CreatedDate;
    private String LastUpdatedBy;
    private String LastUpdatedDate;
    private String KnowModeID;
    private String KnowModeTitle;
    private String CampainID;
    private String CampainName;
    private String uID;
    private String UserName;
    private String NationalID;
    private String EshterakTypeID;
    private String EshterakTypeName;
    private String RouteID;
    private String RouteName;
    private String GeograficalID;
    private String GeographicalName;
    private int SentStatusCode;
    private String BranchID;
    private String RegisterDate;
    private String _RegisterDateTime;
    //    public String RegisterDateTime
//    {
//        get { return FarsiLibrary.Utils.PersianDateConverter.ToPersianDate(RegisterDate).ToString(); }
//        set { _RegisterDateTime = value; }
//    }
    private int VisitPriod;
    private String CollectMobile;
    private String CollectPhone;
    private String CollectAddress;
    private String ReleaseMobile;
    private String ReleasePhone;
    private String ReleaseAddress;

//    class CustomerToMapModel : Customer
//    private String CustomerToMapId;
//    private String Lat;
//    private String Lng;


    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerGroupID() {
        return CustomerGroupID;
    }

    public void setCustomerGroupID(String customerGroupID) {
        CustomerGroupID = customerGroupID;
    }

    public String getCustomerGroupName() {
        return CustomerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        CustomerGroupName = customerGroupName;
    }

    public String getCustNo() {
        return CustNo;
    }

    public void setCustNo(String custNo) {
        CustNo = custNo;
    }

    public String getPreName() {
        return PreName;
    }

    public void setPreName(String preName) {
        PreName = preName;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getFaxNo() {
        return FaxNo;
    }

    public void setFaxNo(String faxNo) {
        FaxNo = faxNo;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public boolean isBlackList() {
        return BlackList;
    }

    public void setBlackList(boolean blackList) {
        BlackList = blackList;
    }

    public int getCustSex() {
        return CustSex;
    }

    public void setCustSex(int custSex) {
        CustSex = custSex;
    }

    public byte getSaleStatus() {
        return SaleStatus;
    }

    public void setSaleStatus(byte saleStatus) {
        SaleStatus = saleStatus;
    }

    public String getEshterakNo() {
        return EshterakNo;
    }

    public void setEshterakNo(String eshterakNo) {
        EshterakNo = eshterakNo;
    }

    public float getCredit() {
        return Credit;
    }

    public void setCredit(float credit) {
        Credit = credit;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getEconomNo() {
        return EconomNo;
    }

    public void setEconomNo(String economNo) {
        EconomNo = economNo;
    }

    public String getRegistNo() {
        return RegistNo;
    }

    public void setRegistNo(String registNo) {
        RegistNo = registNo;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
    }

    public String getCustDesc() {
        return CustDesc;
    }

    public void setCustDesc(String custDesc) {
        CustDesc = custDesc;
    }

    public String getCityTelCode() {
        return CityTelCode;
    }

    public void setCityTelCode(String cityTelCode) {
        CityTelCode = cityTelCode;
    }

    public float getCreditChkd() {
        return CreditChkd;
    }

    public void setCreditChkd(float creditChkd) {
        CreditChkd = creditChkd;
    }

    public String getSemat() {
        return Semat;
    }

    public void setSemat(String semat) {
        Semat = semat;
    }

    public int getUserCount() {
        return UserCount;
    }

    public void setUserCount(int userCount) {
        UserCount = userCount;
    }

    public byte getCallStatus() {
        return CallStatus;
    }

    public void setCallStatus(byte callStatus) {
        CallStatus = callStatus;
    }

    public byte getRanking() {
        return Ranking;
    }

    public void setRanking(byte ranking) {
        Ranking = ranking;
    }

    public boolean isCallWithEmailSend() {
        return CallWithEmailSend;
    }

    public void setCallWithEmailSend(boolean callWithEmailSend) {
        CallWithEmailSend = callWithEmailSend;
    }

    public boolean isCallWithTel() {
        return CallWithTel;
    }

    public void setCallWithTel(boolean callWithTel) {
        CallWithTel = callWithTel;
    }

    public boolean isCallWithFax() {
        return CallWithFax;
    }

    public void setCallWithFax(boolean callWithFax) {
        CallWithFax = callWithFax;
    }

    public boolean isCallWithLetter() {
        return CallWithLetter;
    }

    public void setCallWithLetter(boolean callWithLetter) {
        CallWithLetter = callWithLetter;
    }

    public String getCrmIndustryID() {
        return CrmIndustryID;
    }

    public void setCrmIndustryID(String crmIndustryID) {
        CrmIndustryID = crmIndustryID;
    }

    public String getCrmIndustryTitle() {
        return CrmIndustryTitle;
    }

    public void setCrmIndustryTitle(String crmIndustryTitle) {
        CrmIndustryTitle = crmIndustryTitle;
    }

    public byte getCustTypeCode() {
        return CustTypeCode;
    }

    public void setCustTypeCode(byte custTypeCode) {
        CustTypeCode = custTypeCode;
    }

    public void set_CustTypeName(String _CustTypeName) {
        this._CustTypeName = _CustTypeName;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return LastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        LastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedDate() {
        return LastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        LastUpdatedDate = lastUpdatedDate;
    }

    public String getKnowModeID() {
        return KnowModeID;
    }

    public void setKnowModeID(String knowModeID) {
        KnowModeID = knowModeID;
    }

    public String getKnowModeTitle() {
        return KnowModeTitle;
    }

    public void setKnowModeTitle(String knowModeTitle) {
        KnowModeTitle = knowModeTitle;
    }

    public String getCampainID() {
        return CampainID;
    }

    public void setCampainID(String campainID) {
        CampainID = campainID;
    }

    public String getCampainName() {
        return CampainName;
    }

    public void setCampainName(String campainName) {
        CampainName = campainName;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }

    public String getEshterakTypeID() {
        return EshterakTypeID;
    }

    public void setEshterakTypeID(String eshterakTypeID) {
        EshterakTypeID = eshterakTypeID;
    }

    public String getEshterakTypeName() {
        return EshterakTypeName;
    }

    public void setEshterakTypeName(String eshterakTypeName) {
        EshterakTypeName = eshterakTypeName;
    }

    public String getRouteID() {
        return RouteID;
    }

    public void setRouteID(String routeID) {
        RouteID = routeID;
    }

    public String getRouteName() {
        return RouteName;
    }

    public void setRouteName(String routeName) {
        RouteName = routeName;
    }

    public String getGeograficalID() {
        return GeograficalID;
    }

    public void setGeograficalID(String geograficalID) {
        GeograficalID = geograficalID;
    }

    public String getGeographicalName() {
        return GeographicalName;
    }

    public void setGeographicalName(String geographicalName) {
        GeographicalName = geographicalName;
    }

    public int getSentStatusCode() {
        return SentStatusCode;
    }

    public void setSentStatusCode(int sentStatusCode) {
        SentStatusCode = sentStatusCode;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public String get_RegisterDateTime() {
        return _RegisterDateTime;
    }

    public void set_RegisterDateTime(String _RegisterDateTime) {
        this._RegisterDateTime = _RegisterDateTime;
    }

    public int getVisitPriod() {
        return VisitPriod;
    }

    public void setVisitPriod(int visitPriod) {
        VisitPriod = visitPriod;
    }

    public String getCollectMobile() {
        return CollectMobile;
    }

    public void setCollectMobile(String collectMobile) {
        CollectMobile = collectMobile;
    }

    public String getCollectPhone() {
        return CollectPhone;
    }

    public void setCollectPhone(String collectPhone) {
        CollectPhone = collectPhone;
    }

    public String getCollectAddress() {
        return CollectAddress;
    }

    public void setCollectAddress(String collectAddress) {
        CollectAddress = collectAddress;
    }

    public String getReleaseMobile() {
        return ReleaseMobile;
    }

    public void setReleaseMobile(String releaseMobile) {
        ReleaseMobile = releaseMobile;
    }

    public String getReleasePhone() {
        return ReleasePhone;
    }

    public void setReleasePhone(String releasePhone) {
        ReleasePhone = releasePhone;
    }

    public String getReleaseAddress() {
        return ReleaseAddress;
    }

    public void setReleaseAddress(String releaseAddress) {
        ReleaseAddress = releaseAddress;
    }
}
