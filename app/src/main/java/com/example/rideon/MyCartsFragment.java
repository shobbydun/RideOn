package com.example.rideon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rideon.activities.PlaceOderActivity;
import com.example.rideon.adapters.MyCartAdapter;
import com.example.rideon.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {


    FirebaseFirestore db;
    FirebaseAuth auth;

    TextView GrandTotalAmount;
    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    ProgressBar progressBar;
    Button buynow;
    int totalBill;

    public MyCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GrandTotalAmount = root.findViewById(R.id.textview_GrandTotal);


        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), cartModelList);
        recyclerView.setAdapter(cartAdapter);

        buynow = root.findViewById(R.id.buy_now);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                String documentId = documentSnapshot.getId();

                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                                cartModel.setDocumentId(documentId);

                                cartModelList.add(cartModel);
                                cartAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            
                            
                            calculateTotalAmount(cartModelList);
                            
                        }
                    }
                });

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getContext(), PlaceOderActivity.class);
               intent.putExtra("itemList", (Serializable) cartModelList);
               startActivity(intent);
            }
        });

        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> cartModelList) {

        double totalAmount = 0.0;
        for (MyCartModel myCartModel : cartModelList){
            totalAmount += myCartModel.getTotalPrice();
        }

        GrandTotalAmount.setText("Grand Total Amount: "+ totalAmount);

    }


}