package com.koohpar.oghli.ui.showOrder;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.LakeStatusModel;
import com.koohpar.oghli.data.model.api.OrderDetailEdit;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.data.model.api.ServicesModel;
import com.koohpar.oghli.data.model.api.UpdateOrder;
import com.koohpar.oghli.data.model.api.UpdateOrderDetail;
import com.koohpar.oghli.data.model.api.requestBody.ListAttributeRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ListBaseRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ShowOrdersRequestBody;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.ui.editOrder.EditDetailRequestBody;
import com.koohpar.oghli.ui.showOrder.body.DeleteOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.EditOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.LakeOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.UpdateOrderDetailStatusRequestBody;
import com.koohpar.oghli.ui.showOrder.body.UpdateOrderStatusRequestBody;
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
    private final MutableLiveData<Boolean> editDetailMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<Boolean> editOrderMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<Boolean> updateOrderStatusMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<Boolean> updateOrderDetailStatusMutableLiveData = new SingleLiveData<>();

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
    public MutableLiveData<Boolean> getEditDetailMutableLiveData() {
        return editDetailMutableLiveData;
    }
    public MutableLiveData<Boolean> getEditOrderMutableLiveData() {
        return editOrderMutableLiveData;
    }
    public MutableLiveData<Boolean> getUpdateOrderStatusMutableLiveData() {
        return updateOrderStatusMutableLiveData;
    }
    public MutableLiveData<Boolean> getUpdateOrderDetailStatusMutableLiveData() {
        return updateOrderDetailStatusMutableLiveData;
    }


    public void editOrder(){
        getNavigator().editOrder();
    }
    public void addOrder(){
        getNavigator().addOrder();
    }
    public void result(){
        getNavigator().result();
    }
    public void sumOrder(){
        getNavigator().sumOrder();
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

    private final MutableLiveData<List<ServicesModel>> servicesModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<OrderTypeModel>> orderTypeModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib3Model>> cityModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib2Model>> jensModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib1Model>> sheklModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib4Model>> rangModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<List<ServicesModel>> getServicesModelMutableLiveData() {
        return servicesModelMutableLiveData;
    }
    public MutableLiveData<List<OrderTypeModel>> getOrderTypeModelMutableLiveData() {
        return orderTypeModelMutableLiveData;
    }
    public MutableLiveData<List<ServiceAttrib3Model>> getCityModelMutableLiveData() {
        return cityModelMutableLiveData;
    }
    public MutableLiveData<List<ServiceAttrib2Model>> getJensModelMutableLiveData() {
        return jensModelMutableLiveData;
    }
    public MutableLiveData<List<ServiceAttrib1Model>> getSheklModelMutableLiveData() {
        return sheklModelMutableLiveData;
    }
    public MutableLiveData<List<ServiceAttrib4Model>> getRangModelMutableLiveData() {
        return rangModelMutableLiveData;
    }

    public void callListService(String requestOoghli) {
        Disposable disposable = mRestManager.listServices(new ListBaseRequestBody(requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    servicesModelMutableLiveData.setValue(r);
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

    public void callTypeFactor(String requestOoghli) {
        Disposable disposable = mRestManager.listOrderType(new ListBaseRequestBody(requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    orderTypeModelMutableLiveData.setValue(r);
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

    public void callCity(String requestOoghli,String serviceId) {
        Disposable disposable = mRestManager.listCity(new ListAttributeRequestBody(requestOoghli,serviceId))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    cityModelMutableLiveData.setValue(r);
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

    public void callJens(String requestOoghli, String serviceSelected) {
        Disposable disposable = mRestManager.listJens(new ListAttributeRequestBody(requestOoghli,serviceSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    jensModelMutableLiveData.setValue(r);
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

    public void callShekl(String requestOoghli, String serviceSelected) {
        Disposable disposable = mRestManager.listShekl(new ListAttributeRequestBody(requestOoghli,serviceSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    sheklModelMutableLiveData.setValue(r);
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

    public void callRang(String requestOoghli, String serviceSelected) {
        Disposable disposable = mRestManager.listRang(new ListAttributeRequestBody(requestOoghli,serviceSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    rangModelMutableLiveData.setValue(r);
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

    public void callEditOrder(String requestOoghli, OrdersModel orderModel) {
        Disposable disposable = mRestManager.callEditOrder(new EditOrderRequestBody(requestOoghli,orderModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    editOrderMutableLiveData.setValue(r);
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

    public void callEditOrderDetail(String requestOoghli, OrderDetailEdit orderDetailEdit) {
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

    public void updateOrderStatus(String requestOoghli, UpdateOrder updateOrder) {
        Disposable disposable = mRestManager.updateOrderStatus(new UpdateOrderStatusRequestBody(updateOrder,requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    updateOrderStatusMutableLiveData.setValue(r);
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

    public void updateOrderDetailStatus(String requestOoghli, UpdateOrderDetail updateOrderDetail) {
        Disposable disposable = mRestManager.updateOrderDetailStatus(new UpdateOrderDetailStatusRequestBody(requestOoghli,updateOrderDetail))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    updateOrderDetailStatusMutableLiveData.setValue(r);
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