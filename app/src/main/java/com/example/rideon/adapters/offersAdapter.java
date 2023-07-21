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
import com.example.rideon.activities.NavCategoryActivity;
import com.example.rideon.models.NavCategoryModel;
import com.example.rideon.models.offersModel;

import java.util.List;

public class offersAdapter extends RecyclerView.Adapter<offersAdapter.ViewHolder> {

    Context context;
    List<offersModel> list;

    public offersAdapter(Context context, List<offersModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public offersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull offersAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageview);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.best.setText(list.get(position).getBest());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("name", list.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView name,description,best;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
            description = itemView.findViewById(R.id.nav_cat_description);
            best = itemView.findViewById(R.id.nav_cat_horse);
        }
    }
}
