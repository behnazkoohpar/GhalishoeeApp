package com.koohpar.oghli.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.data.model.api.UserModel;
import com.koohpar.oghli.databinding.ActivityLoginBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.main.MainActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;
import com.koohpar.oghli.utils.TextEncrypter;

import javax.inject.Inject;

/**
 * Created by behnaz on 11/3/17.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements AppConstants, LoginNavigator {

    @Inject
    LoginViewModel mLoginViewModel;
    ActivityLoginBinding mActivityLoginBinding;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityLoginBinding = getViewDataBinding();
            mLoginViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            typeface = Typeface.createFromAsset(LoginActivity.this.getAssets(), "fonts/iran_sans.ttf");
            mActivityLoginBinding.userName.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1)
                        if (start == 10) {
                            mActivityLoginBinding.password.requestFocus();
                        }
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    public void login() {
        try {
            if (validateInfo())
                callLogin();
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void openMainActivity() {
        startActivity(new Intent(MainActivity.getStartIntent(LoginActivity.this)));

    }

    public void setLoginParameter(UserModel userModel) {
        mLoginViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
        mLoginViewModel.getDataManager().updateUserInfo(
                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                userModel.getServiceManID(),
                userModel.getServiceManName(),
                userModel.getUserId(),
                userModel.getUserName(),
                userModel.getPassword(),
                userModel.getuName(),
                userModel.getUserImage());
        openMainActivity();
        this.finish();
    }

    private void callLogin() {
        try {
            mLoginViewModel.getDataManager().setUsername(String.valueOf(mActivityLoginBinding.userName.getText()));
            mLoginViewModel.getDataManager().setPassword(mActivityLoginBinding.password.getText().toString());

            mLoginViewModel.login(mLoginViewModel.getDataManager().getUsername(), mActivityLoginBinding.password.getText().toString(), AppConstants.REQUEST_OOGHLI);
            mLoginViewModel.getTokenResponseModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(UserModel data) {
        if (data != null) {
            setLoginParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private boolean validateInfo() {
        if (mActivityLoginBinding.userName.getText().toString().trim().isEmpty() ||
                mActivityLoginBinding.userName.getText().toString().length() < 2) {
            mActivityLoginBinding.textLayoutUserName.setError(wrapInCustomfont(getString(R.string.validation_phonenumber)));
            mActivityLoginBinding.userName.requestFocus();
            return false;
        } else {
            mActivityLoginBinding.textLayoutUserName.setErrorEnabled(false);
        }
        if (mActivityLoginBinding.password.getText().toString().trim().isEmpty()) {
            mActivityLoginBinding.textLayoutPassword.setError(wrapInCustomfont(getString(R.string.validation_password)));
            mActivityLoginBinding.password.requestFocus();
            return false;
        } else {
            mActivityLoginBinding.textLayoutPassword.setErrorEnabled(false);
        }
        if (mActivityLoginBinding.password.getText().toString().length() < 5) {
            mActivityLoginBinding.textLayoutPassword.setError(wrapInCustomfont(getString(R.string.validation_password_length)));
            mActivityLoginBinding.password.requestFocus();
            return false;
        } else {
            mActivityLoginBinding.textLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
