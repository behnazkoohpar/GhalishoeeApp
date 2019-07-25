package com.koohpar.oghli.ui.listSum;

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
import com.koohpar.oghli.databinding.ActivityListSumBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.showOrder.ShowOrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

public class ListSumActivity extends BaseActivity<ActivityListSumBinding, ListSumViewModel> implements AppConstants, ListSumNavigator {

    @Inject
    ListSumViewModel mListSumViewModel;

    ActivityListSumBinding mActivityListSumBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private ListOrderMissionDetailModelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityListSumBinding = getViewDataBinding();
            mListSumViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            callListSum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ListSumActivity.class);
    }

    @Override
    public ListSumViewModel getViewModel() {
        return mListSumViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_sum;
    }

    private void callListSum() {
        try {

            mListSumViewModel.callListSum(
                    mListSumViewModel.getDataManager().getServiceManId(), "1398/04/28", AppConstants.REQUEST_OOGHLI);
            mListSumViewModel.getOrderMissionDetailModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(List<OrderMissionDetailModel> data) {
        if (data != null) {
            setListParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setListParameter(List<OrderMissionDetailModel> data) {
        recyclerViewListOrderMissionDetailModel = mActivityListSumBinding.list;
        layoutOrderMissionDetailModel = new LinearLayoutManager(this);
        recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
        mAdapter = new ListOrderMissionDetailModelAdapter(data);
        recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new ListOrderMissionDetailModelAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position) {
                ShowOrderActivity.orderId = data.get(position).getOrderID();
                ShowOrderActivity.name = data.get(position).getCustName();
                startActivity(ShowOrderActivity.getStartIntent(ListSumActivity.this));
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
