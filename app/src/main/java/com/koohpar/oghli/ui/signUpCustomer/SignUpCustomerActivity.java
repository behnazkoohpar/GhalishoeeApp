package com.koohpar.oghli.ui.signUpCustomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.BranchResponse;
import com.koohpar.oghli.data.model.api.Customer;
import com.koohpar.oghli.data.model.api.MantagheResponse;
import com.koohpar.oghli.databinding.ActivitySignUpCustomerBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SignUpCustomerActivity extends BaseActivity<ActivitySignUpCustomerBinding, SignUpCustomerViewModel> implements AppConstants, SignUpCustomerNavigator {

    @Inject
    SignUpCustomerViewModel mSignUpCustomerViewModel;

    ActivitySignUpCustomerBinding mActivitySignUpCustomerBinding;
    List<MantagheResponse> mantagheResponseList = new ArrayList<>();
    List<BranchResponse> branchesResponseList = new ArrayList<>();
    private String mantagheSelected;
    private String branchSelected;
    private String mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivitySignUpCustomerBinding = getViewDataBinding();
            mSignUpCustomerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            callListBranches();
            callListMantaghe();

            mActivitySignUpCustomerBinding.tel.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1 && s.charAt(0)==48)
                        CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), "شماره موبایل بدون صفر باشد", getString(R.string.ok), new CommonUtils.IL() {
                            @Override
                            public void onSuccess() {
                                mActivitySignUpCustomerBinding.tel.setText("");
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

    @Override
    public void addCustomer() {
        try {
            PersianCalendar persianCalendar = new PersianCalendar();
            mYear = String.valueOf(persianCalendar.getPersianYear());
            mMonth = String.valueOf(persianCalendar.getPersianMonth()+1);
            mDay = String.valueOf(persianCalendar.getPersianDay());
            if (Integer.parseInt(mMonth) < 10)
                mMonth = "0" + mMonth;
            if (Integer.parseInt(mDay) < 10)
                mDay = "0" + mDay;

            Customer customerModel= new Customer();
            customerModel.setCollectAddress(mActivitySignUpCustomerBinding.address.getText().toString());
            customerModel.setCustName(mActivitySignUpCustomerBinding.userName.getText().toString());
            customerModel.setCollectMobile(mActivitySignUpCustomerBinding.tel.getText().toString());
            customerModel.setCollectPhone(mActivitySignUpCustomerBinding.telHome.getText().toString());
            customerModel.setCustDesc(mActivitySignUpCustomerBinding.desc.getText().toString());
            customerModel.setCreatedBy(mSignUpCustomerViewModel.getDataManager().getServiceManId());
            customerModel.setCreatedDate(mYear + "/" + mMonth + "/" + mDay);
            customerModel.setBranchID("00000000-0000-0000-0000-000000000000");
            customerModel.setCustNo("");
            customerModel.setBirthDate("");
            customerModel.setCustSex(0);
            customerModel.setEshterakNo("");
            customerModel.setGeograficalID("00000000-0000-0000-0000-000000000000");


            mSignUpCustomerViewModel.callAddCustomer(AppConstants.REQUEST_OOGHLI,customerModel);
            mSignUpCustomerViewModel.getAddCustomerMutableLiveData().observe(this, this::receivedDataAddCustomer);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void callListMantaghe() {
        try {
            mSignUpCustomerViewModel.callListMantaghe(AppConstants.REQUEST_OOGHLI);
            mSignUpCustomerViewModel.getMantagheModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void callListBranches() {
        try {
            mSignUpCustomerViewModel.callListBranch(AppConstants.REQUEST_OOGHLI);
            mSignUpCustomerViewModel.getBranchModelMutableLiveData().observe(this, this::receivedDataBranches);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }

    }

    private void receivedDataAddCustomer(String data) {
        if (data != null) {
            Customer customer = new Customer();
            customer.setCustName(mActivitySignUpCustomerBinding.userName.getText().toString());
            customer.setCollectAddress(mActivitySignUpCustomerBinding.address.getText().toString());
            customer.setCollectMobile(mActivitySignUpCustomerBinding.tel.getText().toString());
            customer.setCollectPhone(mActivitySignUpCustomerBinding.telHome.getText().toString());
            customer.setCustomerID(data);
            OrderActivity.isFromCustomer=true;
            OrderActivity.customerModel = customer;
            startActivity(OrderActivity.getStartIntent(SignUpCustomerActivity.this));
        } else {
            CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }
    private void receivedData(List<MantagheResponse> data) {
        if (data != null) {
            String[] datas = new String[data.size()];
            for(int i=0 ; i<data.size();i++){
                datas[i] = data.get(i).getTitle();
            }
            mantagheSelected = data.get(0).getId();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivitySignUpCustomerBinding.mantaghe.setAdapter(adapter);
            mActivitySignUpCustomerBinding.mantaghe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    mantagheSelected =  data.get(position).getTitle();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void receivedDataBranches(List<BranchResponse> data) {
        if (data != null) {
            String[] datas = new String[data.size()];
            for(int i=0 ; i<data.size();i++){
                datas[i] = data.get(i).getBranchName();
            }
            branchSelected = data.get(0).getBranchId();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivitySignUpCustomerBinding.branch.setAdapter(adapter);
            mActivitySignUpCustomerBinding.branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                   branchSelected = (String) data.get(position).getBranchName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(SignUpCustomerActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }
}
