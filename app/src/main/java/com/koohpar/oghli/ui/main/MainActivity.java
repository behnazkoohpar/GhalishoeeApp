package com.koohpar.oghli.ui.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.databinding.ActivityMainBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listCustomer.ListCustomerActivity;
import com.koohpar.oghli.ui.listSum.ListSumActivity;
import com.koohpar.oghli.ui.login.LoginActivity;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerActivity;
import com.koohpar.oghli.ui.signUpCustomer.SignUpCustomerActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements AppConstants, MainNavigator
        , NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainViewModel mMainViewModel;

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityMainBinding = getViewDataBinding();
            mMainViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mActivityMainBinding.nv.setNavigationItemSelectedListener(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void openMenu() {
        mActivityMainBinding.dl.openDrawer(Gravity.RIGHT);
    }

    @TargetApi(23)
    boolean checkForCameraPermission() {
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA)) {
                CommonUtils.showTwoButtonAlert(MainActivity.this, getString(R.string.app_need_access_camera), getString(R.string.pop_up_ok), getString(R.string.cancel), new CommonUtils.IL() {

                    @Override
                    public void onSuccess() {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                AppConstants.MY_PERMISSIONS_REQUEST_CAMERA);
                    }

                    @Override
                    public void onCancel() {

                    }

                });
                return false;
            }
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    AppConstants.MY_PERMISSIONS_REQUEST_CAMERA);

            return false;
        } else
            return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try {
            int id = item.getItemId();
            if (id == R.id.nav_setting) {
            } else if (id == R.id.nav_about_us) {
            } else if (id == R.id.nav_exit) {
                CommonUtils.showTwoButtonAlert(MainActivity.this, getString(R.string.exit_ok), getString(R.string.txt_menu_exit), getString(R.string.btn_cancel), new CommonUtils.IL() {
                    @Override
                    public void onSuccess() {
                        setLogOut();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }

            mActivityMainBinding.dl.closeDrawer(Gravity.RIGHT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setLogOut() {
        try {
            mMainViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
            mMainViewModel.getDataManager().updateUserInfo(
                    DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                    "",
                    "",
                    "",
                    "",
                    null);

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void openSignUp() {
        startActivity(SignUpCustomerActivity.getStartIntent(MainActivity.this));
    }

    @Override
    public void openSearch() {
        startActivity(SearchCustomerActivity.getStartIntent(MainActivity.this));
    }

    @Override
    public void openSum() {
        startActivity(ListSumActivity.getStartIntent(MainActivity.this));
    }

    @Override
    public void openDistribute() {
        startActivity(ListCustomerActivity.getStartIntent(MainActivity.this));
    }
}
