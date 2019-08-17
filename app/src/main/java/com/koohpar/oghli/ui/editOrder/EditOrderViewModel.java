package com.koohpar.oghli.ui.editOrder;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.OrderDetailEdit;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.requestBody.ShowOrdersRequestBody;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class EditOrderViewModel extends BaseViewModel<EditOrderNavigator> implements AppConstants {

    public EditOrderViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    private final MutableLiveData<List<OrderDetailModel>> ordersModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<Boolean> editDetailMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<OrderDetailModel>> getOrdersModelMutableLiveData() {
        return ordersModelMutableLiveData;
    }

    public MutableLiveData<Boolean> getEditDetailMutableLiveData() {
        return editDetailMutableLiveData;
    }

    public void addOrder() {
        getNavigator().addOrder();
    }

    public void closed() {
        getNavigator().closed();
    }

    public void callEditOrder(String ordersID, String ooghli) {
        Disposable disposable = mRestManager.showOrdersDetail(new ShowOrdersRequestBody(ooghli, ordersID))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    ordersModelMutableLiveData.setValue(r);
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

    public void callEditDetail(OrderDetailEdit orderDetailEdit, String requestOoghli) {
        Disposable disposable = mRestManager.editDetail(new EditDetailRequestBody(orderDetailEdit,requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    editDetailMutableLiveData.setValue(r);
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