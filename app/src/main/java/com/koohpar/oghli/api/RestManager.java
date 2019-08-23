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
import com.koohpar.oghli.ui.order.NewCollectAddressBody;
import com.koohpar.oghli.ui.order.NewCollectMobileBody;
import com.koohpar.oghli.ui.order.NewCollectPhoneBody;
import com.koohpar.oghli.ui.order.NewRealeseAddressBody;
import com.koohpar.oghli.ui.order.NewRealeseMobileBody;
import com.koohpar.oghli.ui.order.NewRealesePhoneBody;
import com.koohpar.oghli.ui.order.OrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.DeleteOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.EditOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.LakeOrderRequestBody;
import com.koohpar.oghli.ui.showOrder.body.UpdateOrderDetailStatusRequestBody;
import com.koohpar.oghli.ui.showOrder.body.UpdateOrderStatusRequestBody;
import com.koohpar.oghli.ui.signUpCustomer.AddCustomerRequestBody;
import com.koohpar.oghli.data.model.api.requestBody.ListBaseRequestBody;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface RestManager {

    Observable<UserModel> loginUser(LoginRequestBody loginRequestBody);

    Observable<List<Customer>> searchCustomer(SearchCustomerRequestBody searchCustomerRequestBody);

    Observable<List<OrderMissionDetailModel>> listJam(ListSumRequestBody listSumRequestBody);

    Observable<List<OrderMissionDetailModel>> listPakhsh(ListSumRequestBody listSumRequestBody);

    Observable<List<OrderDetailModel>> showOrdersDetail(ShowOrdersRequestBody showOrdersRequestBody);

    Observable<List<MantagheResponse>> listMantaghe(ListBaseRequestBody listBaseRequestBody);

    Observable<List<BranchResponse>> listBranch(ListBaseRequestBody listBaseRequestBody);

    Observable<String> addCustomer(AddCustomerRequestBody addCustomerRequestBody);

    Observable<List<ServicesModel>> listServices(ListBaseRequestBody listBaseRequestBody);

    Observable<List<OrderTypeModel>> listOrderType(ListBaseRequestBody listBaseRequestBody);

    Observable<List<ServiceAttrib3Model>> listCity(ListAttributeRequestBody listAttRequestBody);

    Observable<List<ServiceAttrib2Model>> listJens(ListAttributeRequestBody listAttributeRequestBody);

    Observable<List<ServiceAttrib1Model>> listShekl(ListAttributeRequestBody listAttributeRequestBody);

    Observable<List<ServiceAttrib4Model>> listRang(ListAttributeRequestBody listAttributeRequestBody);

    Observable<String> insertOrder(OrderRequestBody orderRequestBody);

    Observable<Boolean> editDetail(EditDetailRequestBody editDetailRequestBody);

    Observable<List<LakeStatusModel>> lakeOrder(LakeOrderRequestBody lakeOrderRequestBody);

    Observable<Boolean> deleteOrderDetail(DeleteOrderRequestBody deleteOrderRequestBody);

    Observable<Boolean> callEditOrder(EditOrderRequestBody editOrderRequestBody);

    Observable<Boolean> updateOrderStatus(UpdateOrderStatusRequestBody updateOrderStatusRequestBody);

    Observable<Boolean> updateOrderDetailStatus(UpdateOrderDetailStatusRequestBody updateOrderDetailStatusRequestBody);

    Observable<String> insertNewCollectAddress(NewCollectAddressBody newCollectAddressBody);

    Observable<String> insertNewCollectMobile(NewCollectMobileBody newCollectMobileBody);

    Observable<String> insertNewCollectPhone(NewCollectPhoneBody newCollectPhoneBody);

    Observable<String> insertNewRealeseAddress(NewRealeseAddressBody newRealeseAddressBody);

    Observable<String> insertNewRealeseMobile(NewRealeseMobileBody newRealeseMobileBody);

    Observable<String> insertNewRealesePhone(NewRealesePhoneBody newRealesePhoneBody);
}
