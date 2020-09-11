package com.example.projectbut.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.projectbut.Pojo.Receipt;
import com.example.projectbut.R;
import com.example.projectbut.Service.DataService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {

    TabLayout mTabLayout;
    Context mContext;

    MonthFragment monthFragment;
    DateFragment dateFragment;

    Bundle bundle = new Bundle(1);

    View view;
    List<Receipt> receiptList;
    DataService dataService = new DataService();

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        FragmentManager cfm = getChildFragmentManager();

        mContext = getContext();
        Bundle b = getArguments();
        receiptList = b.getParcelableArrayList("receiptList");

        mTabLayout = (TabLayout) view.findViewById(R.id.layout_tab);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("Month")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("Date")));


        monthFragment = new MonthFragment();
        dateFragment = new DateFragment();

        dateFragment.setArguments(bundle);
        cfm.beginTransaction().replace(R.id.fragment_tab_contents, monthFragment).commit();


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                setFrag(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        return tabView;
    }


    private void setFrag(int index){
        FragmentManager cfm = getChildFragmentManager();
        if(index == 0){
            cfm.beginTransaction().replace(R.id.fragment_tab_contents, monthFragment).commit();

        } else if (index == 1) {
            bundle.putParcelableArrayList("childReceiptList", (ArrayList<? extends Parcelable>) receiptList);
            dateFragment.setArguments(bundle);
            cfm.beginTransaction().replace(R.id.fragment_tab_contents, dateFragment).commit();
        }
    }

}