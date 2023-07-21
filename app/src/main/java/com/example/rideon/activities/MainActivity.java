package com.example.rideon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rideon.HomeActivity;
import com.example.rideon.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        if (auth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            Toast.makeText(this, "Please Wait You Are Already A member", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void Registration(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }
}