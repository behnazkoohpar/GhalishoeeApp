package com.koohpar.oghli.ui.signUpCustomer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.databinding.ActivitySignUpCustomerBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.utils.AppConstants;

import javax.inject.Inject;

public class SignUpCustomerActivity extends BaseActivity<ActivitySignUpCustomerBinding, SignUpCustomerViewModel> implements AppConstants, SignUpCustomerNavigator {

    @Inject
    SignUpCustomerViewModel mSignUpCustomerViewModel;

    ActivitySignUpCustomerBinding mActivitySignUpCustomerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivitySignUpCustomerBinding = getViewDataBinding();
            mSignUpCustomerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SignUpCustomerActivity.class);
    }

    @Override
    public SignUpCustomerViewModel getViewModel() {
        return mSignUpCustomerViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_up_customer;
    }
}
