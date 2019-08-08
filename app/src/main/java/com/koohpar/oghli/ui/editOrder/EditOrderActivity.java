package com.koohpar.oghli.ui.editOrder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.databinding.ActivityEditOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.main.MainActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

public class EditOrderActivity extends BaseActivity<ActivityEditOrderBinding, EditOrderViewModel> implements AppConstants, EditOrderNavigator {

    public static String address,tel,telHome;
    @Inject
    EditOrderViewModel mEditOrderViewModel;
    public static String orderId, name, customerId;

    public static List<ServiceAttrib3Model> att3;
    public static List<ServiceAttrib4Model> att4;
    public static List<ServiceAttrib2Model> att2;
    public static List<ServiceAttrib1Model> att1;
    ActivityEditOrderBinding mActivityEditOrderBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private EditOrderDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityEditOrderBinding = getViewDataBinding();
            mEditOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            callEditOrderDetail();
            if (customerId == null) {
                mActivityEditOrderBinding.btn.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, EditOrderActivity.class);
    }

    @Override
    public EditOrderViewModel getViewModel() {
        return mEditOrderViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_order;
    }

    @Override
    public void closed() {
        startActivity(MainActivity.getStartIntent(EditOrderActivity.this));
    }

    @Override
    public void addOrder() {
        OrderActivity.customerId = customerId;
        OrderActivity.name = name;
        OrderActivity.telNumber = tel;
        OrderActivity.address = address;
        OrderActivity.numberHome = telHome;
        startActivity(OrderActivity.getStartIntent(EditOrderActivity.this));
    }

    private void callEditOrderDetail() {
        try {

            mEditOrderViewModel.callEditOrder(orderId, AppConstants.REQUEST_OOGHLI);
            mEditOrderViewModel.getOrdersModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(List<OrderDetailModel> data) {
        if (data != null) {
            setParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setParameter(List<OrderDetailModel> ordersModels) {

        if (ordersModels != null)
            mActivityEditOrderBinding.name.setText(name);
        recyclerViewListOrderMissionDetailModel = mActivityEditOrderBinding.list;
        layoutOrderMissionDetailModel = new LinearLayoutManager(this);
        recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
        mAdapter = new EditOrderDetailAdapter(ordersModels,att3,att2,att1,att4);
        recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new EditOrderDetailAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position,OrderDetailModel orderDetailModel) {
                callEditDetail(orderDetailModel);
            }

        });
    }

    private void callEditDetail(OrderDetailModel orderDetailModel) {
        try {
            mEditOrderViewModel.callEditDetail(orderDetailModel, AppConstants.REQUEST_OOGHLI);
            mEditOrderViewModel.getEditDetailMutableLiveData().observe(this, this::receivedDataEdit);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }
    private void receivedDataEdit(boolean data) {
        if (data) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_chaged), null, null);

        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }
}
