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
import com.example.rideon.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

     Context context;
     List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularModelList.get(position).getImage_url()).into(holder.imageView);
        holder.name.setText(popularModelList.get(position).getName());
        holder.rating.setText(popularModelList.get(position).getRating());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.discount.setText(popularModelList.get(position).getDiscount());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ViewAllActivity.class);
                intent.putExtra("name", popularModelList.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,rating,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_descri);
            rating = itemView.findViewById(R.id.pop_rating);
            discount = itemView.findViewById(R.id.pop_discount);
        }
    }
}
