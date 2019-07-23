package com.koohpar.oghli.ui.listCustomer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.databinding.ActivityListCustomerBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.utils.AppConstants;

import javax.inject.Inject;

public class ListCustomerActivity  extends BaseActivity<ActivityListCustomerBinding, ListCustomerViewModel> implements AppConstants, ListCustomerNavigator {

    @Inject
    ListCustomerViewModel mListCustomerViewModel;

    ActivityListCustomerBinding mActivityListCustomerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityListCustomerBinding = getViewDataBinding();
            mListCustomerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void openOrder() {
        startActivity(OrderActivity.getStartIntent(ListCustomerActivity.this));
    }
}
