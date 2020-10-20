package com.example.akfi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.akfi.backend.ItemActivity;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder>{
    ArrayList <ItemActivity> arrayList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, data, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            title = itemView.findViewById(R.id.text1);
            data = itemView.findViewById(R.id.text2);
            date = itemView.findViewById(R.id.text3);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //bind the data here
        ItemActivity currentItem = arrayList.get(position);
        holder.title.setText(currentItem.gettitle());
        holder.data.setText(currentItem.getdata());
        holder.date.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() { //Set the Recycle view size
        return arrayList.size();
    }


    public Adapter (ArrayList <ItemActivity> ItemActivity) { //constructor add the item
        this.arrayList = ItemActivity;
    }


}
