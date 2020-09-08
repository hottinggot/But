package com.example.projectbut.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectbut.Service.DataService;
import com.example.projectbut.R;
import com.example.projectbut.Pojo.Receipt;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder>{

    private List<Receipt> data;
    private DataService dataService;
    private View.OnClickListener onItemViewClickListener;
    private Context context;

    public ReceiptAdapter(List<Receipt> data, Context context, DataService dataService){
        this.data = data;
        this.context = context;
        this.dataService = dataService;
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }

    @Override
    public ReceiptAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_info, parent, false);
        if(onItemViewClickListener != null){
            v.setOnClickListener(onItemViewClickListener);
        }
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() { return data.size(); }

    @Override
    public void onBindViewHolder(final ReceiptAdapter.ViewHolder holder, final int position){
        try {
            holder.title.setText(data.get(position).getTitle());
            holder.shop_name.setText(data.get(position).getShopName());
        } catch (Exception e) {System.out.println(e);}

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout info_layout;
        TextView title,shop_name;

        ViewHolder(View itemView) {
            super(itemView);
            info_layout = itemView.findViewById(R.id.info_layout);
            title = itemView.findViewById(R.id.title);
            shop_name = itemView.findViewById(R.id.shop_name);
        }


    }
}
