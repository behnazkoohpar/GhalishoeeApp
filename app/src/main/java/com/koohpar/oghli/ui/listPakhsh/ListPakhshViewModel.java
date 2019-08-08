package com.koohpar.oghli.ui.listPakhsh;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.data.model.api.requestBody.ListSumRequestBody;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class ListPakhshViewModel extends BaseViewModel<ListPakhshNavigator> implements AppConstants {

    private final MutableLiveData<List<OrderMissionDetailModel>> orderMissionDetailModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<OrderMissionDetailModel>> getOrderMissionDetailModelMutableLiveData() {
        return orderMissionDetailModelMutableLiveData;
    }

    public ListPakhshViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }
    public void search(){
        getNavigator().callListPakhsh();
    }

    public void openCalendar(){
        getNavigator().openFromDateCalendar();
    }

    public void callListPakhsh(String serviceManID, String jamDate, String ooghli) {
        Disposable disposable = mRestManager.listPakhsh(new ListSumRequestBody(serviceManID, jamDate, ooghli))
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
}
