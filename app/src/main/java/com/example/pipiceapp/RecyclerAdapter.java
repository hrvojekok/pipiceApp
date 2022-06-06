package com.example.pipiceapp;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Item> itemArrayList;
    private OnItemListener onItemListener;

    public RecyclerAdapter(ArrayList<Item> itemArrayList, OnItemListener onItemListener){
        this.itemArrayList = itemArrayList;
        this.onItemListener = onItemListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView phoneText;
        private ImageView imageView;
        OnItemListener onItemListener;
        public MyViewHolder(final View view, OnItemListener onItemListener){
            super(view);
            phoneText = view.findViewById(R.id.phoneName);
            imageView = view.findViewById(R.id.image);
            this.onItemListener = onItemListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAbsoluteAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String phoneName = itemArrayList.get(position).getPhoneName();
        String imageID = itemArrayList.get(position).getImageID();
        holder.phoneText.setText(phoneName);
        holder.imageView.setImageResource(Integer.parseInt(imageID));


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
    public interface OnItemListener{
        void onItemClick(int position);
    }
}
