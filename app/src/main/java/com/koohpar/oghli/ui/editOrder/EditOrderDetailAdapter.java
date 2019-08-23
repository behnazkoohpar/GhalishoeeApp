package com.koohpar.oghli.ui.editOrder;

import android.content.Context;
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

public class EditOrderDetailAdapter extends RecyclerView.Adapter<EditOrderDetailAdapter.ViewHolder> {
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
    private EditOrderDetailAdapter.OnItemClickListener mListener;
    NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

    public interface OnItemClickListener {
        void onEditClick(int position, OrderDetailEdit orderDetailEdit);
    }

    public void setOnitemclickListener(EditOrderDetailAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public EditOrderDetailAdapter(List<OrderDetailModel> SlistS, List<ServiceAttrib3Model> att3,
                                  List<ServiceAttrib2Model> att2, List<ServiceAttrib1Model> att1, List<ServiceAttrib4Model> att4) {
        this.att1 = att1;
        this.att2 = att2;
        this.att3 = att3;
        this.att4 = att4;
        stList = SlistS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final Spinner typeOrder, city, jensGhali, form, colorFator;
        public final TextView price, tool, arz, sumPrice;
        private final Button editCarpet;
        private final ImageView clearprice, clearsumprice;

        public ViewHolder(final View itemView) {
            super(itemView);
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
            clearprice = (ImageView) itemView.findViewById(R.id.clearprice);
            clearsumprice = (ImageView) itemView.findViewById(R.id.clearsumprice);

        }
    }

    private void receivedDataCity(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib3Model> data) {
        if (data != null) {
            EditOrderActivity.att3 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            citySelected = String.valueOf(data.get(0).getServiceAttrib3ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            viewHolder.city.setAdapter(adapter);
            viewHolder.city.setSelection(position);
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
    }

    private void receivedDataJens(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib2Model> data) {
        if (data != null) {
            EditOrderActivity.att2 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            jensSelected = String.valueOf(data.get(0).getServiceAttrib2ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            viewHolder.jensGhali.setAdapter(adapter);
            viewHolder.jensGhali.setSelection(position);
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
    }

    private void receivedDataShekl(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib1Model> data) {
        if (data != null) {
            EditOrderActivity.att1 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            sheklSelected = String.valueOf(data.get(0).getServiceAttrib1ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            viewHolder.form.setAdapter(adapter);
            viewHolder.form.setSelection(position);
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
    }

    private void receivedDataRang(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib4Model> data) {
        if (data != null) {
            EditOrderActivity.att4 = data;
            String[] datas = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                datas[i] = data.get(i).getServiceAttribTitle();
            }
            rangSelected = String.valueOf(data.get(position).getServiceAttrib4ID());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_text_color);
            viewHolder.colorFator.setAdapter(adapter);
            viewHolder.colorFator.setSelection(position);
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
    }

    public EditOrderDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, parent, false);
        EditOrderDetailAdapter.ViewHolder viewHolder = new EditOrderDetailAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position) {
        receivedDataCity(viewHolder, position, att3);
        receivedDataJens(viewHolder, position, att2);
        receivedDataShekl(viewHolder, position, att1);
        receivedDataRang(viewHolder, position, att4);
        viewHolder.editCarpet.setVisibility(View.VISIBLE);
        if (stList.get(position).getUnitPrice() == 0.0)
            viewHolder.price.setText("");
        else
            viewHolder.price.setText(nf.format(Long.parseLong(String.valueOf(stList.get(position).getUnitPrice()))));
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
            viewHolder.sumPrice.setText(nf.format(Long.parseLong(String.valueOf(stList.get(position).getOrderPrice()))));

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

    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
