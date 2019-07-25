package com.koohpar.oghli.ui.listCustomer;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;

public class ListCustomerViewModel extends BaseViewModel<ListCustomerNavigator> implements AppConstants {

    private final MutableLiveData<BaseResponse<TokenResponse>> tokenResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<BaseResponse<TokenResponse>> getTokenResponseModelMutableLiveData() {
        return tokenResponseModelMutableLiveData;
    }

    public ListCustomerViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }


    public void onCreateNewCustomer(){
        getNavigator().onCreateNewCustomer();
    }
}
