package com.koohpar.oghli.ui.showOrder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ListOrderDetailModelAdapter extends RecyclerView.Adapter<ListOrderDetailModelAdapter.ViewHolder> {
    private final boolean isfromSum;
    private List<OrderDetailModel> stList;
    private List<ServiceAttrib1Model> sheklList;//shekl
    private List<ServiceAttrib2Model> jensList;//jens
    private List<ServiceAttrib3Model> cityList;//city
    private List<ServiceAttrib4Model> rangList;//rang
    public static Context context;
    private String citySelected;
    private String jensSelected;
    private String sheklSelected;
    private String rangSelected;
    private ListOrderDetailModelAdapter.OnItemClickListener mListener;

    NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

    public interface OnItemClickListener {
        void onEditClick(int position, OrderDetailEdit orderDetailEdit);

        void onDeleteClick(int position, OrderDetailModel orderDetailModel);

        void onAdamPakhshClick(int position, OrderDetailModel orderDetailModel);

        void onPakhshOkClick(int position, OrderDetailModel orderDetailModel);
    }

    public void setOnitemclickListener(ListOrderDetailModelAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public ListOrderDetailModelAdapter(List<OrderDetailModel> SlistS, List<ServiceAttrib3Model> cityList,
                                       List<ServiceAttrib2Model> jensList, List<ServiceAttrib1Model> sheklList,
                                       List<ServiceAttrib4Model> rangList, boolean isFromSum) {
        this.isfromSum = isFromSum;
        this.sheklList = sheklList;
        this.jensList = jensList;
        this.cityList = cityList;
        this.rangList = rangList;
        stList = SlistS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final Spinner typeOrder, city, jensGhali, form, colorFator;
        public final TextView price, tool, arz, sumPrice;
        private final Button editCarpet, deleteCarpet, pakhshOk, adamPakhsh;
        private final CardView card_view;
        private final ImageView clearprice, clearsumprice;

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
            clearprice = (ImageView) itemView.findViewById(R.id.clearprice);
            clearsumprice = (ImageView) itemView.findViewById(R.id.clearsumprice);
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
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.city.setAdapter(adapter);
                for (int j = 0; j < cityList.size(); j++) {
                    if (stList.get(position).getServiceAttrib3ID().equalsIgnoreCase(cityList.get(j).getServiceAttrib3ID())) {
                        viewHolder.city.setSelection(j);
                    }
                }
//                viewHolder.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        citySelected = String.valueOf(data.get(position).getServiceAttrib3ID());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//                    }
//                });
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
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.jensGhali.setAdapter(adapter);
//            viewHolder.jensGhali.setSelection(position);
                for (int j = 0; j < jensList.size(); j++) {
                    if (stList.get(position).getServiceAttrib2ID().equalsIgnoreCase(jensList.get(j).getServiceAttrib2ID())) {
                        viewHolder.jensGhali.setSelection(j);
                    }
                }
//                viewHolder.jensGhali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        jensSelected = String.valueOf(data.get(position).getServiceAttrib2ID());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//                    }
//                });
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
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.form.setAdapter(adapter);
                for (int j = 0; j < sheklList.size(); j++) {
                    if (stList.get(position).getServiceAttrib1ID().equalsIgnoreCase(sheklList.get(j).getServiceAttrib1ID())) {
                        viewHolder.form.setSelection(j);
                    }
                }
//            viewHolder.form.setSelection(position);
//                viewHolder.form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        sheklSelected = String.valueOf(data.get(position).getServiceAttrib1ID());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//                    }
//                });
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
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.colorFator.setAdapter(adapter);
                for (int j = 0; j < rangList.size(); j++) {
                    if (stList.get(position).getServiceAttrib4ID().equalsIgnoreCase(rangList.get(j).getServiceAttrib4ID())) {
                        viewHolder.colorFator.setSelection(j);
                    }
                }
//            viewHolder.colorFator.setSelection(position);
//                viewHolder.colorFator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view,
//                                               int position, long id) {
//                        rangSelected = String.valueOf(data.get(position).getServiceAttrib4ID());
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                        // TODO Auto-generated method stub
//                    }
//                });
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
        receivedDataCity(viewHolder, position, cityList);//city
        receivedDataJens(viewHolder, position, jensList);//jens
        receivedDataShekl(viewHolder, position, sheklList);//shekl
        receivedDataRang(viewHolder, position, rangList);//rang
        if (!this.isfromSum) {
            viewHolder.city.setEnabled(false);
            viewHolder.colorFator.setEnabled(false);
            viewHolder.form.setEnabled(false);
            viewHolder.jensGhali.setEnabled(false);
            viewHolder.tool.setEnabled(false);
            viewHolder.price.setEnabled(false);
            viewHolder.arz.setEnabled(false);
            viewHolder.sumPrice.setEnabled(false);
        }
        if (stList.get(position).getUnitPrice() == 0.0)
            viewHolder.price.setText("");
        else
            viewHolder.price.setText(nf.format(Long.parseLong(String.valueOf((int) stList.get(position).getUnitPrice()))));
        if (stList.get(position).getLenght() == 0.0)
            viewHolder.tool.setText("");
        else
            viewHolder.tool.setText(String.valueOf(stList.get(position).getLenght()));
        if (stList.get(position).getWidth() == 0.0)
            viewHolder.arz.setText("");
        else
            viewHolder.arz.setText(String.valueOf(stList.get(position).getWidth()));
        if (stList.get(position).getOrderPrice() == 0.0)
            viewHolder.sumPrice.setText("");
        else
            viewHolder.sumPrice.setText(nf.format(Long.parseLong(String.valueOf((int) stList.get(position).getOrderPrice()))));

        if (stList.get(position).getOrderStatusID() == 3 || stList.get(position).getOrderStatusID() == 11)
            viewHolder.card_view.setCardBackgroundColor(Color.rgb(186, 236, 164));

        if (stList.get(position).getOrderStatusID() == 1 || stList.get(position).getOrderStatusID() == 9)
            viewHolder.card_view.setCardBackgroundColor(Color.rgb(255, 204, 204));
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
                viewHolder.card_view.setCardBackgroundColor(Color.rgb(255, 204, 204));
        }
        viewHolder.clearprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.price.setText("");
            }
        });

        viewHolder.clearsumprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.sumPrice.setText("");
            }
        });
        viewHolder.editCarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderDetailEdit orderDetailEdit = new OrderDetailEdit();
                orderDetailEdit.setOrderDetailID(stList.get(position).getOrderDetailID());
                if (!viewHolder.price.getText().toString().isEmpty() && !viewHolder.price.getText().toString().equals(""))
                    orderDetailEdit.setUnitPrice(Float.parseFloat(viewHolder.price.getText().toString()));
                sheklSelected = sheklList.get(viewHolder.form.getSelectedItemPosition()).getServiceAttrib1ID();
                jensSelected = jensList.get(viewHolder.jensGhali.getSelectedItemPosition()).getServiceAttrib2ID();
                citySelected = cityList.get(viewHolder.city.getSelectedItemPosition()).getServiceAttrib3ID();
                rangSelected = rangList.get(viewHolder.colorFator.getSelectedItemPosition()).getServiceAttrib4ID();
                orderDetailEdit.setServiceAttrib1ID(sheklSelected);
                orderDetailEdit.setServiceAttrib2ID(jensSelected);
                orderDetailEdit.setServiceAttrib3ID(citySelected);
                orderDetailEdit.setServiceAttrib4ID(rangSelected);
                if (!viewHolder.arz.getText().toString().isEmpty() && !viewHolder.arz.getText().toString().equals(""))
                    orderDetailEdit.setWidth(Float.parseFloat(viewHolder.arz.getText().toString()));
                if (!viewHolder.tool.getText().toString().isEmpty() && !viewHolder.tool.getText().toString().equals(""))
                    orderDetailEdit.setLenght(Float.parseFloat(viewHolder.tool.getText().toString()));

//                orderDetailEdit.setQuantity(12);
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

        viewHolder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String initial = s.toString();
                // remove all non-digits characters
                String processed = initial.replaceAll(",", "");

                if (processed.length() > 3) {
//                    processed = processed.replaceAll("(\\d{3})(?=\\d)", "$1,");
                    processed = nf.format(Long.parseLong(processed));
                }
                //Remove the listener
                viewHolder.price.removeTextChangedListener(this);
                //Assign processed text
                viewHolder.price.setText(processed);
                viewHolder.price.addTextChangedListener(this);
            }
        });
        viewHolder.sumPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String initial = s.toString();
                // remove all non-digits characters
                String processed = initial.replaceAll(",", "");

                if (processed.length() > 3) {
//                    processed = processed.replaceAll("(\\d{3})(?=\\d)", "$1,");
                    processed = nf.format(Long.parseLong(processed));
                }
                //Remove the listener
                viewHolder.sumPrice.removeTextChangedListener(this);
                //Assign processed text
                viewHolder.sumPrice.setText(processed);
                viewHolder.sumPrice.addTextChangedListener(this);
            }
        });

    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
