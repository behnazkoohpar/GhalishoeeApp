package com.koohpar.oghli.ui.listCustomer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.databinding.ActivityListCustomerBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerActivity;
import com.koohpar.oghli.ui.signUpCustomer.SignUpCustomerActivity;
import com.koohpar.oghli.utils.AppConstants;

import javax.inject.Inject;

public class ListCustomerActivity  extends BaseActivity<ActivityListCustomerBinding, ListCustomerViewModel> implements AppConstants, ListCustomerNavigator {

    @Inject
    ListCustomerViewModel mListCustomerViewModel;

    ActivityListCustomerBinding mActivityListCustomerBinding;
    private RecyclerView recyclerViewListCustomerModel;
    private LinearLayoutManager layoutCustomerModel;
    private ListCustomerModelAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityListCustomerBinding = getViewDataBinding();
            mListCustomerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            setCustomerModels();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCustomerModels() {
        recyclerViewListCustomerModel = mActivityListCustomerBinding.list;
        layoutCustomerModel = new LinearLayoutManager(this);
        recyclerViewListCustomerModel.setLayoutManager(layoutCustomerModel);
        mAdapter = new ListCustomerModelAdapter(SearchCustomerActivity.customerModels);
        recyclerViewListCustomerModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new ListCustomerModelAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position) {
//                open Detail Order
            }

            @Override
            public void onCallTelClick(int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0"+SearchCustomerActivity.customerModels.get(position).getCollectMobile()));
                startActivity(intent);
            }

            @Override
            public void onCallTelHomeClick(int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0"+SearchCustomerActivity.customerModels.get(position).getCollectPhone()));
                startActivity(intent);
            }
        });
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ListCustomerActivity.class);
    }

    @Override
    public ListCustomerViewModel getViewModel() {
        return mListCustomerViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_customer;
    }

    @Override
    public void onCreateNewCustomer() {
        startActivity(SignUpCustomerActivity.getStartIntent(ListCustomerActivity.this));
    }
}
