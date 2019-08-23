package com.koohpar.oghli.ui.listSum;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.OrderMissionDetailModel;

import java.util.List;

public class ListOrderMissionDetailModelAdapter  extends RecyclerView.Adapter<ListOrderMissionDetailModelAdapter.ViewHolder> {

    public List<OrderMissionDetailModel> stList;
    public static Context context;
    private ListOrderMissionDetailModelAdapter.OnItemClickListener mListener;
    public ListOrderMissionDetailModelAdapter(List<OrderMissionDetailModel> SlistS) {
        stList = SlistS;
    }

    public interface OnItemClickListener {
        void onOpenClick(int position);

        void onCallTelClick(int position);

        void onCallTelHomeClick(int position);
    }

    public void setOnitemclickListener(ListOrderMissionDetailModelAdapter.OnItemClickListener onitemclickListener) {
        mListener = onitemclickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name,telnumber,numberhome,address;
        public CardView card_view;
        public Button select;
        public ViewHolder(final View itemView, final ListOrderMissionDetailModelAdapter.OnItemClickListener listener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            telnumber = (TextView) itemView.findViewById(R.id.telnumber);
            numberhome = (TextView) itemView.findViewById(R.id.numberhome);
            address = (TextView) itemView.findViewById(R.id.address);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            select = (Button) itemView.findViewById(R.id.select);

            select.setOnClickListener(new View.OnClickListener() {
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
            name.setOnClickListener(new View.OnClickListener() {
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

    public ListOrderMissionDetailModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_sum, parent, false);
        ListOrderMissionDetailModelAdapter.ViewHolder viewHolder = new ListOrderMissionDetailModelAdapter.ViewHolder(itemLayoutView, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListOrderMissionDetailModelAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.name.setText(stList.get(position).getCustName());
        viewHolder.telnumber.setText(stList.get(position).getCollectMobile());
        viewHolder.numberhome.setText(stList.get(position).getCollectPhone());
        viewHolder.address.setText(stList.get(position).getCollectAddress());
        if (stList.get(position).getOrderStatusID() == 3 || stList.get(position).getOrderStatusID() == 11)
            viewHolder.card_view.setCardBackgroundColor(Color.rgb(186,236,164));

        if (stList.get(position).getOrderStatusID() == 1 || stList.get(position).getOrderStatusID() == 9)
            viewHolder.card_view.setCardBackgroundColor(Color.rgb(255,204,204));
    }

    public int getItemCount() {
        if (this.stList != null)
            return this.stList.size();
        return 0;
    }
}
