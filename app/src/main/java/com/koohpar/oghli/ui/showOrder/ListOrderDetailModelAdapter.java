package com.koohpar.oghli.ui.showOrder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderDetailEdit;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.ServiceAttrib1Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib2Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib3Model;
import com.koohpar.oghli.data.model.api.ServiceAttrib4Model;
import com.koohpar.oghli.utils.CommonUtils;

import java.util.List;

public class ListOrderDetailModelAdapter extends RecyclerView.Adapter<ListOrderDetailModelAdapter.ViewHolder> {
    private final boolean isfromSum;
    private List<OrderDetailModel> stList;
    private List<ServiceAttrib1Model> att1;
    private List<ServiceAttrib2Model> att2;
    private List<ServiceAttrib3Model> att3;
    private List<ServiceAttrib4Model> att4;
    public static Context context;
    private String citySelected;
    private String jensSelected;
    private String sheklSelected;
    private String rangSelected;
    private ListOrderDetailModelAdapter.OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onEditClick(int position, OrderDetailEdit orderDetailEdit);

        void onDeleteClick(int position, OrderDetailModel orderDetailModel);
        void onAdamPakhshClick(int position, OrderDetailModel orderDetailModel);
        void onPakhshOkClick(int position, OrderDetailModel orderDetailModel);
    }

    public void setOnitemclickListener(ListOrderDetailModelAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public ListOrderDetailModelAdapter(List<OrderDetailModel> SlistS, List<ServiceAttrib3Model> att3,
                                       List<ServiceAttrib2Model> att2, List<ServiceAttrib1Model> att1,
                                       List<ServiceAttrib4Model> att4, boolean isFromSum) {
        this.isfromSum = isFromSum;
        this.att1 = att1;
        this.att2 = att2;
        this.att3 = att3;
        this.att4 = att4;
        stList = SlistS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final Spinner typeOrder, city, jensGhali, form, colorFator;
        public final TextView price, tool, arz, sumPrice;
        private final Button editCarpet, deleteCarpet, pakhshOk, adamPakhsh;
        private final CardView card_view;

        public ViewHolder(final View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            typeOrder = (Spinner) itemView.findViewById(R.id.typeOrder);
            city = (Spinner) itemView.findViewById(R.id.city);
            jensGhali = (Spinner) itemView.findViewById(R.id.jensGhali);
            form = (Spinner) itemView.findViewById(R.id.form);
            colorFator = (Spinner) itemView.findViewById(R.id.colorFator);
            price = (TextView) itemView.findViewById(R.id.price);
            tool = (TextView) itemView.findViewById(R.id.tool);
            arz = (TextView) itemView.findViewById(R.id.arz);
            sumPrice = (TextView) itemView.findViewById(R.id.sumPrice);
            editCarpet = (Button) itemView.findViewById(R.id.editCarpet);
            deleteCarpet = (Button) itemView.findViewById(R.id.deleteCarpet);
            pakhshOk = (Button) itemView.findViewById(R.id.pakhshOk);
            adamPakhsh = (Button) itemView.findViewById(R.id.adamPakhsh);
        }
    }

    private void receivedDataCity(final ListOrderDetailModelAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib3Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    datas[i] = data.get(i).getServiceAttribTitle();
                }
//                citySelected = String.valueOf(data.get(0).getServiceAttrib3ID());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                viewHolder.city.setAdapter(adapter);
//                viewHolder.city.setSelection(position);
                viewHolder.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataJens(final ListOrderDetailModelAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib2Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    datas[i] = data.get(i).getServiceAttribTitle();
                }
//            jensSelected = String.valueOf(data.get(0).getServiceAttrib2ID());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                viewHolder.jensGhali.setAdapter(adapter);
//            viewHolder.jensGhali.setSelection(position);
                viewHolder.jensGhali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataShekl(final ListOrderDetailModelAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib1Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    datas[i] = data.get(i).getServiceAttribTitle();
                }
//            sheklSelected = String.valueOf(data.get(0).getServiceAttrib1ID());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                viewHolder.form.setAdapter(adapter);
//            viewHolder.form.setSelection(position);
                viewHolder.form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataRang(final ListOrderDetailModelAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib4Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    datas[i] = data.get(i).getServiceAttribTitle();
                }
//            rangSelected = String.valueOf(data.get(position).getServiceAttrib4ID());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                viewHolder.colorFator.setAdapter(adapter);
//            viewHolder.colorFator.setSelection(position);
                viewHolder.colorFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListOrderDetailModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, parent, false);
        ListOrderDetailModelAdapter.ViewHolder viewHolder = new ListOrderDetailModelAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListOrderDetailModelAdapter.ViewHolder viewHolder, final int position) {
        receivedDataCity(viewHolder, position, att3);
        receivedDataJens(viewHolder, position, att2);
        receivedDataShekl(viewHolder, position, att1);
        receivedDataRang(viewHolder, position, att4);
        viewHolder.price.setText(String.valueOf(stList.get(position).getUnitPrice()));
        viewHolder.tool.setText(String.valueOf(stList.get(position).getLenght()));
        viewHolder.arz.setText(String.valueOf(stList.get(position).getWidth()));
        viewHolder.sumPrice.setText(String.valueOf(stList.get(position).getOrderPrice()));
        if (stList.get(position).getOrderStatusID() == 3 || stList.get(position).getOrderStatusID() == 11)
            viewHolder.card_view.setCardBackgroundColor(Color.rgb(186,236,164));

        if (stList.get(position).getOrderStatusID() == 1 || stList.get(position).getOrderStatusID() == 9)
            viewHolder.card_view.setCardBackgroundColor(Color.rgb(255,204,204));
        if (isfromSum) {
            viewHolder.adamPakhsh.setVisibility(View.GONE);
            viewHolder.pakhshOk.setVisibility(View.GONE);
            viewHolder.deleteCarpet.setVisibility(View.VISIBLE);
            viewHolder.editCarpet.setVisibility(View.VISIBLE);
        } else {
            viewHolder.adamPakhsh.setVisibility(View.VISIBLE);
            viewHolder.pakhshOk.setVisibility(View.VISIBLE);
            viewHolder.deleteCarpet.setVisibility(View.GONE);
            viewHolder.editCarpet.setVisibility(View.GONE);
            if (stList.get(position).getLakeDetailStatusCode() == 0)
                viewHolder.card_view.setCardBackgroundColor(Color.rgb(255,204,204));
        }

        viewHolder.editCarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderDetailEdit orderDetailEdit = new OrderDetailEdit();
                orderDetailEdit.setOrderDetailID(stList.get(position).getOrderDetailID());
                orderDetailEdit.setUnitPrice(Float.parseFloat(viewHolder.price.getText().toString()));
                orderDetailEdit.setServiceAttrib1ID(sheklSelected);
                orderDetailEdit.setServiceAttrib2ID(jensSelected);
                orderDetailEdit.setServiceAttrib3ID(citySelected);
                orderDetailEdit.setServiceAttrib4ID(rangSelected);
                orderDetailEdit.setWidth(Float.parseFloat(viewHolder.arz.getText().toString()));
                orderDetailEdit.setLenght(Float.parseFloat(viewHolder.tool.getText().toString()));
                orderDetailEdit.setQuantity(12);
                mListener.onEditClick(position, orderDetailEdit);
            }
        });

        viewHolder.deleteCarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteClick(position, stList.get(position));
            }
        });

        viewHolder.adamPakhsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAdamPakhshClick(position, stList.get(position));
            }
        });

        viewHolder.pakhshOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPakhshOkClick(position, stList.get(position));
            }
        });

    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
