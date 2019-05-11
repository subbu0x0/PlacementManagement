package com.evolet.placementmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
