package com.koohpar.oghli.ui.listSum;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.ServiceManModel;
import com.koohpar.oghli.data.model.api.requestBody.ListBaseRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ListSumRequestBody;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class ListSumViewModel extends BaseViewModel<ListSumNavigator> implements AppConstants {

    private final MutableLiveData<List<OrderMissionDetailModel>> orderMissionDetailModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceManModel>> listServiceManModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<OrderMissionDetailModel>> getOrderMissionDetailModelMutableLiveData() {
        return orderMissionDetailModelMutableLiveData;
    }public MutableLiveData<List<ServiceManModel>> getListServiceManModelMutableLiveData() {
        return listServiceManModelMutableLiveData;
    }

    public void search(){
        getNavigator().callSearch();
    }
    public void openCalendar(){
        getNavigator().openFromDateCalendar();
    }

    public ListSumViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }


    public void callListSum(String serviceManID, String jamDate, String ooghli) {
        Disposable disposable = mRestManager.listJam(new ListSumRequestBody(serviceManID, jamDate, ooghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    orderMissionDetailModelMutableLiveData.setValue(r);
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

    public void callListServiceMan(String requestOoghli) {
        Disposable disposable = mRestManager.listServiceMan(new ListBaseRequestBody(requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    listServiceManModelMutableLiveData.setValue(r);
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
