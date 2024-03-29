package com.koohpar.oghli.data.pref;

import com.koohpar.oghli.data.DataManager;

/**
 * Created by behnaz on 10/29/2017.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getUserId();
    String getServiceManId();
    String getServiceManName();

    void setUserId(String userId);
    void setServiceManId(String serviceManId);
    void setServiceManName(String serviceManName);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getSecurityKey();

    void setSecurityKey(String userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getProfilePicture();

    void setProfilePicture(String profilePicture);

    String getToken();

    void setToken(String token);

    String getTokenDate();

    void setTokenDate(String tokenDate);

    String getInvoiceNumber();

    void setInvoiceNumber(String invoiceNumber);

    String getTripleDes();

    void setTripleDes(String tripleDes);

    String getNationalCode();

    void setNationalCode(String nationalCode);

    String getLatitude();

    void setLatitude(String latitude);

    String getLongitude();

    void setLongitude(String longitude);

    String getNationalCodeClub();

    void setNationalCodeClub(String nationalCodeClub);
}
