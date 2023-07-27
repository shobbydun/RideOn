package com.example.rideon.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rideon.R;
import com.example.rideon.activities.ViewAllActivity;
import com.example.rideon.models.HomeCategory;
import com.example.rideon.models.HomeclassModel;

import java.util.List;

public class HomeclassAdapter extends RecyclerView.Adapter<HomeclassAdapter.ViewHolder> {

    Context context;
    List<HomeclassModel> categoryList;
    public HomeclassAdapter(Context context, List<HomeclassModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    @NonNull
    @Override
    public HomeclassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeclassAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_class_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeclassAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(categoryList.get(position).getImg_url()).into(holder.catImg);
        holder.name.setText(categoryList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("name", categoryList.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.home_class_img);
            name = itemView.findViewById(R.id.class_home_name);
        }
    }
}