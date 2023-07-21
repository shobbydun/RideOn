package com.example.rideon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rideon.adapters.NavCategoryAdapter;
import com.example.rideon.models.NavCategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OfferFragment extends Fragment {


    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdapter navCategoryAdapter;
    ProgressBar progressBar;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category,container,false);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.cat_rec);
        recyclerView.setVisibility(View.GONE);


        //Category
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(),categoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);

        db.collection("OfferFragment")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                categoryModelList.add(navCategoryModel);
                                navCategoryAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });

        return root;
    }
}