package com.iongames.projectsecond;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder2>{

    Context context;
    ArrayList<Clients> newsArrayList;
    private OnNoteListener mOnNoteListener;
    public ClientAdapter(Context context,ArrayList<Clients> newsArrayList,OnNoteListener mOnNoteListener){

        this.context= context;
        this.newsArrayList=newsArrayList;
        this.mOnNoteListener= mOnNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder2(v,mOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        Clients clients = newsArrayList.get(position);
        holder.cost.setText(clients.number_phone);
        holder.name.setText(clients.client_name);

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView cost;
        OnNoteListener onNoteListener;
        public MyViewHolder2(View view,OnNoteListener onNoteListener){
            super(view);
            name= view.findViewById(R.id.name);
            cost= view.findViewById(R.id.cost);
            ConstraintLayout name2 = view.findViewById(R.id.item);
            this.onNoteListener = onNoteListener;
            name2.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteListener(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteListener(int position);


    }
}
