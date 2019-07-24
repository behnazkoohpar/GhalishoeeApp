package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.CustomerModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.ui.listSum.ListSumRequestBody;
import com.koohpar.oghli.ui.login.LoginRequestBody;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerRequestBody;
import com.koohpar.oghli.ui.showOrder.ShowOrdersRequestBody;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface RestManager {

    Observable<UserModel> loginUser(LoginRequestBody loginRequestBody);

    Observable<List<CustomerModel>> searchCustomer(SearchCustomerRequestBody searchCustomerRequestBody);

    Observable<List<OrderMissionDetailModel>> listJam(ListSumRequestBody listSumRequestBody);

    Observable<List<OrderMissionDetailModel>> listPakhsh(ListSumRequestBody listSumRequestBody);

    Observable<OrdersModel> showOrdersDetail(ShowOrdersRequestBody showOrdersRequestBody);

}
