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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderDetailEdit;
import com.koohpar.oghli.data.model.api.OrderDetailModel;
import com.koohpar.oghli.data.model.api.RofuAttribModel;
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
    private List<ServiceAttrib1Model> sheklList;//shekl
    private List<ServiceAttrib2Model> jensList;//jens
    private List<ServiceAttrib3Model> cityList;//city
    private List<ServiceAttrib4Model> rangList;//rang
    private List<RofuAttribModel> rofuList;//rang
    public static Context context;
    private String citySelected;
    private String jensSelected;
    private String sheklSelected;
    private String rangSelected;
    private String rofuSelected;
    private EditOrderDetailAdapter.OnItemClickListener mListener;
    NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

    public interface OnItemClickListener {
        void onEditClick(int position, OrderDetailEdit orderDetailEdit);
    }

    public void setOnitemclickListener(EditOrderDetailAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public EditOrderDetailAdapter(List<OrderDetailModel> SlistS, List<ServiceAttrib3Model> cityList, List<RofuAttribModel> rofuList,
                                  List<ServiceAttrib2Model> jensList, List<ServiceAttrib1Model> sheklList, List<ServiceAttrib4Model> rangList) {
        this.sheklList = sheklList;
        this.jensList = jensList;
        this.cityList = cityList;
        this.rofuList = rofuList;
        this.rangList = rangList;
        stList = SlistS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final Spinner typeOrder, city, jensGhali, form, colorFator, rofu;
        public final EditText price, desc, rofuprice, tool, arz, sumPrice;
        private final Button editCarpet;
        private final ImageView clearprice, clearrofuprice, clearsumprice;

        public ViewHolder(final View itemView) {
            super(itemView);
            typeOrder = (Spinner) itemView.findViewById(R.id.typeOrder);
            city = (Spinner) itemView.findViewById(R.id.city);
            rofu = (Spinner) itemView.findViewById(R.id.rofu);
            jensGhali = (Spinner) itemView.findViewById(R.id.jensGhali);
            form = (Spinner) itemView.findViewById(R.id.form);
            colorFator = (Spinner) itemView.findViewById(R.id.colorFator);
            price = (EditText) itemView.findViewById(R.id.price);
            rofuprice = (EditText) itemView.findViewById(R.id.rofuprice);
            desc = (EditText) itemView.findViewById(R.id.desc);
            tool = (EditText) itemView.findViewById(R.id.tool);
            arz = (EditText) itemView.findViewById(R.id.arz);
            sumPrice = (EditText) itemView.findViewById(R.id.sumPrice);
            editCarpet = (Button) itemView.findViewById(R.id.editCarpet);
            clearprice = (ImageView) itemView.findViewById(R.id.clearprice);
            clearrofuprice = (ImageView) itemView.findViewById(R.id.clearrofuprice);
            clearsumprice = (ImageView) itemView.findViewById(R.id.clearsumprice);

        }
    }

    private void receivedDataCity(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib3Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size() + 1];
                for (int i = 0; i < data.size(); i++) {
                    datas[0] = "-----";
                    datas[i + 1] = data.get(i).getServiceAttribTitle();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.city.setAdapter(adapter);
                viewHolder.city.setSelection(0);
                for (int j = 0; j < cityList.size(); j++) {
                    if (stList.get(position).getServiceAttrib3ID().equalsIgnoreCase(cityList.get(j).getServiceAttrib3ID()))
                        viewHolder.city.setSelection(j + 1);
                }
            } else {
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataJens(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib2Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size() + 1];
                for (int i = 0; i < data.size(); i++) {
                    datas[0] = "-----";
                    datas[i + 1] = data.get(i).getServiceAttribTitle();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.jensGhali.setAdapter(adapter);
                viewHolder.jensGhali.setSelection(0);
                for (int j = 0; j < jensList.size(); j++) {
                    if (stList.get(position).getServiceAttrib2ID().equalsIgnoreCase(jensList.get(j).getServiceAttrib2ID()))
                        viewHolder.jensGhali.setSelection(j + 1);
                }
            } else {
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataShekl(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib1Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size() + 1];
                for (int i = 0; i < data.size(); i++) {
                    datas[0] = "-----";
                    datas[i + 1] = data.get(i).getServiceAttribTitle();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.form.setAdapter(adapter);
                viewHolder.form.setSelection(0);
                for (int j = 0; j < sheklList.size(); j++) {
                    if (stList.get(position).getServiceAttrib1ID().equalsIgnoreCase(sheklList.get(j).getServiceAttrib1ID()))
                        viewHolder.form.setSelection(j + 1);
                }
            } else {
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataRang(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<ServiceAttrib4Model> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size() + 1];
                for (int i = 0; i < data.size(); i++) {
                    datas[0] = "-----";
                    datas[i + 1] = data.get(i).getServiceAttribTitle();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.colorFator.setAdapter(adapter);
                viewHolder.colorFator.setSelection(0);
                for (int j = 0; j < rangList.size(); j++) {
                    if (stList.get(position).getServiceAttrib4ID().equalsIgnoreCase(rangList.get(j).getServiceAttrib4ID()))
                        viewHolder.colorFator.setSelection(j + 1);
                }
            } else {
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void receivedDataRofu(final EditOrderDetailAdapter.ViewHolder viewHolder, final int position, List<RofuAttribModel> data) {
        try {
            if (data != null) {
                String[] datas = new String[data.size() + 1];
                for (int i = 0; i < data.size(); i++) {
                    datas[0] = "-----";
                    datas[i + 1] = data.get(i).getRofuAttribTitle();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, datas);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_text_color);
                viewHolder.rofu.setAdapter(adapter);
                viewHolder.rofu.setSelection(0);
                for (int j = 0; j < rofuList.size(); j++) {
                    if (stList.get(position).getRofuAttrib1ID().equalsIgnoreCase(rofuList.get(j).getRofuAttribID()))
                        viewHolder.rofu.setSelection(j + 1);
                }
            } else {
                CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.problem), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            receivedDataCity(viewHolder, position, cityList);
            receivedDataJens(viewHolder, position, jensList);
            receivedDataShekl(viewHolder, position, sheklList);
            receivedDataRang(viewHolder, position, rangList);
            receivedDataRofu(viewHolder, position, rofuList);

            viewHolder.editCarpet.setVisibility(View.VISIBLE);
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

            if (stList.get(position).getRofuPrice() == 0.0)
                viewHolder.rofuprice.setText("");
            else
                viewHolder.rofuprice.setText(nf.format(Long.parseLong(String.valueOf((int) stList.get(position).getRofuPrice()))));

            viewHolder.desc.setText(stList.get(position).getRofuDesc());

            viewHolder.clearprice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.price.setText("");
                }
            });
            viewHolder.clearrofuprice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.rofuprice.setText("");
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
                        orderDetailEdit.setUnitPrice(Float.parseFloat(viewHolder.price.getText().toString().replaceAll(",", "")));

                    if (viewHolder.form.getSelectedItemPosition() == 0)
                        sheklSelected = "00000000-0000-0000-0000-000000000000";
                    else
                        sheklSelected = sheklList.get(viewHolder.form.getSelectedItemPosition() - 1).getServiceAttrib1ID();

                    if (viewHolder.jensGhali.getSelectedItemPosition() == 0)
                        jensSelected = "00000000-0000-0000-0000-000000000000";
                    else
                        jensSelected = jensList.get(viewHolder.jensGhali.getSelectedItemPosition() - 1).getServiceAttrib2ID();

                    if (viewHolder.city.getSelectedItemPosition() == 0)
                        citySelected = "00000000-0000-0000-0000-000000000000";
                    else
                        citySelected = cityList.get(viewHolder.city.getSelectedItemPosition() - 1).getServiceAttrib3ID();

                    if (viewHolder.colorFator.getSelectedItemPosition() == 0)
                        rangSelected = "00000000-0000-0000-0000-000000000000";
                    else
                        rangSelected = rangList.get(viewHolder.colorFator.getSelectedItemPosition() - 1).getServiceAttrib4ID();
                    if (viewHolder.rofu.getSelectedItemPosition() == 0)
                        rofuSelected = "00000000-0000-0000-0000-000000000000";
                    else
                        rofuSelected = rofuList.get(viewHolder.rofu.getSelectedItemPosition() - 1).getRofuAttribID();

                    if (!rofuSelected.equals("00000000-0000-0000-0000-000000000000"))
                        orderDetailEdit.setRofu(true);
                    else
                        orderDetailEdit.setRofu(false);
                    orderDetailEdit.setRofuAttrib1ID(rofuSelected);
                    orderDetailEdit.setRofuDesc(viewHolder.desc.getText().toString());
                    if (!viewHolder.rofuprice.getText().toString().isEmpty() &&
                            !viewHolder.rofuprice.getText().toString().equals(""))
                        orderDetailEdit.setRofuPrice(Float.parseFloat(viewHolder.rofuprice.getText().toString().replaceAll(",", "")));

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
                    viewHolder.sumPrice.setSelection(processed.length());
                    viewHolder.sumPrice.addTextChangedListener(this);
                }
            });
            viewHolder.rofuprice.addTextChangedListener(new TextWatcher() {
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
                    viewHolder.rofuprice.removeTextChangedListener(this);
                    //Assign processed text
                    viewHolder.rofuprice.setText(processed);
                    viewHolder.rofuprice.setSelection(processed.length());
                    viewHolder.rofuprice.addTextChangedListener(this);
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
                    viewHolder.price.setSelection(processed.length());
                    viewHolder.price.addTextChangedListener(this);

                }
            });
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(context, context.getString(R.string.text_attention), context.getString(R.string.webservice_error), null, null);
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
