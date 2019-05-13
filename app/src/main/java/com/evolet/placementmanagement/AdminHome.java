package com.evolet.placementmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.evolet.placementmanagement.Admin.Add1;
import com.evolet.placementmanagement.Admin.Add2;
import com.evolet.placementmanagement.Admin.Add3;
import com.evolet.placementmanagement.Admin.Add4;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void op(View v){
        Intent i = new Intent(this, Add1.class);
        startActivity(i);
    }
    public void recruit(View v){
        Intent i = new Intent(this, Add2.class);
       startActivity(i);
    }
    public void hr(View v){
       Intent i = new Intent(this, Add3.class);
        startActivity(i);
    }
    public void tr(View v){
        Intent i = new Intent(this, Add4.class);
        startActivity(i);
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
