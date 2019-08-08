package com.koohpar.oghli.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.data.model.api.ServicesModel;
import com.koohpar.oghli.databinding.ActivityOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.editOrder.EditOrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class OrderActivity extends BaseActivity<ActivityOrderBinding, OrderViewModel> implements AppConstants, OrderNavigator {

    public static String customerId, name, telNumber, numberHome, address;
    @Inject
    OrderViewModel mOrderViewModel;
    ActivityOrderBinding mActivityOrderBinding;
    private String serviceSelected;
    private int numberOrderSelected = 1;
    private String orderTypeSelected;
    private String citySelected;
    private String jensSelected;
    private String sheklSelected;
    private String rangSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityOrderBinding = getViewDataBinding();
            mOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mActivityOrderBinding.name.setText(name);
            mActivityOrderBinding.numberhome.setText(numberHome);
            mActivityOrderBinding.telnumber.setText(telNumber);
            mActivityOrderBinding.address.setText(address);
            callListService();
            setNumberOrder();
            callListTypeFactor();

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

    private void callListService() {
        try {
            mOrderViewModel.callListService(AppConstants.REQUEST_OOGHLI);
            mOrderViewModel.getServicesModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(List<ServicesModel> data) {
        if (data != null) {
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceName();
            }
            serviceSelected = data.get(0).getServiceID();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivityOrderBinding.typeOrder.setAdapter(adapter);

            callCity();
            callJens();
            callShekl();
            callRang();

            mActivityOrderBinding.typeOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    serviceSelected = data.get(position).getServiceID();
                    callCity();
                    callJens();
                    callShekl();
                    callRang();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callListTypeFactor() {
        try {
            mOrderViewModel.callTypeFactor(AppConstants.REQUEST_OOGHLI);
            mOrderViewModel.getOrderTypeModelMutableLiveData().observe(this, this::receivedDataTypeFactor);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataTypeFactor(List<OrderTypeModel> data) {
        if (data != null) {
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getOrderTypeName();
            }
            orderTypeSelected = String.valueOf(data.get(0).getOrderTypeCode());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivityOrderBinding.typeFator.setAdapter(adapter);
            mActivityOrderBinding.typeFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    orderTypeSelected = String.valueOf(data.get(position).getOrderTypeCode());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callCity() {
        try {
            mOrderViewModel.callCity(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mOrderViewModel.getCityModelMutableLiveData().observe(this, this::receivedDataCity);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataCity(List<ServiceAttrib3Model> data) {
        if (data != null) {
            EditOrderActivity.att3 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            citySelected = String.valueOf(data.get(0).getServiceAttrib3ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivityOrderBinding.city.setAdapter(adapter);
            mActivityOrderBinding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    citySelected = String.valueOf(data.get(position).getServiceAttrib3ID());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callJens() {
        try {
            mOrderViewModel.callJens(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mOrderViewModel.getJensModelMutableLiveData().observe(this, this::receivedDataJens);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataJens(List<ServiceAttrib2Model> data) {
        if (data != null) {
            EditOrderActivity.att2 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            jensSelected = String.valueOf(data.get(0).getServiceAttrib2ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivityOrderBinding.jensGhali.setAdapter(adapter);
            mActivityOrderBinding.jensGhali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    jensSelected = String.valueOf(data.get(position).getServiceAttrib2ID());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callShekl() {
        try {
            mOrderViewModel.callShekl(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mOrderViewModel.getSheklModelMutableLiveData().observe(this, this::receivedDataShekl);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataShekl(List<ServiceAttrib1Model> data) {
        if (data != null) {
            EditOrderActivity.att1 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            sheklSelected = String.valueOf(data.get(0).getServiceAttrib1ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivityOrderBinding.form.setAdapter(adapter);
            mActivityOrderBinding.form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    sheklSelected = String.valueOf(data.get(position).getServiceAttrib1ID());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callRang() {
        try {
            mOrderViewModel.callRang(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mOrderViewModel.getRangModelMutableLiveData().observe(this, this::receivedDataRang);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataRang(List<ServiceAttrib4Model> data) {
        if (data != null) {
            EditOrderActivity.att4 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            rangSelected = String.valueOf(data.get(0).getServiceAttrib4ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
            mActivityOrderBinding.colorFator.setAdapter(adapter);
            mActivityOrderBinding.colorFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    rangSelected = String.valueOf(data.get(position).getServiceAttrib4ID());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    public void setNumberOrder() {
        String[] datas = new String[20];
        for (int i = 0; i < datas.length; i++)
            datas[i] = String.valueOf(i + 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
        mActivityOrderBinding.numberOrder.setAdapter(adapter);
        mActivityOrderBinding.numberOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                numberOrderSelected = Integer.parseInt(datas[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void addOrder() {
        try {
            List<OrderDetailModel> orderDetailModels = new ArrayList<>();

            for (int i = 0; i < numberOrderSelected; i++) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setLenght(Integer.parseInt(mActivityOrderBinding.tool.getText().toString()));
                orderDetailModel.setWidth(Integer.parseInt(mActivityOrderBinding.arz.getText().toString()));
                orderDetailModel.setUnitPrice(Float.parseFloat(mActivityOrderBinding.price.getText().toString()));
                orderDetailModel.setServiceAttrib1ID(sheklSelected);
                orderDetailModel.setServiceAttrib2ID(jensSelected);
                orderDetailModel.setServiceAttrib3ID(citySelected);
                orderDetailModel.setServiceAttrib4ID(rangSelected);
                orderDetailModels.add(orderDetailModel);
            }

            PersianCalendar persianCalendar = new PersianCalendar();
            String mYear = String.valueOf(persianCalendar.getPersianYear());
            String mMonth = String.valueOf(persianCalendar.getPersianMonth());
            String mDay = String.valueOf(persianCalendar.getPersianDay());
            if (Integer.parseInt(mMonth) < 10)
                mMonth = "0" + mMonth;
            if (Integer.parseInt(mDay) < 10)
                mDay = "0" + mDay;
            String date = mYear + "/" + mMonth + "/" + mDay;
            OrdersModel ordersModel = new OrdersModel();
            ordersModel.setCustName(name);
            ordersModel.setCustomerID(customerId);
            ordersModel.setServicesID(serviceSelected);
            ordersModel.setCollectServiceManID(mOrderViewModel.getDataManager().getServiceManId());
            ordersModel.setCollectTime(String.valueOf(persianCalendar.getTime()));
            ordersModel.setCollectAddress(mActivityOrderBinding.addressSum.getText().toString());
            ordersModel.setCollectMobile(mActivityOrderBinding.tel.getText().toString());
            ordersModel.setCollectPhone(mActivityOrderBinding.telHome.getText().toString());
            ordersModel.setReleaseAddress(mActivityOrderBinding.addressPakhsh.getText().toString());
            ordersModel.setReleaseMobile(mActivityOrderBinding.telpakhsh.getText().toString());
            ordersModel.setReleasePhone(mActivityOrderBinding.telHomePakhsh.getText().toString());
            ordersModel.setCreatedBy(mOrderViewModel.getDataManager().getServiceManId());
            ordersModel.setCreatedDate(date);
            ordersModel.setLstOrderDetail(orderDetailModels);
            ordersModel.setOrdersCount(numberOrderSelected);
            ordersModel.setServiceCode(orderTypeSelected);
            mOrderViewModel.addNewOrder(AppConstants.REQUEST_OOGHLI, ordersModel);
            mOrderViewModel.getInsertOrderMutableLiveData().observe(this, this::receivedDataInsertOrder);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataInsertOrder(String s) {
        if (s != null) {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.data_record), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {
                    EditOrderActivity.orderId = s;
                    EditOrderActivity.customerId = customerId;
                    EditOrderActivity.name = name;
                    EditOrderActivity.address = address;
                    EditOrderActivity.tel = telNumber;
                    EditOrderActivity.telHome = numberHome;
                    startActivity(EditOrderActivity.getStartIntent(OrderActivity.this));
                }

                @Override
                public void onCancel() {
                    EditOrderActivity.orderId = s;
                    EditOrderActivity.customerId = customerId;
                    EditOrderActivity.name = name;
                    startActivity(EditOrderActivity.getStartIntent(OrderActivity.this));
                }
            });

        } else {
            CommonUtils.showSingleButtonAlert(OrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }
}

