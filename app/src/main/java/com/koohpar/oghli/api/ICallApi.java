package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.BranchResponse;
import com.koohpar.oghli.data.model.api.Customer;
import com.koohpar.oghli.data.model.api.LakeStatusModel;
import com.koohpar.oghli.data.model.api.MantagheResponse;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.RofuAttribModel;
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

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by behnaz on 10/31/2017.
 */

public interface ICallApi {

    @POST("Login")
    Observable<UserModel> loginUser(@Body LoginRequestBody loginRequestBody);

    @POST("Retrive_Customer_List_ByPhone")
    Observable<List<Customer>> searchCustomer(@Body SearchCustomerRequestBody searchCustomerRequestBody);

    @POST("Retrive_List_Jam")
    Observable<List<OrderMissionDetailModel>> listJam(@Body ListSumRequestBody listSumRequestBody);

    @POST("Retrive_List_Pakhsh")
    Observable<List<OrderMissionDetailModel>> listPakhsh(@Body ListSumRequestBody listSumRequestBody);

    @POST("Retrive_OrderDetail")
    Observable<List<OrderDetailModel>> showOrderDetail(@Body ShowOrdersRequestBody showOrdersRequestBody);

    @POST("Retrive_Mantaghe")
    Observable<List<MantagheResponse>> mantaghe(@Body ListBaseRequestBody listBaseRequestBody);

    @POST("Retrive_Branches")
    Observable<List<BranchResponse>> branch(@Body ListBaseRequestBody listBaseRequestBody);

    @POST("Insert_Customer")
    Observable<String> addCustomer(@Body AddCustomerRequestBody addCustomerRequestBody);

    @POST("Retrive_List_Service")
    Observable<List<ServicesModel>> listServices(@Body ListBaseRequestBody listBaseRequestBody);

    @POST("Retrive_OrderTypes")
    Observable<List<OrderTypeModel>> listOrderType(@Body ListBaseRequestBody listBaseRequestBody);

    @POST("Retrive_ServiceAttrib3")
    Observable<List<ServiceAttrib3Model>> listCity(@Body ListAttributeRequestBody listAttRequestBody);

    @POST("Retrive_ServiceAttrib2")
    Observable<List<ServiceAttrib2Model>> listJens(@Body ListAttributeRequestBody listAttributeRequestBody);

    @POST("Retrive_ServiceAttrib1")
    Observable<List<ServiceAttrib1Model>> listShekl(@Body ListAttributeRequestBody listAttributeRequestBody);

    @POST("Retrive_ServiceAttrib4")
    Observable<List<ServiceAttrib4Model>> listRang(@Body ListAttributeRequestBody listAttributeRequestBody);

    @POST("Insert_Order")
    Observable<String> insertOrder(@Body OrderRequestBody orderRequestBody);

    @POST("Update_OrderDetail")
    Observable<Boolean> editDetail(@Body EditDetailRequestBody editDetailRequestBody);

    @POST("Retrive_LakeStatus")
    Observable<List<LakeStatusModel>> lakeOrder(@Body LakeOrderRequestBody lakeOrderRequestBody);

    @POST("Delete_OrderDetail")
    Observable<Boolean> deleteOrderDetail(@Body DeleteOrderRequestBody deleteOrderRequestBody);

    @POST("Update_Order")
    Observable<Boolean> callEditOrder(@Body EditOrderRequestBody editOrderRequestBody);

    @POST("Update_Order_Status")
    Observable<Boolean> updateOrderStatus(@Body UpdateOrderStatusRequestBody updateOrderStatusRequestBody);

    @POST("Update_OrderDetail_Status")
    Observable<Boolean> updateOrderDetailStatus(@Body UpdateOrderDetailStatusRequestBody updateOrderDetailStatusRequestBody);

    @POST("Insert_CustomerCollectAddress")
    Observable<String> insertNewCollectAddress(@Body NewCollectAddressBody newCollectAddressBody);

    @POST("Insert_CustomerCollectMobile")
    Observable<String> insertNewCollectMobile(@Body NewCollectMobileBody newCollectMobileBody);

    @POST("Insert_CustomerCollectPhone")
    Observable<String> insertNewCollectPhone(@Body NewCollectPhoneBody newCollectPhoneBody);

    @POST("Insert_CustomerReleaseAddress")
    Observable<String> insertNewRealeseAddress(@Body NewRealeseAddressBody newRealeseAddressBody);

    @POST("Insert_CustomerReleaseMobile")
    Observable<String> insertNewRealeseMobile(@Body NewRealeseMobileBody newRealeseMobileBody);

    @POST("Insert_CustomerReleasePhone")
    Observable<String> insertNewRealesePhone(@Body NewRealesePhoneBody newRealesePhoneBody);

    @POST("Retrive_RofuAttribute_List")
    Observable<List<RofuAttribModel>> listRofu(@Body ListBaseRequestBody listBaseRequestBody);


//    @GET
//    Call<ResponseBody> callMapAddress(@Url String url, @Body RequestBody body);

//    @GET("/xml")
//    Call<Responce> getProducts();

}
