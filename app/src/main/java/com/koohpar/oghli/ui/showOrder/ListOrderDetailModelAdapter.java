package com.koohpar.oghli.ui.showOrder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderDetailModel;

import java.util.List;

public class ListOrderDetailModelAdapter extends RecyclerView.Adapter<ListOrderDetailModelAdapter.ViewHolder> {
    public List<OrderDetailModel> stList;
    public static Context context;

    public ListOrderDetailModelAdapter(List<OrderDetailModel> SlistS) {
        stList = SlistS;
    }
    private ListOrderDetailModelAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onOpenClick(int position,OrderDetailModel orderDetailModel);
    }

    public void setOnitemclickListener(ListOrderDetailModelAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView typeOrder, city, jensGhali, form,colorFator,price,discountPrice,priceRufo,quantity,tool,arz,price2;
        private final Button delete;

        public ViewHolder(final View itemView) {
            super(itemView);
            typeOrder = (TextView) itemView.findViewById(R.id.typeOrder);
            city = (TextView) itemView.findViewById(R.id.city);
            jensGhali = (TextView) itemView.findViewById(R.id.jensGhali);
            form = (TextView) itemView.findViewById(R.id.form);
            colorFator = (TextView) itemView.findViewById(R.id.colorFator);
            price = (TextView) itemView.findViewById(R.id.price);
            discountPrice = (TextView) itemView.findViewById(R.id.discountPrice);
            priceRufo = (TextView) itemView.findViewById(R.id.priceRufo);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            tool = (TextView) itemView.findViewById(R.id.tool);
            arz = (TextView) itemView.findViewById(R.id.arz);
            price2 = (TextView) itemView.findViewById(R.id.price2);
            delete = (Button) itemView.findViewById(R.id.delete);

        }
    }

    public ListOrderDetailModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        ListOrderDetailModelAdapter.ViewHolder viewHolder = new ListOrderDetailModelAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListOrderDetailModelAdapter.ViewHolder viewHolder, final int position) {

//                IsRofu
//                RofuPrice
        viewHolder.city.setText(stList.get(position).getServiceAttrib3Name());
        viewHolder.jensGhali.setText(stList.get(position).getServiceAttrib2Name());
        viewHolder.typeOrder.setText(stList.get(position).getServiceName());
        viewHolder.form.setText(stList.get(position).getServiceAttrib1Name());
        viewHolder.colorFator.setText(stList.get(position).getServiceAttrib4Name());
        viewHolder.price.setText(String.valueOf(stList.get(position).getUnitPrice()));
        viewHolder.discountPrice.setText(String.valueOf(stList.get(position).getDiscountPrice()));
        viewHolder.priceRufo.setText(String.valueOf(stList.get(position).getRofuPrice()));
        viewHolder.quantity.setText(String.valueOf(stList.get(position).getQuantity()));
        viewHolder.tool.setText(String.valueOf(stList.get(position).getLenght()));
        viewHolder.arz.setText(String.valueOf(stList.get(position).getWidth()));
        viewHolder.price2.setText(String.valueOf(stList.get(position).getOrderPrice()));

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOpenClick(position,stList.get(position));
            }
        });

    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
