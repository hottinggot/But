package com.example.projectbut.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.projectbut.R;
import com.example.projectbut.fragment.MonthFragment;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {

    private Context context;
    private List<List<Object>> months;
    Bundle bundle = new Bundle(1);
    MonthFragment monthFragment = new MonthFragment();

    public ViewPagerAdapter(Context context, List<List<Object>> data) {
        this.context = context;
        this.months = data;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_month_viewpager, parent, false);
        return new ViewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        if(position<months.size()){

//            bundle.putInt("monthPosition", position);
//            monthFragment.setArguments(bundle);

            CalendarAdapter adapter = new CalendarAdapter(months.get(position));
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
            holder.date_calender.setLayoutManager(manager);
            holder.date_calender.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView date_calender;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            date_calender = itemView.findViewById(R.id.date_calendar);
        }
    }
}
