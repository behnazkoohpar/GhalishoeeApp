package com.koohpar.oghli.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koohpar.oghli.R;
import com.koohpar.oghli.data.model.api.MantagheResponse;

import java.util.List;

public class MantagheAdapter extends RecyclerView.Adapter<MantagheAdapter.MyViewHolder> {

    private List<MantagheResponse> cityList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.expandedListItem);
        }
    }

    public MantagheAdapter(List<MantagheResponse> cityList) {
        this.cityList = cityList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MantagheResponse city = cityList.get(position);
        holder.title.setText(city.getTitle());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}
