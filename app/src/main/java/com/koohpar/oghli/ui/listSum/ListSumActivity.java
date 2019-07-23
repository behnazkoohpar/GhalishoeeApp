package com.koohpar.oghli.ui.listSum;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.BaseResponse;
import com.koohpar.oghli.data.model.api.TokenResponse;
import com.koohpar.oghli.databinding.ActivityListSumBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import javax.inject.Inject;

public class ListSumActivity extends BaseActivity<ActivityListSumBinding, ListSumViewModel> implements AppConstants, ListSumNavigator {

    @Inject
    ListSumViewModel mListSumViewModel;

    ActivityListSumBinding mActivityListSumBinding;

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

//            mListSumViewModel.callListSum(mListSumViewModel.getDataManager().getUsername(), mActivityListSumBinding.password.getText().toString(), AppConstants.REQUEST_OOGHLI);
            mListSumViewModel.getTokenResponseModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(BaseResponse<TokenResponse> tokenResponseBaseResponse) {

    }


}
