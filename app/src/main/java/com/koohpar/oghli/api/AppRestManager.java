package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.CustomerModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.ui.listSum.ListSumRequestBody;
import com.koohpar.oghli.ui.login.LoginRequestBody;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerRequestBody;
import com.koohpar.oghli.ui.showOrder.ShowOrdersRequestBody;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class AppRestManager implements RestManager {

    @SuppressWarnings("WeakerAccess")
    @Inject
    public AppRestManager(ICallApi iCallApi) {
        this.iCallApi = iCallApi;
    }

    //    @SuppressWarnings("WeakerAccess")
//    @Inject
    ICallApi iCallApi;

    @Override
    public Observable<UserModel> loginUser(LoginRequestBody loginRequestBody) {
        return iCallApi.loginUser(loginRequestBody);
    }

    @Override
    public Observable<List<CustomerModel>> searchCustomer(SearchCustomerRequestBody searchCustomerRequestBody) {
        return iCallApi.searchCustomer(searchCustomerRequestBody);
    }

    @Override
    public Observable<List<OrderMissionDetailModel>> listJam(ListSumRequestBody listSumRequestBody) {
        return iCallApi.listJam(listSumRequestBody);
    }

    @Override
    public Observable<List<OrderMissionDetailModel>> listPakhsh(ListSumRequestBody listSumRequestBody) {
        return iCallApi.listPakhsh(listSumRequestBody);
    }

    @Override
    public Observable<OrdersModel> showOrdersDetail(ShowOrdersRequestBody showOrdersRequestBody) {
        return iCallApi.showOrderDetail(showOrdersRequestBody);
    }
}
