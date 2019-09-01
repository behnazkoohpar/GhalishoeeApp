package com.koohpar.oghli.ui.showOrder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.koohpar.oghli.BR;
import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.LakeStatusModel;
import com.koohpar.oghli.data.model.api.OrderDetailEdit;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;
import com.koohpar.oghli.data.model.api.OrderTypeModel;
import com.koohpar.oghli.data.model.api.OrdersModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.data.model.api.ServicesModel;
import com.koohpar.oghli.data.model.api.UpdateOrder;
import com.koohpar.oghli.data.model.api.UpdateOrderDetail;
import com.koohpar.oghli.databinding.ActivityShowOrderBinding;
import com.koohpar.oghli.ui.base.BaseActivity;
import com.koohpar.oghli.ui.listSum.ListOrderMissionDetailModelAdapter;
import com.koohpar.oghli.ui.listSum.ListSumActivity;
import com.koohpar.oghli.ui.main.MainActivity;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.CommonUtils;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShowOrderActivity extends BaseActivity<ActivityShowOrderBinding, ShowOrderViewModel> implements AppConstants, ShowOrderNavigator {

    public static OrderMissionDetailModel orderMissionDetail;
    @Inject
    ShowOrderViewModel mShowOrderViewModel;
    public static boolean isFromSum = false;
    ActivityShowOrderBinding mActivityShowOrderBinding;
    private RecyclerView recyclerViewListOrderMissionDetailModel;
    private LinearLayoutManager layoutOrderMissionDetailModel;
    private ListOrderDetailModelAdapter mAdapter;
    public List<ServiceAttrib3Model> att3;
    public List<ServiceAttrib4Model> att4;
    public List<ServiceAttrib2Model> att2;
    public List<ServiceAttrib1Model> att1;
    private String serviceSelected;
    private String orderTypeSelected;
    private String citySelected;
    private String jensSelected;
    private String sheklSelected;
    private String rangSelected;
    private int numberOrderSelected;
    private List<OrderDetailModel> ordersModelsList;
    private OrderDetailModel orderDetailModelCarpet;
    private List<OrderDetailModel> ordersModelsListOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityShowOrderBinding = getViewDataBinding();
            mShowOrderViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mActivityShowOrderBinding.address.setText(orderMissionDetail.getCollectAddress());
            mActivityShowOrderBinding.telnumber.setText(orderMissionDetail.getCollectMobile());
            mActivityShowOrderBinding.numberhome.setText(orderMissionDetail.getCollectPhone());
            mActivityShowOrderBinding.name.setText(orderMissionDetail.getCustName());
            if (!isFromSum) {
                mActivityShowOrderBinding.numberOrder.setEnabled(false);
                mActivityShowOrderBinding.typeFator.setEnabled(false);
                mActivityShowOrderBinding.typeOrder.setEnabled(false);
                mActivityShowOrderBinding.btn.setVisibility(View.GONE);
                mActivityShowOrderBinding.editOrder.setVisibility(View.GONE);
            }
            callListTypeFactor();
            callListService();

            if (orderMissionDetail.getCustomerId() == null) {
                mActivityShowOrderBinding.btn.setVisibility(View.GONE);
            }
            if (isFromSum) {
                mActivityShowOrderBinding.result.setText("دلایل عدم جمع");
                mActivityShowOrderBinding.sumOrder.setVisibility(View.VISIBLE);
            } else {
                mActivityShowOrderBinding.sumOrder.setVisibility(View.GONE);
                mActivityShowOrderBinding.result.setVisibility(View.GONE);
                mActivityShowOrderBinding.result.setText("دلایل عدم پخش");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ShowOrderActivity.class);
    }

    @Override
    public ShowOrderViewModel getViewModel() {
        return mShowOrderViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_order;
    }

    @Override
    public void addOrder() {
        OrderActivity.orderMissionDetail = orderMissionDetail;
        startActivity(OrderActivity.getStartIntent(ShowOrderActivity.this));
    }

    private void callShowOrderDetail() {
        try {
            mShowOrderViewModel.callShowOrder(orderMissionDetail.getOrderID(), AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getOrdersModelMutableLiveData().observe(this, this::receivedDataShowOrderDetail);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataShowOrderDetail(List<OrderDetailModel> data) {
        if (data != null) {
            setParameter(data);
        } else {
            setNumberOrder();
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void setParameter(List<OrderDetailModel> ordersModels) {
        if (ordersModels.size() > 0) {
            if (ordersModels != null)
                mActivityShowOrderBinding.name.setText(orderMissionDetail.getCustName());
            ordersModelsList = ordersModels;
            ordersModelsListOld = ordersModels;
            serviceSelected = ordersModels.get(0).getServiceID();
            recyclerViewListOrderMissionDetailModel = mActivityShowOrderBinding.list;
            layoutOrderMissionDetailModel = new LinearLayoutManager(this);
            recyclerViewListOrderMissionDetailModel.setLayoutManager(layoutOrderMissionDetailModel);
            mAdapter = new ListOrderDetailModelAdapter(ordersModels, att3, att2, att1, att4, isFromSum);
            recyclerViewListOrderMissionDetailModel.setAdapter(mAdapter);
            mAdapter.setOnitemclickListener(new ListOrderDetailModelAdapter.OnItemClickListener() {
                @Override
                public void onEditClick(int position, OrderDetailEdit orderDetailEdit) {
                    CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.do_edit_card), null, new CommonUtils.IL() {
                        @Override
                        public void onSuccess() {
                            callEditOrderDetail(orderDetailEdit);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });

                }

                @Override
                public void onDeleteClick(int position, OrderDetailModel orderDetailModel) {
                    CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.do_delete_card), null, new CommonUtils.IL() {
                        @Override
                        public void onSuccess() {
                            callDeleteOrderDetail(orderDetailModel);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }

                @Override
                public void onAdamPakhshClick(int position, OrderDetailModel orderDetailModel) {
                    callAdamPakhshCarpet(orderDetailModel);
                }

                @Override
                public void onPakhshOkClick(int position, OrderDetailModel orderDetailModel) {
                    orderDetailModelCarpet = orderDetailModel;
                    LakeStatusModel lakeStatusModel = new LakeStatusModel();
                    lakeStatusModel.setLakeStatusCode(11);
                    updateOrderStatusPakhsh(lakeStatusModel);
                }
            });
            setNumberOrder();
        } else
            setNumberOrder();
    }

    private void callDeleteOrderDetail(OrderDetailModel orderDetailModel) {
        try {
            mShowOrderViewModel.callDeleteOrderDetail(AppConstants.REQUEST_OOGHLI, orderDetailModel);
            mShowOrderViewModel.getDeleteOrderDetailMutableLiveData().observe(this, this::receivedDataDelete);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataDelete(Boolean data) {
        if (data) {
            //TODO: delete from list or refresh layout
            finish();
            startActivity(getIntent());
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callEditOrderDetail(OrderDetailEdit orderDetailEdit) {
        try {
            mShowOrderViewModel.callEditOrderDetail(AppConstants.REQUEST_OOGHLI, orderDetailEdit);
            mShowOrderViewModel.getEditDetailMutableLiveData().observe(this, this::receivedDataEdit);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataEdit(Boolean data) {
        if (data) {
            //TODO: delete from list or refresh layout
            finish();
            startActivity(getIntent());
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    @Override
    public void result() {
        if (isFromSum)
            callAdamSum();
        else
            callAdamPakhsh();
    }


    @Override
    public void sumOrder() {
        try {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_changed), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {
                    LakeStatusModel lakeStatusModel = new LakeStatusModel();
                    lakeStatusModel.setLakeStatusCode(3);
                    updateOrderStatus(lakeStatusModel);
                }

                @Override
                public void onCancel() {

                }
            });

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    @Override
    public void editOrder() {
        try {
            OrdersModel ordersModel = new OrdersModel();
            List<OrderDetailModel> orderDetailModels = new ArrayList<>();

            for (int i = 0; i < numberOrderSelected; i++) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setUnitPrice(0f);
                orderDetailModels.add(orderDetailModel);
            }
            ordersModel.setOrdersID(orderMissionDetail.getOrderID());
            ordersModel.setOrderTypeCode(Integer.parseInt(orderTypeSelected));
            ordersModel.setOrdersCount(numberOrderSelected);
            ordersModel.setLastUpdatedBy(mShowOrderViewModel.getDataManager().getServiceManId());
            ordersModel.setLstOrderDetail(orderDetailModels);

            mShowOrderViewModel.callEditOrder(AppConstants.REQUEST_OOGHLI, ordersModel);
            mShowOrderViewModel.getEditOrderMutableLiveData().observe(this, this::receivedDataSumEditOrder);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataSumEditOrder(Boolean aBoolean) {
        if (aBoolean) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_chaged), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {
                    finish();
                    startActivity(getIntent());
                }

                @Override
                public void onCancel() {
                    finish();
                    startActivity(getIntent());
                }
            });

        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_receive_error), null, null);

        }
    }

    private void callAdamSum() {
        try {
            mShowOrderViewModel.callAdamSum(1, AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getAdamSumMutableLiveData().observe(this, this::receivedDataSum);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataSum(List<LakeStatusModel> lakeStatusModels) {
        if (lakeStatusModels.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShowOrderActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(ShowOrderActivity.this);

            for (int i = 0; i < lakeStatusModels.size(); i++)
                arrayAdapter.add(lakeStatusModels.get(i).getLakeStatusName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    updateOrderStatus(lakeStatusModels.get(which));
                }
            });
            builderSingle.show();
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);

        }
    }

    private void updateOrderStatus(LakeStatusModel lakeStatusModel) {
        try {
            UpdateOrder updateOrder = new UpdateOrder();
            updateOrder.OrdersID = ordersModelsList.get(0).getOrdersID();
            updateOrder.setCollectLakeStatusCode(String.valueOf(lakeStatusModel.getLakeStatusCode()));
            updateOrder.setOrderStatusID(String.valueOf(lakeStatusModel.getLakeStatusCode()));
            updateOrder.setReleaseLakeStatusCode(String.valueOf(0));
            mShowOrderViewModel.updateOrderStatus(AppConstants.REQUEST_OOGHLI, updateOrder);
            mShowOrderViewModel.getUpdateOrderStatusMutableLiveData().observe(this, this::receivedUpdateOrderStatus);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedUpdateOrderStatus(Boolean aBoolean) {
        if (aBoolean) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_chaged), null, new CommonUtils.IL() {
                @Override
                public void onSuccess() {
//                    startActivity(ListSumActivity.getStartIntent(ShowOrderActivity.this));
//                    finish();
                }

                @Override
                public void onCancel() {
//                    startActivity(ListSumActivity.getStartIntent(ShowOrderActivity.this));
//                    finish();
                }
            });

        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);

        }
    }

    private void callAdamPakhsh() {
        try {
            mShowOrderViewModel.callAdamPakhsh(2, AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getAdamPakhshMutableLiveData().observe(this, this::receivedDataPakhsh);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataPakhsh(List<LakeStatusModel> lakeStatusModels) {
        if (lakeStatusModels.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShowOrderActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(ShowOrderActivity.this);

            for (int i = 0; i < lakeStatusModels.size(); i++)
                arrayAdapter.add(lakeStatusModels.get(i).getLakeStatusName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    updateOrderStatusPakhsh(lakeStatusModels.get(which));
                }
            });
            builderSingle.show();
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);

        }
    }

    private void updateOrderStatusPakhsh(LakeStatusModel lakeStatusModel) {
        try {
            UpdateOrderDetail updateOrderDetail = new UpdateOrderDetail();
            updateOrderDetail.setOrderDetailID(orderDetailModelCarpet.getOrderDetailID());
            updateOrderDetail.setOrderStatusID(String.valueOf(lakeStatusModel.getLakeStatusCode()));
            updateOrderDetail.setLakeDetailStatusCode(String.valueOf(lakeStatusModel.getLakeStatusCode()));
            mShowOrderViewModel.updateOrderDetailStatus(AppConstants.REQUEST_OOGHLI, updateOrderDetail);
            mShowOrderViewModel.getUpdateOrderDetailStatusMutableLiveData().observe(this, this::receivedUpdateOrderStatus);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void callAdamPakhshCarpet(OrderDetailModel orderDetailModel) {
        try {
            orderDetailModelCarpet = orderDetailModel;
            mShowOrderViewModel.callAdamPakhsh(2, AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getAdamPakhshMutableLiveData().observe(this, this::receivedDataPakhshCarpet);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataPakhshCarpet(List<LakeStatusModel> lakeStatusModels) {
        if (lakeStatusModels.size() > 0) {
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShowOrderActivity.this, android.R.layout.select_dialog_singlechoice);
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(ShowOrderActivity.this);

            for (int i = 0; i < lakeStatusModels.size(); i++)
                arrayAdapter.add(lakeStatusModels.get(i).getLakeStatusName());
            builderSingle.setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    updateOrderStatusPakhsh(lakeStatusModels.get(which));
                }
            });
            builderSingle.show();
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_is_null), null, null);
        }
    }

    private void callListService() {
        try {
            mShowOrderViewModel.callListService(AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getServicesModelMutableLiveData().observe(this, this::receivedData);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
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
            mActivityShowOrderBinding.typeOrder.setAdapter(adapter);
            mActivityShowOrderBinding.typeOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    serviceSelected = data.get(position).getServiceID();
                    callCity();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            });
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callListTypeFactor() {
        try {
            mShowOrderViewModel.callTypeFactor(AppConstants.REQUEST_OOGHLI);
            mShowOrderViewModel.getOrderTypeModelMutableLiveData().observe(this, this::receivedDataTypeFactor);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
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
            mActivityShowOrderBinding.typeFator.setAdapter(adapter);
            mActivityShowOrderBinding.typeFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callCity() {
        try {
            mShowOrderViewModel.callCity(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mShowOrderViewModel.getCityModelMutableLiveData().observe(this, this::receivedDataCity);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataCity(List<ServiceAttrib3Model> data) {
        if (data != null) {
            att3 = data;
//            String[] datas = new String[data.size()];
//            for (int i = 0; i < data.size(); i++) {
//                datas[i] = data.get(i).getServiceAttribTitle();
//            }
//            citySelected = String.valueOf(data.get(0).getServiceAttrib3ID());
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
//            mActivityShowOrderBinding.city.setAdapter(adapter);
//            mActivityShowOrderBinding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                    citySelected = String.valueOf(data.get(position).getServiceAttrib3ID());
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // TODO Auto-generated method stub
//                }
//            });
            callJens();

        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callJens() {
        try {
            mShowOrderViewModel.callJens(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mShowOrderViewModel.getJensModelMutableLiveData().observe(this, this::receivedDataJens);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataJens(List<ServiceAttrib2Model> data) {
        if (data != null) {
            att2 = data;
//            String[] datas = new String[data.size()];
//            for (int i = 0; i < data.size(); i++) {
//                datas[i] = data.get(i).getServiceAttribTitle();
//            }
//            jensSelected = String.valueOf(data.get(0).getServiceAttrib2ID());
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
//            mActivityShowOrderBinding.jensGhali.setAdapter(adapter);
//            mActivityShowOrderBinding.jensGhali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                    jensSelected = String.valueOf(data.get(position).getServiceAttrib2ID());
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // TODO Auto-generated method stub
//                }
//            });
            callShekl();

        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callShekl() {
        try {
            mShowOrderViewModel.callShekl(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mShowOrderViewModel.getSheklModelMutableLiveData().observe(this, this::receivedDataShekl);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataShekl(List<ServiceAttrib1Model> data) {
        if (data != null) {
            att1 = data;
//            String[] datas = new String[data.size()];
//            for (int i = 0; i < data.size(); i++) {
//                datas[i] = data.get(i).getServiceAttribTitle();
//            }
//            sheklSelected = String.valueOf(data.get(0).getServiceAttrib1ID());
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
//            mActivityShowOrderBinding.form.setAdapter(adapter);
//            mActivityShowOrderBinding.form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                    sheklSelected = String.valueOf(data.get(position).getServiceAttrib1ID());
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // TODO Auto-generated method stub
//                }
//            });
            callRang();

        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    private void callRang() {
        try {
            mShowOrderViewModel.callRang(AppConstants.REQUEST_OOGHLI, serviceSelected);
            mShowOrderViewModel.getRangModelMutableLiveData().observe(this, this::receivedDataRang);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedDataRang(List<ServiceAttrib4Model> data) {
        if (data != null) {
            att4 = data;
//            String[] datas = new String[data.size()];
//            for (int i = 0; i < data.size(); i++) {
//                datas[i] = data.get(i).getServiceAttribTitle();
//            }
//            rangSelected = String.valueOf(data.get(0).getServiceAttrib4ID());
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
//            mActivityShowOrderBinding.colorFator.setAdapter(adapter);
//            mActivityShowOrderBinding.colorFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                    rangSelected = String.valueOf(data.get(position).getServiceAttrib4ID());
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // TODO Auto-generated method stub
//                }
//            });
            callShowOrderDetail();
        } else {
            CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.problem), null, null);
        }
    }

    public void setNumberOrder() {
        String[] datas = new String[21];
        for (int i = 0; i < datas.length; i++)
            datas[i] = String.valueOf(i);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datas);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_text_color);
        mActivityShowOrderBinding.numberOrder.setAdapter(adapter);
        if (ordersModelsList != null && ordersModelsList.size() >= 1)
            mActivityShowOrderBinding.numberOrder.setSelection(ordersModelsList.size());
        else
            mActivityShowOrderBinding.numberOrder.setSelection(0);
        mActivityShowOrderBinding.numberOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (ordersModelsList != null && mActivityShowOrderBinding.numberOrder.getSelectedItemPosition() < (ordersModelsList.size() - 1))
                    CommonUtils.showSingleButtonAlert(ShowOrderActivity.this, getString(R.string.text_attention), getString(R.string.data_delete_error), null, new CommonUtils.IL() {
                        @Override
                        public void onSuccess() {
                            mActivityShowOrderBinding.numberOrder.setSelection(ordersModelsList.size() - 1);
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                else
                    numberOrderSelected = Integer.parseInt(datas[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}

