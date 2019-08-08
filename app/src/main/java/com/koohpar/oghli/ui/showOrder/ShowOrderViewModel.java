package com.koohpar.oghli.ui.showOrder;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.LakeStatusModel;
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

public class ShowOrderViewModel  extends BaseViewModel<ShowOrderNavigator> implements AppConstants {

    public ShowOrderViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    private final MutableLiveData<List<OrderDetailModel>> ordersModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<LakeStatusModel>> adamSumMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<LakeStatusModel>> adamPakhshMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<Boolean> deleteOrderDetailMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<OrderDetailModel>> getOrdersModelMutableLiveData() {
        return ordersModelMutableLiveData;
    }
    public MutableLiveData<List<LakeStatusModel>> getAdamSumMutableLiveData() {
        return adamSumMutableLiveData;
    }
    public MutableLiveData<List<LakeStatusModel>> getAdamPakhshMutableLiveData() {
        return adamPakhshMutableLiveData;
    }
    public MutableLiveData<Boolean> getDeleteOrderDetailMutableLiveData() {
        return deleteOrderDetailMutableLiveData;
    }


    public void addOrder(){
        getNavigator().addOrder();
    }
    public void result(){
        getNavigator().result();
    }

    public void callShowOrder(String ordersID, String ooghli) {
        Disposable disposable = mRestManager.showOrdersDetail(new ShowOrdersRequestBody(ooghli,ordersID))
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

    public void callAdamSum(int type, String requestOoghli) {
        Disposable disposable = mRestManager.lakeOrder(new LakeOrderRequestBody(requestOoghli,type))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    adamSumMutableLiveData.setValue(r);
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

    public void callAdamPakhsh(int type, String requestOoghli) {
        Disposable disposable = mRestManager.lakeOrder(new LakeOrderRequestBody(requestOoghli,type))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    adamPakhshMutableLiveData.setValue(r);
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

    public void callDeleteOrderDetail(String requestOoghli, OrderDetailModel orderDetailModel) {
        Disposable disposable = mRestManager.deleteOrderDetail(new DeleteOrderRequestBody(requestOoghli,orderDetailModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    deleteOrderDetailMutableLiveData.setValue(r);
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