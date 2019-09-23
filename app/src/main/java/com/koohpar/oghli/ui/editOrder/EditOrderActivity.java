package com.koohpar.oghli.ui.editOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.Customer;
import com.koohpar.oghli.data.model.api.OrderDetailEdit;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.RofuAttribModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.data.model.api.ServicesModel;
import com.koohpar.oghli.databinding.ActivityEditOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.main.MainActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EditOrderActivity extends BaseActivity<ActivityEditOrderBinding, EditOrderViewModel> implements AppConstants, EditOrderNavigator {

    public static OrderMissionDetailModel orderMissionDetail;
    public static Customer customerModel;
    public static boolean isFromCustomer;
    @Inject
    EditOrderViewModel mEditOrderViewModel;
    public String orderID;

    public static List<ServiceAttrib3Model> att3;
    public static List<ServiceAttrib4Model> att4;
    public static List<ServiceAttrib2Model> att2;
    public static List<ServiceAttrib1Model> att1;
    public static List<RofuAttribModel> rofulist;
    ActivityEditOrderBinding mActivityEditOrderBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private EditOrderDetailAdapter mAdapter;
    private String serviceSelected;
    private String orderTypeSelected;
    private int numberOrderSelected;
    private List<OrderDetailModel> ordersModelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityEditOrderBinding = getViewDataBinding();
            mEditOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            recyclerViewListOrderMissionDetailModel = mActivityEditOrderBinding.list;
            layoutOrderMissionDetailModel = new LinearLayoutManager(this);
            recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
            callListTypeFactor();
            callListService();
            setNumberOrder();
            if (isFromCustomer) {
                mActivityEditOrderBinding.address.setText(customerModel.getCollectAddress());
                mActivityEditOrderBinding.telnumber.setText(customerModel.getCollectMobile());
                mActivityEditOrderBinding.numberhome.setText(customerModel.getCollectPhone());
                mActivityEditOrderBinding.name.setText(customerModel.getCustName());
            } else {
                mActivityEditOrderBinding.address.setText(orderMissionDetail.getCollectAddress());
                mActivityEditOrderBinding.telnumber.setText(orderMissionDetail.getCollectMobile());
                mActivityEditOrderBinding.numberhome.setText(orderMissionDetail.getCollectPhone());
                mActivityEditOrderBinding.name.setText(orderMissionDetail.getCustName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, EditOrderActivity.class);
    }

    @Override
    public EditOrderViewModel getViewModel() {
        return mEditOrderViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_order;
    }

    @Override
    public void closed() {
        startActivity(MainActivity.getStartIntent(EditOrderActivity.this));
    }

    @Override
    public void callEditOrderDetail() {
        try {
            if (orderID != null) {
                int number = mActivityEditOrderBinding.numberOrder.getSelectedItemPosition();
                OrdersModel ordersModel = new OrdersModel();
                List<OrderDetailModel> orderDetailModels = new ArrayList<>();

                for (int i = 0; i < number; i++) {
                    OrderDetailModel orderDetailModel = new OrderDetailModel();
                    orderDetailModel.setUnitPrice(0f);
                    orderDetailModels.add(orderDetailModel);
                }
                ordersModel.setOrdersID(orderID);
                ordersModel.setOrderTypeCode(Integer.parseInt(orderTypeSelected));
                ordersModel.setOrdersCount(number);
                ordersModel.setLastUpdatedBy(mEditOrderViewModel.getDataManager().getServiceManId());
                ordersModel.setLstOrderDetail(orderDetailModels);

                mEditOrderViewModel.callEditOrder(AppConstants.REQUEST_OOGHLI, ordersModel);
                mEditOrderViewModel.getEditDetailMutableLiveData().observe(this, this::receivedDataEdit);
            } else {
                addOrder();
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataEdit(Boolean data) {
//        if (data != null) {
//            setParameter(data);
//        } else {
//            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
//        }

        if (data) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_chaged), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {
                    callRetriveOrder();
                }

                @Override
                public void onCancel() {
                    callRetriveOrder();
                }
            });

        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_receive_error), null, null);

        }
    }

    private void setParameter(List<OrderDetailModel> ordersModels) {
        if (ordersModels.size() > 0) {
            if (ordersModels != null) {
                if (isFromCustomer)
                    mActivityEditOrderBinding.name.setText(customerModel.getCustName());
                else
                    mActivityEditOrderBinding.name.setText(orderMissionDetail.getCustName());
            }
            mAdapter = null;
            mAdapter = new EditOrderDetailAdapter(ordersModels, att3, rofulist, att2, att1, att4);
            recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            mAdapter.setOnitemclickListener(new EditOrderDetailAdapter.OnItemClickListener() {
                @Override
                public void onEditClick(int position, OrderDetailEdit orderDetailEdit) {
                    CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.do_edit_card), null, new CommonUtils.IL() {
                        @Override
                        public void onSuccess() {
                            callEditDetail(orderDetailEdit);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
            });
        }
    }

    private void callEditDetail(OrderDetailEdit orderDetailEdit) {
        try {
            mEditOrderViewModel.callEditDetail(orderDetailEdit, AppConstants.REQUEST_OOGHLI);
            mEditOrderViewModel.getEditDetail2MutableLiveData().observe(this, this::receivedDataEditDetail);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataEditDetail(boolean aBoolean) {
        if (aBoolean) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_chaged), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {

//                    callRetriveOrder();
                }

                @Override
                public void onCancel() {

//                    callRetriveOrder();
                }
            });

        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_receive_error), null, null);

        }
    }

    private void callListService() {
        try {
            mEditOrderViewModel.callListService(AppConstants.REQUEST_OOGHLI);
            mEditOrderViewModel.getServicesModelMutableLiveData().observe(this, this::receivedData);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
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
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            mActivityEditOrderBinding.typeOrder.setAdapter(adapter);
            mActivityEditOrderBinding.typeOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    serviceSelected = data.get(position).getServiceID();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callListTypeFactor() {
        try {
            mEditOrderViewModel.callTypeFactor(AppConstants.REQUEST_OOGHLI);
            mEditOrderViewModel.getOrderTypeModelMutableLiveData().observe(this, this::receivedDataTypeFactor);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
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

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            mActivityEditOrderBinding.typeFator.setAdapter(adapter);
            mActivityEditOrderBinding.typeFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    public void setNumberOrder() {
        String[] datas = new String[21];
        for (int i = 0; i < datas.length; i++)
            datas[i] = String.valueOf(i);
        numberOrderSelected = Integer.parseInt(datas[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_text_color);
        mActivityEditOrderBinding.numberOrder.setAdapter(adapter);
        mActivityEditOrderBinding.numberOrder.setSelection(ordersModelsList.size());
        mActivityEditOrderBinding.numberOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void addOrder() {
        try {
            List<OrderDetailModel> orderDetailModels = new ArrayList<>();
            int number = mActivityEditOrderBinding.numberOrder.getSelectedItemPosition();
            for (int i = 0; i < number; i++) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
//                orderDetailModel.setLenght(Integer.parseInt(mActivityOrderBinding.tool.getText().toString()));
//                orderDetailModel.setWidth(Integer.parseInt(mActivityOrderBinding.arz.getText().toString()));
                orderDetailModel.setUnitPrice(0f);
//                orderDetailModel.setServiceAttrib1ID(sheklSelected);
//                orderDetailModel.setServiceAttrib2ID(jensSelected);
//                orderDetailModel.setServiceAttrib3ID(citySelected);
//                orderDetailModel.setServiceAttrib4ID(rangSelected);
                orderDetailModels.add(orderDetailModel);
            }

//            PersianCalendar persianCalendar = new PersianCalendar();
//            String mYear = String.valueOf(persianCalendar.getPersianYear());
//            String mMonth = String.valueOf(persianCalendar.getPersianMonth() + 1);
//            String mDay = String.valueOf(persianCalendar.getPersianDay());
//            if (Integer.parseInt(mMonth) < 10)
//                mMonth = "0" + mMonth;
//            if (Integer.parseInt(mDay) < 10)
//                mDay = "0" + mDay;
//            String date = mYear + "/" + mMonth + "/" + mDay;

            OrdersModel ordersModel = new OrdersModel();
            if (isFromCustomer)
                ordersModel.setCustomerID(customerModel.getCustomerID());
            else
                ordersModel.setCustomerID(orderMissionDetail.getCustomerId());
            ordersModel.setServicesID(serviceSelected);
            ordersModel.setCollectServiceManID(mEditOrderViewModel.getDataManager().getServiceManId());

            ordersModel.setCreatedBy(mEditOrderViewModel.getDataManager().getServiceManId());
            ordersModel.setLstOrderDetail(orderDetailModels);
            ordersModel.setOrdersCount(number);
            ordersModel.setOrderTypeCode(Integer.parseInt(orderTypeSelected));
            mEditOrderViewModel.addNewOrder(AppConstants.REQUEST_OOGHLI, ordersModel);
            mEditOrderViewModel.getInsertOrderMutableLiveData().observe(this, this::receivedDataInsertOrder);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataInsertOrder(String s) {
        if (!s.equals("00000000-0000-0000-0000-000000000000")) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_record), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {
                    orderID = s;
                    callRetriveOrder();
                }

                @Override
                public void onCancel() {
                    orderID = s;
                    callRetriveOrder();
                }
            });

        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callRetriveOrder() {
        try {
            mEditOrderViewModel.callRetriveOrder(orderID, AppConstants.REQUEST_OOGHLI);
            mEditOrderViewModel.getOrdersModelMutableLiveData().observe(this, this::receivedRetrived);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedRetrived(List<OrderDetailModel> orderDetailModels) {
        if (orderDetailModels != null) {
            setParameter(orderDetailModels);
        } else {
            CommonUtils.showSingleButtonAlert(EditOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

}

