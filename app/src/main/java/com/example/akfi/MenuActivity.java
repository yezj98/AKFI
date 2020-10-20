package com.example.akfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.akfi.backend.ItemActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemActivity> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        retrieveFirebase();
        build();

    }

    public void build() {

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        adapter = new Adapter(itemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        animation();
    }

    private void animation() {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder drag, @NonNull RecyclerView.ViewHolder target) {

                int dragPosition = drag.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();

                Collections.swap(itemList, dragPosition, targetPosition);
                adapter.notifyItemMoved(dragPosition, targetPosition);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void retrieveFirebase() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd`MM`YYYY");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collect((Map<String, Object>) snapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void collect(Map<String, Object> user) {

        ArrayList<Object> dataList = new ArrayList<>();
        ArrayList<Object> titleList = new ArrayList<>();
        ArrayList<Object> dateList = new ArrayList<>();


        for (Map.Entry<String, Object> entry : user.entrySet()) { //take out all the data

            Map singleUser = (Map) entry.getValue();

            dataList.add(singleUser.get("data"));
            titleList.add(singleUser.get("title"));
            dateList.add(singleUser.get("date"));
        }

        for (int i = 0; i < dataList.size(); i++) {
            itemList.add(new ItemActivity((String) titleList.get(i), (String) dataList.get(i),(String) dateList.get(i)));
            adapter.notifyDataSetChanged();
            Log.d("sss", "" + dataList.get(i));
        }
    }
}