package com.koohpar.oghli.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.DetailHomeResponse;

import java.util.List;

public class DetailHomeAdapter extends RecyclerView.Adapter<DetailHomeAdapter.MyViewHolder> {

    private List<DetailHomeResponse> detailHomeResponses;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.expandedListItem);
        }
    }

    public DetailHomeAdapter(List<DetailHomeResponse> detailHomeResponses) {
        this.detailHomeResponses = detailHomeResponses;
    }

    @Override
    public DetailHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new DetailHomeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailHomeAdapter.MyViewHolder holder, int position) {
        DetailHomeResponse detailHomeResponse = detailHomeResponses.get(position);
        holder.title.setText(detailHomeResponse.getTitle());
    }

    @Override
    public int getItemCount() {
        return detailHomeResponses.size();
    }
}
