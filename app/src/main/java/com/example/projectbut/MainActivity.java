package com.example.projectbut;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectbut.Pojo.Receipt;
import com.example.projectbut.Service.DataService;
import com.example.projectbut.fragment.HomeFragment;
import com.example.projectbut.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    DataService dataService = new DataService();
    List<Receipt> receiptList;

    private Context mContext;

    //Fragment
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;

    private BottomNavigationView bottomNavigationView;
    private Bundle bundle = new Bundle(1);

    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        dataService.select.selectAll().enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                receiptList = response.body();
                bundle.putParcelableArrayList("receiptList", (ArrayList<? extends Parcelable>) receiptList);
                homeFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.navigation_frameLayout, homeFragment).commit();
            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                switch(menuItem.getItemId()){
                    case R.id.navigation_home : {
                        fragmentTransaction.replace(R.id.navigation_frameLayout, homeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_search : {
                        fragmentTransaction.replace(R.id.navigation_search, searchFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_my : {
                        break;
                    }
                }
                return true;
            }
        });

    }

}