package com.example.rideon.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rideon.R;
import com.example.rideon.adapters.NavCategoryDetailed;
import com.example.rideon.models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class offersActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCategoryDetailed adapter;
    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        String name = getIntent().getStringExtra("name");

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavCategoryDetailed(this,list);
        recyclerView.setAdapter(adapter);

        //get Sports Car
        if (name != null && name.equalsIgnoreCase("Sports Car")) {
            db.collection("OfferFragment").whereEqualTo("name", "Sports Car").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        //get SUVs
        if (name != null && name.equalsIgnoreCase("SUVs")) {
            db.collection("OfferFragment").whereEqualTo("name", "SUVs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        //get Family Car
        if (name != null && name.equalsIgnoreCase("Family Car ")) {
            db.collection("OfferFragment").whereEqualTo("name", "Family Car ").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        //get HatchBack
        if (name != null && name.equalsIgnoreCase("HatchBack")) {
            db.collection("OfferFragment").whereEqualTo("name", "HatchBack").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        //get Classic One
        if (name != null && name.equalsIgnoreCase("Classic One")) {
            db.collection("OfferFragment").whereEqualTo("name", "Classic One").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }

    }
}