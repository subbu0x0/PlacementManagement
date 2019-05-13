package com.evolet.placementmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UStatus extends AppCompatActivity {
    String status,s1;
    private EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustatus);
        e = findViewById(R.id.st);
        Bundle b = getIntent().getExtras();
        s1  = b.getString("name");

    }
    public void update(View v){
        status = e.getText().toString();
        if(!TextUtils.isEmpty(status)){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Data");
        db.child(s1).child("status").setValue(status);
        Toast.makeText(this,"Successfully updated",Toast.LENGTH_LONG).show();
        }
    }
}
