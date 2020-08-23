package com.example.projectbut;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectbut.DividerItemDecoration;
import com.example.projectbut.fragment.DateFragment;
import com.example.projectbut.fragment.MonthFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    DataService dataService = new DataService();
    List<Receipt> receiptList;
    private TabLayout mTabLayout;
    private Context mContext;
    private MonthFragment monthFragment;
    private DateFragment dateFragment;
    Bundle bundle = new Bundle(1);
    //final RecyclerView receipt_list = findViewById(R.id.receipt_list);

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final View gray_box = findViewById(R.id.gray_box);

        mTabLayout = (TabLayout)findViewById(R.id.layout_tab);
        mContext = getApplicationContext();
        dateFragment = new DateFragment();
        monthFragment = new MonthFragment();

        //receipt_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //receipt_list.addItemDecoration(new DividerItemDecoration(this));

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("Month")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("Date")));


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


        dataService.select.selectAll().enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                receiptList = response.body();
            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /*
    private void setAdapter(final RecyclerView receipt_list){
        final ReceiptAdapter receiptAdapter = new ReceiptAdapter(receiptList, this, dataService);
        receiptAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReceiptDetail.class );
                intent.putExtra("id", receiptList.get(receipt_list.getChildAdapterPosition(view)).getId());

                view.getContext().startActivity(intent);
            }
        });
        receipt_list.setAdapter(receiptAdapter);
    }
     */

    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab,null);
        TextView txt_name = (TextView)tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        return tabView;
    }

    private void setFrag(int index){
        if(index == 0){
            fm.beginTransaction().replace(R.id.main_frame, monthFragment).commit();
        } else if (index == 1) {
            bundle.putParcelableArrayList("receiptList", (ArrayList<? extends Parcelable>) receiptList);
            dateFragment.setArguments(bundle);
            fm.beginTransaction().replace(R.id.main_frame, dateFragment).commit();
        }
    }
}