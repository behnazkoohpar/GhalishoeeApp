package com.koohpar.oghli.ui.searchCustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.databinding.ActivitySearchCustomerBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listCustomer.ListCustomerActivity;
import com.koohpar.oghli.utils.AppConstants;

import javax.inject.Inject;

public class SearchCustomerActivity extends BaseActivity<ActivitySearchCustomerBinding, SearchCustomerViewModel> implements AppConstants, SearchCustomerNavigator {

    @Inject
    SearchCustomerViewModel mSearchCustomerViewModel;

    ActivitySearchCustomerBinding mActivitySearchCustomerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivitySearchCustomerBinding = getViewDataBinding();
            mSearchCustomerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SearchCustomerActivity.class);
    }

    @Override
    public SearchCustomerViewModel getViewModel() {
        return mSearchCustomerViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_customer;
    }

    @Override
    public void onCallSearch() {
        startActivity(ListCustomerActivity.getStartIntent(SearchCustomerActivity.this));
    }
}
