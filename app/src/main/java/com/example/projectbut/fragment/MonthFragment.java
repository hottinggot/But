package com.example.projectbut.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectbut.R;
import com.example.projectbut.adapter.ViewPagerAdapter;
import com.example.projectbut.util.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MonthFragment extends Fragment {

    View view;
    private TextView monthHeader;
    private ViewPager2 viewpagerCalendar;
    private List<List<Object>> monthList;

    private List<Object> nowDateList;

    private long date;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_month, container, false);

        monthHeader = (TextView) view.findViewById(R.id.month_header);
        viewpagerCalendar = (ViewPager2) view.findViewById(R.id.viewpager_calendar);

        setCalendarList();

        setViewpagerAdapter(viewpagerCalendar);

//        Bundle b = getArguments();
//        int position = b.getInt("monthPosition");
//
//        monthHeader.setText(String.valueOf(monthList.get(position).get(Calendar.MONTH)));

        return view;
    }

    private void setViewpagerAdapter(final ViewPager2 viewpagerCalendar) {
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), monthList);
        /*
        calendarAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
         */

        viewpagerCalendar.setAdapter(viewPagerAdapter);
    }

    public void setCalendarList(){

        GregorianCalendar cal = new GregorianCalendar();

        List<List<Object>> monthList = new ArrayList<>();

        date = cal.getTimeInMillis();

        for(int i = -1; i<2; i++){
            try{
                List<Object> dateList = new ArrayList<>();
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+i,1,0,0,0);

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1; //월의 1일인 요일 -1 == empty 갯수를 알 수 있음.
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                for(int j=0; j<dayOfWeek; j++){
                    dateList.add(Keys.EMPTY);
                }

                for(int j=1; j<=max; j++){
                    dateList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }

                for(int j=0; j < 7-(dateList.size()%7); j++){
                    dateList.add(Keys.EMPTY);
                }

                if(i==0) nowDateList = dateList;

                monthList.add(dateList);


            } catch (Exception e){
                e.printStackTrace();
            }

        }
        this.monthList = monthList;

    }

}
