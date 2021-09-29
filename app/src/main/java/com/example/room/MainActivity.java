package com.example.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private MyViewModel myViewModel;
    private static final int CREATE_ACTIVITY_REQUEST_CODE = 1;
    private EntryListAdapter entryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list_recycler_view);
        entryListAdapter = new EntryListAdapter(this);
        recyclerView.setAdapter(entryListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.ic_input_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivityForResult(intent, CREATE_ACTIVITY_REQUEST_CODE);
            }
        });

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        myViewModel.getAllEntries().observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(@Nullable List<Entry> entries) {
                entryListAdapter.setEntries(entries);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            final String id = UUID.randomUUID().toString();
            Entry entry = new Entry(id, data.getStringExtra(CreateActivity.DESTINATION_ADDED), data.getStringExtra(CreateActivity.COUNTRY_ADDED));
            myViewModel.insert(entry);

            Toast.makeText(getApplicationContext(), "Entry saved", Toast.LENGTH_LONG).show();
        }
    }
}
