package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.BranchResponse;
import com.koohpar.oghli.data.model.api.CustomerModel;
import com.koohpar.oghli.data.model.api.MantagheResponse;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.ui.listSum.ListSumRequestBody;
import com.koohpar.oghli.ui.login.LoginRequestBody;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerRequestBody;
import com.koohpar.oghli.ui.showOrder.ShowOrdersRequestBody;
import com.koohpar.oghli.ui.signUpCustomer.AddCustomerRequestBody;
import com.koohpar.oghli.ui.signUpCustomer.ListBaseRequestBody;

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
    public Observable<List<OrderDetailModel>> showOrdersDetail(ShowOrdersRequestBody showOrdersRequestBody) {
        return iCallApi.showOrderDetail(showOrdersRequestBody);
    }

    @Override
    public Observable<List<MantagheResponse>> listMantaghe(ListBaseRequestBody listBaseRequestBody) {
        return iCallApi.mantaghe(listBaseRequestBody);
    }

    @Override
    public Observable<List<BranchResponse>> listBranch(ListBaseRequestBody listBaseRequestBody) {
        return iCallApi.branch(listBaseRequestBody);
    }

    @Override
    public Observable addCustomer(AddCustomerRequestBody addCustomerRequestBody) {
        return iCallApi.addCustomer(addCustomerRequestBody);
    }
}
