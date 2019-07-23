package com.koohpar.oghli.api;

import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.ui.login.LoginRequestBody;

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

    @POST("/RetryGetSms")
    Call<ResponseBody> callRetryGetSms(@Body RequestBody requestBody);

//    @GET
//    Call<ResponseBody> callMapAddress(@Url String url, @Body RequestBody body);

//    @GET("/xml")
//    Call<Responce> getProducts();

}
