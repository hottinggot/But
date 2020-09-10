package com.example.projectbut.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectbut.Pojo.Receipt;
import com.example.projectbut.R;
import com.example.projectbut.ReceiptDetailActivity;
import com.example.projectbut.Service.DataService;
import com.example.projectbut.adapter.ReceiptAdapter;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SearchFragment extends Fragment {

    View view;
    List<Receipt> receiptList;
    RecyclerView receipt_list;
    DataService dataService = new DataService();
    ReceiptAdapter receiptAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        receipt_list = view.findViewById(R.id.search_recycler);

        receipt_list.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        Bundle b = getArguments();
        receiptList = b.getParcelableArrayList("receiptList");

        setAdapter(receipt_list);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_action);

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) item.getActionView(); //SearchView 얻어옴
        searchView.onActionViewExpanded(); // 아이콘 영역을 액션뷰 전체로 확장

        if(searchView!=null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName())); //searchable에 설정한 정보 가져옴.
            searchView.setIconifiedByDefault(false);
            searchView.setQueryHint("검색");
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            receiptAdapter.setFilter(filter(receiptList,query));
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            receiptAdapter.setFilter(filter(receiptList, newText));
            return false;
        }
    };

    private List<Receipt> filter(List<Receipt> receiptList, String query){
        query = query.toLowerCase();
        List<Receipt> filteredList = new ArrayList<>();

        if(!query.isEmpty()){
            for(Receipt r : receiptList){
                final String title = r.getTitle();
                if(title.contains(query)){
                    filteredList.add(r);
                }
            }
        } else filteredList = receiptList;

        return filteredList;

    }

    private void setAdapter(RecyclerView recyclerView){
        receiptAdapter = new ReceiptAdapter(receiptList,getContext(),dataService);
        receiptAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ReceiptDetailActivity.class);
                startActivity(i);
            }
        });
        recyclerView.setAdapter(receiptAdapter);
    }


}