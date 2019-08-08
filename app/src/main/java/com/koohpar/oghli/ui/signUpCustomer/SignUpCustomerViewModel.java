package com.koohpar.oghli.ui.signUpCustomer;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.BranchResponse;
import com.koohpar.oghli.data.model.api.Customer;
import com.koohpar.oghli.data.model.api.MantagheResponse;
import com.koohpar.oghli.data.model.api.requestBody.ListBaseRequestBody;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SignUpCustomerViewModel extends BaseViewModel<SignUpCustomerNavigator> implements AppConstants {

    private final MutableLiveData<String> addCustomerMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<String> getAddCustomerMutableLiveData() {
        return addCustomerMutableLiveData;
    }

    private final MutableLiveData<List<BranchResponse>> branchModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<BranchResponse>> getBranchModelMutableLiveData() {
        return branchModelMutableLiveData;
    }

    private final MutableLiveData<List<MantagheResponse>> mantagheModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<MantagheResponse>> getMantagheModelMutableLiveData() {
        return mantagheModelMutableLiveData;
    }

    public void addCustomer(){
        getNavigator().addCustomer();
    }

    public SignUpCustomerViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    public void callListMantaghe(String requestOoghli) {
        Disposable disposable = mRestManager.listMantaghe(new ListBaseRequestBody(requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    mantagheModelMutableLiveData.setValue(r);
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

    public void callListBranch(String requestOoghli) {
        Disposable disposable = mRestManager.listBranch(new ListBaseRequestBody(requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    branchModelMutableLiveData.setValue(r);
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

    public void callAddCustomer(String requestOoghli, Customer customerModel) {
        Disposable disposable = mRestManager.addCustomer(new AddCustomerRequestBody(requestOoghli,customerModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    addCustomerMutableLiveData.setValue(r);
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

