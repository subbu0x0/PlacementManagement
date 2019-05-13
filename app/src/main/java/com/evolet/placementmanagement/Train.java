package com.evolet.placementmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Train extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
    }
    public void aft(View v){
        Intent i4 = new Intent(getApplicationContext(),Trainers.class);
        startActivity(i4);
    }
    public void rc(View v){
        Intent i4 = new Intent(getApplicationContext(),Friends.class);
        startActivity(i4);
    }
    public void onBackPressed(){
        super.onBackPressed();
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    Intent i = new Intent(getApplicationContext(),AdminLogin.class);
                    startActivity(i);

                }
                else {
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
        firebaseAuth.signOut();
    }
}
