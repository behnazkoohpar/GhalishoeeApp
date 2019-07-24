package com.koohpar.oghli.ui.listPakhsh;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.databinding.ActivityListPakhshBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listSum.ListOrderMissionDetailModelAdapter;
import com.koohpar.oghli.ui.showOrder.ShowOrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

public class ListPakhshActivity extends BaseActivity<ActivityListPakhshBinding, ListPakhshViewModel> implements AppConstants, ListPakhshNavigator {

    @Inject
    ListPakhshViewModel mListPakhshViewModel;

    ActivityListPakhshBinding mActivityListPakhshBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private ListOrderMissionDetailModelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityListPakhshBinding = getViewDataBinding();
            mListPakhshViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            callListPakhsh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ListPakhshActivity.class);
    }

    @Override
    public ListPakhshViewModel getViewModel() {
        return mListPakhshViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_pakhsh;
    }

    private void callListPakhsh() {
        try {

            mListPakhshViewModel.callListPakhsh(
                    "8dd7df03-3f2b-449e-bf0f-12b781deefd9", "1398/04/31", AppConstants.REQUEST_OOGHLI);
            mListPakhshViewModel.getOrderMissionDetailModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ListPakhshActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(List<OrderMissionDetailModel> data) {
        if (data != null) {
            setListParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(ListPakhshActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setListParameter(List<OrderMissionDetailModel> data) {
        recyclerViewListOrderMissionDetailModel = mActivityListPakhshBinding.list;
        layoutOrderMissionDetailModel = new LinearLayoutManager(this);
        recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
        mAdapter = new ListOrderMissionDetailModelAdapter(data);
        recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new ListOrderMissionDetailModelAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position) {
                ShowOrderActivity.orderId = data.get(position).getOrderID();
                startActivity(ShowOrderActivity.getStartIntent(ListPakhshActivity.this));
            }

            @Override
            public void onCallTelClick(int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0" + data.get(position).getCollectMobile()));
                startActivity(intent);
            }

            @Override
            public void onCallTelHomeClick(int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0" + data.get(position).getCollectPhone()));
                startActivity(intent);
            }
        });
    }
}
