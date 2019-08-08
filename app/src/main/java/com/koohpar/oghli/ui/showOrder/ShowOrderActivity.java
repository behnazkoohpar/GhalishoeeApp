package com.koohpar.oghli.ui.showOrder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.LakeStatusModel;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.databinding.ActivityShowOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listSum.ListOrderMissionDetailModelAdapter;
import com.koohpar.oghli.ui.listSum.ListSumActivity;
import com.koohpar.oghli.ui.main.MainActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

public class ShowOrderActivity extends BaseActivity<ActivityShowOrderBinding, ShowOrderViewModel> implements AppConstants, ShowOrderNavigator {

    @Inject
    ShowOrderViewModel mShowOrderViewModel;
    public static String orderId, name, customerId,address,tel,telHome;
    public static boolean isFromSum = false;
    ActivityShowOrderBinding mActivityShowOrderBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private ListOrderDetailModelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityShowOrderBinding = getViewDataBinding();
            mShowOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mActivityShowOrderBinding.address.setText(address);
            mActivityShowOrderBinding.telnumber.setText(tel);
            mActivityShowOrderBinding.numberhome.setText(telHome);
            mActivityShowOrderBinding.name.setText(name);

            callShowOrderDetail();
            if (customerId == null) {
                mActivityShowOrderBinding.btn.setVisibility(View.GONE);
            }
            if (isFromSum)
                mActivityShowOrderBinding.result.setText("دلایل عدم جمع");
            else
                mActivityShowOrderBinding.result.setText("دلایل عدم پخش");
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

    @Override
    public void addOrder() {
        OrderActivity.customerId = customerId;
        OrderActivity.name = name;
        OrderActivity.address = address;
        OrderActivity.telNumber = tel;
        OrderActivity.numberHome = telHome;
        startActivity(OrderActivity.getStartIntent(ShowOrderActivity.this));
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

    private void receivedData(List<OrderDetailModel> data) {
        if (data != null) {
            setParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setParameter(List<OrderDetailModel> ordersModels) {
        if (ordersModels != null)
            mActivityShowOrderBinding.name.setText(name);
        recyclerViewListOrderMissionDetailModel = mActivityShowOrderBinding.list;
        layoutOrderMissionDetailModel = new LinearLayoutManager(this);
        recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
        mAdapter = new ListOrderDetailModelAdapter(ordersModels);
        recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new ListOrderDetailModelAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position,OrderDetailModel orderDetailModel) {
                callDeleteOrderDetail(orderDetailModel);
            }
        });
    }

    private void callDeleteOrderDetail(OrderDetailModel orderDetailModel) {
        try {
            mShowOrderViewModel.callDeleteOrderDetail( AppConstants.REQUEST_OOGHLI,orderDetailModel);
            mShowOrderViewModel.getDeleteOrderDetailMutableLiveData().observe(this, this::receivedDataDelete);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataDelete(Boolean data) {
        if (data ) {
            //TODO: delete from list or refresh layout
            finish();
            startActivity(getIntent());
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    @Override
    public void result() {
        if (isFromSum)
            callAdamSum();
        else
            callAdamPakhsh();
    }

    private void callAdamSum() {
        try {
            mShowOrderViewModel.callAdamSum(1, AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getAdamSumMutableLiveData().observe(this, this::receivedDataSum);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataSum(List<LakeStatusModel> lakeStatusModels) {
        if (lakeStatusModels.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShowOrderActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(ShowOrderActivity.this);

            for (int i = 0; i < lakeStatusModels.size(); i++)
                arrayAdapter.add(lakeStatusModels.get(i).getLakeStatusName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    startActivity(MainActivity.getStartIntent(ShowOrderActivity.this));
                    finish();

                }
            });
            builderSingle.show();
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);

        }
    }

    private void callAdamPakhsh() {
        try {
            mShowOrderViewModel.callAdamPakhsh(2, AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getAdamPakhshMutableLiveData().observe(this, this::receivedDataPakhsh);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataPakhsh(List<LakeStatusModel> lakeStatusModels) {
        if (lakeStatusModels.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShowOrderActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(ShowOrderActivity.this);

            for (int i = 0; i < lakeStatusModels.size(); i++)
                arrayAdapter.add(lakeStatusModels.get(i).getLakeStatusName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    startActivity(MainActivity.getStartIntent(ShowOrderActivity.this));
                    finish();
                }
            });
            builderSingle.show();
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);

        }
    }

}

