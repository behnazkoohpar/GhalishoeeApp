package com.koohpar.oghli.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.databinding.ActivityOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.utils.AppConstants;

import javax.inject.Inject;

public class OrderActivity extends BaseActivity<ActivityOrderBinding, OrderViewModel> implements AppConstants, OrderNavigator {

    @Inject
    OrderViewModel mOrderViewModel;
    ActivityOrderBinding mActivityOrderBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityOrderBinding = getViewDataBinding();
            mOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, OrderActivity.class);
    }

    @Override
    public OrderViewModel getViewModel() {
        return mOrderViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }


}
