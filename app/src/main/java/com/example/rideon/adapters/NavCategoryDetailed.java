package com.example.rideon.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rideon.R;
import com.example.rideon.models.NavCategoryDetailedModel;

import java.util.List;

public class NavCategoryDetailed extends RecyclerView.Adapter<NavCategoryDetailed.ViewHolder> {

    Context context;
    List<NavCategoryDetailedModel> list;

    public NavCategoryDetailed(Context context, List<NavCategoryDetailedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NavCategoryDetailed.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryDetailed.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
//        holder.description.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cat_nav_detailed_img);
            name = itemView.findViewById(R.id.nav_cat_detailed_description);
    //        price = itemView.findViewById(R.id.pricedetailed);
            description = itemView.findViewById(R.id.nav_cat_description);

        }
    }
}
