package com.example.projectbut.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectbut.R;
import com.example.projectbut.model.Day;
import com.example.projectbut.model.EmptyDay;
import com.example.projectbut.util.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter{

    private final int EMPTY_TYPE = 0;
    private final int DAY_TYPE = 1;

    private View.OnClickListener onItemViewClickListener;
    private List<Object> mCalendarList;

    public CalendarAdapter(List<Object> calendarList){
        this.mCalendarList = calendarList;
    }

    /*
    public void setCalendarList(List<Object> calendarList){
        this.mCalendarList = calendarList;
        notifyDataSetChanged();
    }
     */

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener;
    }

    @Override
    public int getItemViewType(int position){
        Object item = mCalendarList.get(position);
        if(item instanceof String){
            return EMPTY_TYPE;
        } else {
            return DAY_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == EMPTY_TYPE){
            return new EmptyViewHolder(inflater.inflate(R.layout.item_day_empty,parent,false));
        } else {
            return new DayViewHolder(inflater.inflate(R.layout.item_day,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
        int viewType = getItemViewType(position);

        if(viewType == EMPTY_TYPE){
            EmptyViewHolder holder = (EmptyViewHolder)viewHolder;
            EmptyDay model = new EmptyDay();
            holder.bind(model);
        } else if (viewType == DAY_TYPE){
            DayViewHolder holder = (DayViewHolder)viewHolder;
            Object item = mCalendarList.get(position);
            Day model = new Day();

            if(item instanceof Calendar){
                model.setCalendar((Calendar)item);
            }

            holder.bind(model);
        }
    }

    @Override
    public int getItemCount(){
        if(mCalendarList!=null) {
            return mCalendarList.size();
        }
        return 0;
    }




    private class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(@NonNull View itemView){
            super(itemView);
            initView(itemView);
        }

        public void initView(View v){

        }

        public void bind(EmptyDay model){

        }
    }

    private class DayViewHolder extends RecyclerView.ViewHolder{

        TextView itemDay;

        public DayViewHolder(@NonNull View itemView){
            super(itemView);
            initView(itemView);
        }

        public void initView(View v){
            itemDay = (TextView)v.findViewById(R.id.item_day);
        }

        public void bind(Day model){
            String day = ((Day) model).getDay();
            itemDay.setText(day);
        }

    }
}
