package com.koohpar.oghli.ui.listPakhsh;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.databinding.ActivityListPakhshBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listSum.ListOrderMissionDetailModelAdapter;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.ui.showOrder.ShowOrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.date.DatePickerDialog;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ListPakhshActivity extends BaseActivity<ActivityListPakhshBinding, ListPakhshViewModel> implements AppConstants, ListPakhshNavigator
        , DatePickerDialog.OnDateSetListener {

    @Inject
    ListPakhshViewModel mListPakhshViewModel;

    ActivityListPakhshBinding mActivityListPakhshBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private ListOrderMissionDetailModelAdapter mAdapter;
    private String mYear;
    private String mMonth;
    private String mDay;
    private boolean selectedDateFrom;
    private boolean selectedTimeFrom;
    private PersianCalendar now;
    private DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityListPakhshBinding = getViewDataBinding();
            mListPakhshViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            PersianCalendar persianCalendar = new PersianCalendar();
            mYear = String.valueOf(persianCalendar.getPersianYear());
            mMonth = String.valueOf(persianCalendar.getPersianMonth()+1);
            mDay = String.valueOf(persianCalendar.getPersianDay());
            if (Integer.parseInt(mMonth) < 10)
                mMonth = "0" + mMonth;
            if (Integer.parseInt(mDay) < 10)
                mDay = "0" + mDay;
            mActivityListPakhshBinding.date.setText(mYear + "/" + mMonth + "/" + mDay);
            callListPakhsh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ListPakhshActivity.class);
    }

    @Override
    public ListPakhshViewModel getViewModel() {
        return mListPakhshViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_pakhsh;
    }

    @Override
    public void callListPakhsh() {
        try {
            if (mActivityListPakhshBinding.date.getText().toString().isEmpty())
                mListPakhshViewModel.callListPakhsh(
                        mListPakhshViewModel.getDataManager().getServiceManId(), mYear + "/" + mMonth + "/" + mDay, AppConstants.REQUEST_OOGHLI);
            else
                mListPakhshViewModel.callListPakhsh(
                        mListPakhshViewModel.getDataManager().getServiceManId(), mActivityListPakhshBinding.date.getText().toString(), AppConstants.REQUEST_OOGHLI);
            mListPakhshViewModel.getOrderMissionDetailModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ListPakhshActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(List<OrderMissionDetailModel> data) {
        if (data != null) {
            setListParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(ListPakhshActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setListParameter(List<OrderMissionDetailModel> data) {
        if (data.size() == 0) {
            CommonUtils.showSingleButtonAlert(ListPakhshActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);
            return;
        }
        recyclerViewListOrderMissionDetailModel = mActivityListPakhshBinding.list;
        layoutOrderMissionDetailModel = new LinearLayoutManager(this);
        recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
        mAdapter = new ListOrderMissionDetailModelAdapter(data);
        recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new ListOrderMissionDetailModelAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position) {
                ShowOrderActivity.orderMissionDetail = data.get(position);
                ShowOrderActivity.isFromSum =false;
                OrderActivity.isFromCustomer =false;
                startActivity(ShowOrderActivity.getStartIntent(ListPakhshActivity.this));
            }

            @Override
            public void onCallTelClick(int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0" + data.get(position).getCollectMobile()));
                startActivity(intent);
            }

            @Override
            public void onCallTelHomeClick(int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0" + data.get(position).getCollectPhone()));
                startActivity(intent);
            }
        });
    }

    public void openFromDateCalendar() {
        now = new PersianCalendar();
        dpd = DatePickerDialog.newInstance(
                this,
                now.getPersianYear(),
                now.getPersianMonth(),
                now.getPersianDay()
        );
        selectedDateFrom = true;
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String day, month;
        day = String.valueOf(dayOfMonth);
        month = String.valueOf((monthOfYear + 1));
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;
        if ((monthOfYear + 1) < 10)
            month = "0" + (monthOfYear + 1);
        String date = year + "/" + month + "/" + day;
        mActivityListPakhshBinding.date.setText(date);
        callListPakhsh();
    }


}
