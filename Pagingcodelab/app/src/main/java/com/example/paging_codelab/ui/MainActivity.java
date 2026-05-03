package com.example.paging_codelab.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paging_codelab.R;
import com.example.paging_codelab.adapter.ItemPagingAdapter;
import com.example.paging_codelab.model.Item;
import com.example.paging_codelab.paging.ItemPagingSource;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemPagingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ItemPagingAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Configure Pager
        Pager<Integer, Item> pager = new Pager<>(
                new PagingConfig(20, 10, false),
                ItemPagingSource::new
        );

        // Convert Pager to LiveData and observe
        PagingLiveData.getLiveData(pager).observe(this, pagingData -> {
            adapter.submitData(getLifecycle(), pagingData);
        });
    }
}
