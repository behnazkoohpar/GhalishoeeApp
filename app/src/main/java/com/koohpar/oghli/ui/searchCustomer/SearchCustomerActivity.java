package com.koohpar.oghli.ui.searchCustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.Customer;
import com.koohpar.oghli.databinding.ActivitySearchCustomerBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listCustomer.ListCustomerActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchCustomerActivity extends BaseActivity<ActivitySearchCustomerBinding, SearchCustomerViewModel> implements AppConstants, SearchCustomerNavigator {

    @Inject
    SearchCustomerViewModel mSearchCustomerViewModel;

    ActivitySearchCustomerBinding mActivitySearchCustomerBinding;
    public static List<Customer> customerModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivitySearchCustomerBinding = getViewDataBinding();
            mSearchCustomerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mActivitySearchCustomerBinding.tel.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1 && s.charAt(0)==48)
                        CommonUtils.showSingleButtonAlert(SearchCustomerActivity.this, getString(R.string.text_attention), "شماره موبایل بدون صفر باشد", getString(R.string.ok), new CommonUtils.IL() {
                            @Override
                            public void onSuccess() {
                                mActivitySearchCustomerBinding.tel.setText("");
                                return;
                            }

                            @Override
                            public void onCancel() {
                                return;
                            }
                        });

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
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
        if (validateInfo()) {
            try {
                mSearchCustomerViewModel.searchCustomer(mActivitySearchCustomerBinding.tel.getText().toString(),
                        AppConstants.REQUEST_OOGHLI);
                mSearchCustomerViewModel.getCustomerModelMutableLiveData().observe(this, this::receivedData);

            } catch (Exception e) {
                CommonUtils.showSingleButtonAlert(SearchCustomerActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
                e.printStackTrace();
            }
        }
//
    }

    private void receivedData(List<Customer> data) {
        if (data != null) {
            customerModels = data;
            startActivity(ListCustomerActivity.getStartIntent(SearchCustomerActivity.this));
        } else {
            CommonUtils.showSingleButtonAlert(SearchCustomerActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private boolean validateInfo() {
//        if (mActivitySearchCustomerBinding.userName.getText().toString().trim().isEmpty() ||
//                mActivitySearchCustomerBinding.userName.getText().toString().length() < 10) {
//            mActivitySearchCustomerBinding.textLayoutUserName.setError(wrapInCustomfont(getString(R.string.validation_name)));
//            mActivitySearchCustomerBinding.userName.requestFocus();
//            return false;
//        } else {
//            mActivitySearchCustomerBinding.textLayoutUserName.setErrorEnabled(false);
//        }
//        if (mActivitySearchCustomerBinding.telHome.getText().toString().length() < 10) {
//            mActivitySearchCustomerBinding.textLayoutTelHome.setError(wrapInCustomfont(getString(R.string.validation_telhome)));
//            mActivitySearchCustomerBinding.telHome.requestFocus();
//            return false;
//        } else {
//            mActivitySearchCustomerBinding.textLayoutTelHome.setErrorEnabled(false);
//        }
        if (mActivitySearchCustomerBinding.tel.getText().toString().trim().isEmpty() ||
                mActivitySearchCustomerBinding.tel.getText().toString().length() < 10) {
            mActivitySearchCustomerBinding.textLayoutTel.setError(wrapInCustomfont(getString(R.string.validation_telnumber)));
            mActivitySearchCustomerBinding.tel.requestFocus();
            return false;
        } else {
            mActivitySearchCustomerBinding.textLayoutTel.setErrorEnabled(false);
        }
        return true;
    }
}
