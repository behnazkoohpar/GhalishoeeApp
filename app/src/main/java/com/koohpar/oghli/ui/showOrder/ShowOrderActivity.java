package com.koohpar.oghli.ui.showOrder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.databinding.ActivityShowOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

public class ShowOrderActivity extends BaseActivity<ActivityShowOrderBinding, ShowOrderViewModel> implements AppConstants, ShowOrderNavigator {

    @Inject
    ShowOrderViewModel mShowOrderViewModel;
    public static String orderId;
    ActivityShowOrderBinding mActivityShowOrderBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityShowOrderBinding = getViewDataBinding();
            mShowOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            callShowOrderDetail();
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ShowOrderActivity.class);
    }

    @Override
    public ShowOrderViewModel getViewModel() {
        return mShowOrderViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_order;
    }

    private void callShowOrderDetail() {
        try {

            mShowOrderViewModel.callShowOrder(orderId, AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getOrdersModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(OrdersModel data) {
        if (data != null) {
            setParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setParameter(OrdersModel ordersModel) {
        if (ordersModel != null) {
            mActivityShowOrderBinding.name.setText(ordersModel.getCustName());
            mActivityShowOrderBinding.factorNumber.setText(ordersModel.getOrdersNo());
            mActivityShowOrderBinding.typeOrder.setText(ordersModel.getServiceName());
            mActivityShowOrderBinding.price.setText(String.valueOf(ordersModel.getOrderServicePrice()));
            mActivityShowOrderBinding.numberOrder.setText(String.valueOf(ordersModel.getOrdersCount()));
        }
    }
}

