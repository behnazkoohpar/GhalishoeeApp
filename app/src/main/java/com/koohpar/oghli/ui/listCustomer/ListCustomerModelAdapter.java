package com.koohpar.oghli.ui.listCustomer;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.Customer;

import java.util.List;

public class ListCustomerModelAdapter extends RecyclerView.Adapter<ListCustomerModelAdapter.ViewHolder> {
    public List<Customer> stList;
    public static Context context;
    private ListCustomerModelAdapter.OnItemClickListener mListener;
    public ListCustomerModelAdapter(List<Customer> SlistS) {
        stList = SlistS;
    }

    public interface OnItemClickListener {
        void onOpenClick(int position);

        void onCallTelClick(int position);

        void onCallTelHomeClick(int position);
    }

    public void setOnitemclickListener(ListCustomerModelAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name,telnumber,numberhome,address;
        public CardView card_view;

        public ViewHolder(final View itemView, final ListCustomerModelAdapter.OnItemClickListener listener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            telnumber = (TextView) itemView.findViewById(R.id.telnumber);
            numberhome = (TextView) itemView.findViewById(R.id.numberhome);
            address = (TextView) itemView.findViewById(R.id.address);
            card_view = (CardView) itemView.findViewById(R.id.card_view);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onOpenClick(position);
                        }
                    }
                }
            });
            telnumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCallTelClick(position);
                        }
                    }

                }
            });
            numberhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCallTelHomeClick(position);
                        }
                    }
                }
            });

        }
    }

    public ListCustomerModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_customer, parent, false);
        ListCustomerModelAdapter.ViewHolder viewHolder = new ListCustomerModelAdapter.ViewHolder(itemLayoutView, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListCustomerModelAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.name.setText(stList.get(position).getCustName());
        viewHolder.telnumber.setText(stList.get(position).getCollectMobile());
        viewHolder.numberhome.setText(stList.get(position).getCollectPhone());
        viewHolder.address.setText(stList.get(position).getCollectAddress());
    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
