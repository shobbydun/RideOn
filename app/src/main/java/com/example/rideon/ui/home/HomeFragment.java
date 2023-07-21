package com.example.rideon.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rideon.R;
import com.example.rideon.adapters.HomeAdapter;
import com.example.rideon.adapters.PopularAdapters;
import com.example.rideon.adapters.RecommendedAdapter;
import com.example.rideon.adapters.ViewAllAdapter;
import com.example.rideon.models.HomeCategory;
import com.example.rideon.models.PopularModel;
import com.example.rideon.models.RecommendedModel;
import com.example.rideon.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    Context context;
    RecyclerView popularRec, homeCatRec, RecommendedRec;
    FirebaseFirestore db;

    //popular cars
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    //HomeCategory
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    //Recommended
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;

    //searching
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private  RecyclerView recyclerViewsearch;
    private ViewAllAdapter viewAllAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);

        db =FirebaseFirestore.getInstance();

        popularRec = root.findViewById(R.id.popular_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        RecommendedRec= root.findViewById(R.id.recommended_rec);

        scrollView= root.findViewById(R.id.scroll_view);
        progressBar= root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);



//popular Items
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//Home Category
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Recommended
        RecommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new  ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(),recommendedModelList);
        RecommendedRec.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        recyclerViewsearch = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.search_box);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewsearch.setAdapter(viewAllAdapter);
        recyclerViewsearch.setHasFixedSize(true);

        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                
                if (s.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else {
                    searchProducts(s.toString());
                }
                
            }
        });

        return root;
    }

    private void searchProducts(String type) {

        if (!type.isEmpty()){
            db.collection("ViewAllItems").whereEqualTo("name",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null){

                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }

                            }

                        }
                    });
        }
    }


}