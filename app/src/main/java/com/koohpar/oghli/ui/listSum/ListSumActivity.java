package com.koohpar.oghli.ui.listSum;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.ServiceManModel;
import com.koohpar.oghli.databinding.ActivityListSumBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.ui.showOrder.ShowOrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.date.DatePickerDialog;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class ListSumActivity extends BaseActivity<ActivityListSumBinding, ListSumViewModel> implements AppConstants, ListSumNavigator,
        DatePickerDialog.OnDateSetListener {

    @Inject
    ListSumViewModel mListSumViewModel;

    ActivityListSumBinding mActivityListSumBinding;
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
    private String serviceManSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityListSumBinding = getViewDataBinding();
            mListSumViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            PersianCalendar persianCalendar = new PersianCalendar();
            mYear = String.valueOf(persianCalendar.getPersianYear());
            mMonth = String.valueOf(persianCalendar.getPersianMonth() + 1);
            mDay = String.valueOf(persianCalendar.getPersianDay());
            if (Integer.parseInt(mMonth) < 10)
                mMonth = "0" + mMonth;
            if (Integer.parseInt(mDay) < 10)
                mDay = "0" + mDay;
            mActivityListSumBinding.date.setText(mYear + "/" + mMonth + "/" + mDay);
            if (mListSumViewModel.getDataManager().getServiceManId() == null || mListSumViewModel.getDataManager().getServiceManId().equalsIgnoreCase("00000000-0000-0000-0000-000000000000")) {
                mActivityListSumBinding.listServiceMan.setVisibility(View.VISIBLE);
                serviceManSelected = null;
                callListServiceMan();
            } else {
                mActivityListSumBinding.listServiceMan.setVisibility(View.GONE);
                serviceManSelected=mListSumViewModel.getDataManager().getServiceManId();
                callListSum(serviceManSelected);
            }
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

    private void callListServiceMan() {
        try {
            mListSumViewModel.callListServiceMan(AppConstants.REQUEST_OOGHLI);
            mListSumViewModel.getListServiceManModelMutableLiveData().observe(this, this::receivedDataServiceMan);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataServiceMan(List<ServiceManModel> data) {
        if (data != null) {
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceManName();
            }
            serviceManSelected = String.valueOf(data.get(0).getServiceManID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            mActivityListSumBinding.listServiceMan.setAdapter(adapter);
            mActivityListSumBinding.listServiceMan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    serviceManSelected = String.valueOf(data.get(position).getServiceManID());
                    callListSum(serviceManSelected);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    @Override
    public void callSearch() {
        callListSum(serviceManSelected);
    }

    public void callListSum(String serviceManId) {
        try {
            if (mActivityListSumBinding.date.getText().toString().isEmpty())
                mListSumViewModel.callListSum(
                        serviceManId, mYear + "/" + mMonth + "/" + mDay, AppConstants.REQUEST_OOGHLI);
            else
                mListSumViewModel.callListSum(
                        serviceManId, mActivityListSumBinding.date.getText().toString(), AppConstants.REQUEST_OOGHLI);
            mListSumViewModel.getOrderMissionDetailModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(List<OrderMissionDetailModel> data) {
        if (data != null) {
            setListParameter(data);
        } else {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setListParameter(List<OrderMissionDetailModel> data) {
        if (data.size() == 0) {
            CommonUtils.showSingleButtonAlert(ListSumActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);
            return;
        }
        recyclerViewListOrderMissionDetailModel = mActivityListSumBinding.list;
        layoutOrderMissionDetailModel = new LinearLayoutManager(this);
        recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
        mAdapter = new ListOrderMissionDetailModelAdapter(data);
        recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
        mAdapter.setOnitemclickListener(new ListOrderMissionDetailModelAdapter.OnItemClickListener() {
            @Override
            public void onOpenClick(int position) {
                ShowOrderActivity.orderMissionDetail = data.get(position);
                ShowOrderActivity.isFromSum = true;
                OrderActivity.isFromCustomer = false;
                startActivity(ShowOrderActivity.getStartIntent(ListSumActivity.this));
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
        mActivityListSumBinding.date.setText(date);
        callListSum(serviceManSelected);
    }


}
