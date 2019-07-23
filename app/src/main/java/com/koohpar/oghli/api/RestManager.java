package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.ui.login.LoginRequestBody;

import io.reactivex.Observable;

public interface RestManager {

    Observable<UserModel> loginUser(LoginRequestBody loginRequestBody);

}
