package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.BranchResponse;
import com.koohpar.oghli.data.model.api.Customer;
import com.koohpar.oghli.data.model.api.LakeStatusModel;
import com.koohpar.oghli.data.model.api.MantagheResponse;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.data.model.api.ServicesModel;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.data.model.api.requestBody.ListSumRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.LoginRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ListAttributeRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.SearchCustomerRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ShowOrdersRequestBody;
import com.koohpar.oghli.ui.editOrder.EditDetailRequestBody;
import com.koohpar.oghli.ui.order.OrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.DeleteOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.EditOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.LakeOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.UpdateOrderDetailStatusRequestBody;
import com.koohpar.oghli.ui.showOrder.body.UpdateOrderStatusRequestBody;
import com.koohpar.oghli.ui.signUpCustomer.AddCustomerRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ListBaseRequestBody;

import java.util.List;

import javax.inject.Inject;

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
    public Observable<List<Customer>> searchCustomer(SearchCustomerRequestBody searchCustomerRequestBody) {
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

    @Override
    public Observable<List<ServicesModel>> listServices(ListBaseRequestBody listBaseRequestBody) {
        return iCallApi.listServices(listBaseRequestBody);
    }

    @Override
    public Observable<List<OrderTypeModel>> listOrderType(ListBaseRequestBody listBaseRequestBody) {
        return iCallApi.listOrderType(listBaseRequestBody);
    }

    @Override
    public Observable<List<ServiceAttrib3Model>> listCity(ListAttributeRequestBody listAttRequestBody) {
        return iCallApi.listCity(listAttRequestBody);
    }

    @Override
    public Observable<List<ServiceAttrib2Model>> listJens(ListAttributeRequestBody listAttributeRequestBody) {
        return iCallApi.listJens(listAttributeRequestBody);
    }

    @Override
    public Observable<List<ServiceAttrib1Model>> listShekl(ListAttributeRequestBody listAttributeRequestBody) {
        return iCallApi.listShekl(listAttributeRequestBody);
    }

    @Override
    public Observable<List<ServiceAttrib4Model>> listRang(ListAttributeRequestBody listAttributeRequestBody) {
        return iCallApi.listRang(listAttributeRequestBody);
    }

    @Override
    public Observable<String> insertOrder(OrderRequestBody orderRequestBody) {
        return iCallApi.insertOrder(orderRequestBody);
    }

    @Override
    public Observable<Boolean> editDetail(EditDetailRequestBody editDetailRequestBody) {
        return iCallApi.editDetail(editDetailRequestBody);
    }

    @Override
    public Observable<List<LakeStatusModel>> lakeOrder(LakeOrderRequestBody lakeOrderRequestBody) {
        return iCallApi.lakeOrder(lakeOrderRequestBody);
    }

    @Override
    public Observable<Boolean> deleteOrderDetail(DeleteOrderRequestBody deleteOrderRequestBody) {
        return iCallApi.deleteOrderDetail(deleteOrderRequestBody);
    }

    @Override
    public Observable<Boolean> callEditOrder(EditOrderRequestBody editOrderRequestBody) {
        return iCallApi.callEditOrder(editOrderRequestBody);
    }

    @Override
    public Observable<Boolean> updateOrderStatus(UpdateOrderStatusRequestBody updateOrderStatusRequestBody) {
        return iCallApi.updateOrderStatus(updateOrderStatusRequestBody);
    }

    @Override
    public Observable<Boolean> updateOrderDetailStatus(UpdateOrderDetailStatusRequestBody updateOrderDetailStatusRequestBody) {
        return iCallApi.updateOrderDetailStatus(updateOrderDetailStatusRequestBody);
    }
}
