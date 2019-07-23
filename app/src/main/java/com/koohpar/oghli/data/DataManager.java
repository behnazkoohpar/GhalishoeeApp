package com.koohpar.oghli.data;

import com.koohpar.oghli.data.pref.PreferencesHelper;

/**
 * Created by behnaz on 10/29/2017.
 */

public interface DataManager extends PreferencesHelper {

    void setUserAsLoggedOut();
    void updateUserInfo(
            LoggedInMode loggedInMode,
            String userId,
            String vUserName,
            String vPassword,
            String vName,
            byte[] image);

    enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1);
        private final int mType;
        LoggedInMode(int type) {
            mType = type;
        }
        public int getType() {
            return mType;
        }
    }

}
