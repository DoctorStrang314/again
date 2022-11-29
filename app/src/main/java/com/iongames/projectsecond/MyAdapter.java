package com.iongames.projectsecond;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<News> newsArrayList;

    public MyAdapter(Context context, ArrayList<News> newsArrayList){
        this.context= context;
        this.newsArrayList=newsArrayList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        News news = newsArrayList.get(position);
        holder.cost.setText(news.cost);
        holder.name.setText(news.name);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView cost;
        ClientAdapter.OnNoteListener onNoteListener;
        public MyViewHolder(View view){
            super(view);
            name= view.findViewById(R.id.name);
            cost= view.findViewById(R.id.cost);

        }

    }

}