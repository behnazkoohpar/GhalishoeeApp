package com.koohpar.oghli.ui.searchCustomer;

import android.arch.lifecycle.MutableLiveData;
import android.text.Editable;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.CustomerModel;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SearchCustomerViewModel extends BaseViewModel<SearchCustomerNavigator> implements AppConstants {

    private final MutableLiveData<List<CustomerModel>> customerModelModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<CustomerModel>> getCustomerModelMutableLiveData() {
        return customerModelModelMutableLiveData;
    }

    public SearchCustomerViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    public void onCallSearch(){
        getNavigator().onCallSearch();
    }


    public void searchCustomer( String tel,String ooghli) {
        Disposable disposable = mRestManager.searchCustomer(new SearchCustomerRequestBody(tel, ooghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    customerModelModelMutableLiveData.setValue(r);
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
