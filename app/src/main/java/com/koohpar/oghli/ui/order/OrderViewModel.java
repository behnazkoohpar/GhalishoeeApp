package com.koohpar.oghli.ui.order;

import android.arch.lifecycle.MutableLiveData;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.CustomerCollectAddressModel;
import com.koohpar.oghli.data.model.api.CustomerCollectMobileModel;
import com.koohpar.oghli.data.model.api.CustomerCollectPhoneModel;
import com.koohpar.oghli.data.model.api.CustomerReleaseAddressModel;
import com.koohpar.oghli.data.model.api.CustomerReleaseMobileModel;
import com.koohpar.oghli.data.model.api.CustomerReleasePhoneModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.RofuAttribModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.data.model.api.ServicesModel;
import com.koohpar.oghli.data.model.api.requestBody.ListAttributeRequestBody;
import com.koohpar.oghli.di.module.RxRetrofitErrorConsumer;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.data.model.api.requestBody.ListBaseRequestBody;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class OrderViewModel extends BaseViewModel<OrderNavigator> implements AppConstants {

    private final MutableLiveData<List<ServicesModel>> servicesModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<OrderTypeModel>> orderTypeModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib3Model>> cityModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib2Model>> jensModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib1Model>> sheklModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<ServiceAttrib4Model>> rangModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<String> insertOrderMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<List<RofuAttribModel>> rofuModelMutableLiveData = new SingleLiveData<>();

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
    public MutableLiveData<String> getInsertOrderMutableLiveData() {
        return insertOrderMutableLiveData;
    }
    public MutableLiveData<List<RofuAttribModel>> getRofuModelMutableLiveData() {
        return rofuModelMutableLiveData;
    }

    public OrderViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }


    public void addOrder(){
//        getNavigator().editOrder();
        getNavigator().addOrder();
    }
    public void addInfo(){
//        getNavigator().editOrder();
        getNavigator().addInfo();
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

    public void addNewOrder(String requestOoghli, OrdersModel ordersModel) {
        Disposable disposable = mRestManager.insertOrder(new OrderRequestBody(requestOoghli,ordersModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void addNewCollectAddress(String requestOoghli, CustomerCollectAddressModel customerCollectAddressModel) {
        Disposable disposable = mRestManager.insertNewCollectAddress(new NewCollectAddressBody(requestOoghli,customerCollectAddressModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void addNewCollectMobile(String requestOoghli, CustomerCollectMobileModel customerCollectMobileModel) {
        Disposable disposable = mRestManager.insertNewCollectMobile(new NewCollectMobileBody(requestOoghli,customerCollectMobileModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void addNewCollectPhone(String requestOoghli, CustomerCollectPhoneModel customerCollectPhoneModel) {
        Disposable disposable = mRestManager.insertNewCollectPhone(new NewCollectPhoneBody(requestOoghli,customerCollectPhoneModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void addNewRealeseAddress(String requestOoghli, CustomerReleaseAddressModel customerReleaseAddressModel) {
        Disposable disposable = mRestManager.insertNewRealeseAddress(new NewRealeseAddressBody(requestOoghli,customerReleaseAddressModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void addNewRealeseMobile(String requestOoghli, CustomerReleaseMobileModel customerReleaseMobileModel) {

        Disposable disposable = mRestManager.insertNewRealeseMobile(new NewRealeseMobileBody(requestOoghli,customerReleaseMobileModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void addNewRealesePhone(String requestOoghli, CustomerReleasePhoneModel customerReleasePhoneModel) {
        Disposable disposable = mRestManager.insertNewRealesePhone(new NewRealesePhoneBody(requestOoghli,customerReleasePhoneModel))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    insertOrderMutableLiveData.setValue(r);
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

    public void callRofu(String requestOoghli) {
        Disposable disposable = mRestManager.listRofu(new ListBaseRequestBody(requestOoghli))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    rofuModelMutableLiveData.setValue(r);
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
