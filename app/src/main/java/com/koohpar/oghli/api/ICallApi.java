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

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by behnaz on 10/31/2017.
 */

public interface ICallApi {

    @POST("Login")
    Observable<UserModel> loginUser(@Body LoginRequestBody loginRequestBody);

    @POST("Retrive_Customer_List_ByData")
    Observable<List<CustomerModel>> searchCustomer(@Body SearchCustomerRequestBody searchCustomerRequestBody);

    @POST("Retrive_List_Jam")
    Observable<List<OrderMissionDetailModel>> listJam(@Body ListSumRequestBody listSumRequestBody);

    @POST("Retrive_List_Pakhsh")
    Observable<List<OrderMissionDetailModel>> listPakhsh(@Body ListSumRequestBody listSumRequestBody);

    @POST("Retrive_Order")
    Observable<OrdersModel> showOrderDetail(@Body ShowOrdersRequestBody showOrdersRequestBody);


//    @GET
//    Call<ResponseBody> callMapAddress(@Url String url, @Body RequestBody body);

//    @GET("/xml")
//    Call<Responce> getProducts();

}
