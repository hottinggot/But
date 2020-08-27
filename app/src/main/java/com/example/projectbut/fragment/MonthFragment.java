package com.example.projectbut.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.projectbut.R;
import com.example.projectbut.adapter.CalendarAdapter;
import com.example.projectbut.util.DateUtil;
import com.example.projectbut.util.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthFragment extends Fragment {

    View view;
    private TextView monthHeader;
    private RecyclerView calendar;
    private long date;
    public ArrayList<Object> mCalendarList = new ArrayList<>();

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_month, container, false);

        monthHeader = (TextView)view.findViewById(R.id.month_header);
        calendar = (RecyclerView)view.findViewById(R.id.calender);

        calendar.setLayoutManager(new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL));
        setCalendarList();

        monthHeader.setText(DateUtil.getDate(date, DateUtil.MONTH_FORMAT));

        setAdapter(calendar);

        return view;
    }

    private void setAdapter(final RecyclerView calendar){
        final CalendarAdapter calendarAdapter = new CalendarAdapter(mCalendarList);
        /*
        calendarAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
         */

        calendar.setAdapter(calendarAdapter);
    }

    public void setCalendarList(){

        GregorianCalendar cal = new GregorianCalendar();
        ArrayList<Object> calendarList = new ArrayList<>();

        date = cal.getTimeInMillis();

        for(int i = -300; i<300; i++){
            try{
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+i,1,0,0,0);

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; //월의 1일인 요일 -1 == empty 갯수를 알 수 있음.
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                for(int j=0; j<dayOfWeek; j++){
                    calendarList.add(Keys.EMPTY);
                }

                for(int j=1; j<=max; j++){
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        mCalendarList = calendarList;
    }

}
