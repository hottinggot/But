package com.example.projectbut;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectbut.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    DataService dataService = new DataService();
    List<Receipt> receiptList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final View gray_box = findViewById(R.id.gray_box);
        final RecyclerView receipt_list = findViewById(R.id.receipt_list);


        receipt_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        receipt_list.addItemDecoration(new DividerItemDecoration(this));

        dataService.select.selectAll().enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                try{
                    receiptList = response.body();
                    setAdapter(receipt_list);
                } catch (Exception e) {
                    System.out.println(e + "!!!!!!!!!");
                }

            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    void setAdapter(final RecyclerView receipt_list){
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
}