package com.example.projectbut.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectbut.DividerItemDecoration;
import com.example.projectbut.Pojo.Receipt;
import com.example.projectbut.R;
import com.example.projectbut.ReceiptDetailActivity;
import com.example.projectbut.Service.DataService;
import com.example.projectbut.adapter.ReceiptAdapter;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DateFragment extends Fragment {
    private View view;

    private DataService dataService = new DataService();
    private List<Receipt> receiptList;
    private RecyclerView receipt_list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        view = inflater.inflate(R.layout.fragment_date, container, false);

        receipt_list = (RecyclerView)view.findViewById(R.id.receipt_list);

        receipt_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        receipt_list.addItemDecoration(new DividerItemDecoration(getActivity()));


        receiptList = bundle.getParcelableArrayList("childReceiptList");
        //Log.d("List success", receiptList.get(0).getTitle());

        setAdapter(receipt_list);

        return view;
    }

    private void setAdapter(final RecyclerView receipt_list){
        final ReceiptAdapter receiptAdapter = new ReceiptAdapter(receiptList, getActivity(), dataService);
        receiptAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReceiptDetailActivity.class );
                //intent.putExtra("id", receiptList.get(receipt_list.getChildAdapterPosition(view)).getId());

                startActivity(intent);
            }
        });
        receipt_list.setAdapter(receiptAdapter);
    }

}
