package com.koohpar.oghli.ui.login;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.data.model.api.requestBody.LoginRequestBody;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by behnaz on 11/3/17.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> implements AppConstants {

    private final MutableLiveData<UserModel> tokenResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<UserModel> getTokenResponseModelMutableLiveData() {
        return tokenResponseModelMutableLiveData;
    }

    public LoginViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    public void onCallSendTelNumber() {
        getNavigator().login();
    }

    public void onForgetPassword() {
//        getNavigator().forgetPassword();
    }

    public void onSignUp() {

//        getNavigator().signUp();
    }

    void login( String userName,String password,String ooghli ) {
        Disposable disposable = mRestManager.loginUser(new LoginRequestBody(userName, password, ooghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    tokenResponseModelMutableLiveData.setValue(r);
                    Timber.i("data login : " + r);
                    Timber.d("result response : " + r);
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in login view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

}
