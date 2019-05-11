package com.evolet.placementmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evolet.placementmanagement.Classes.Contacts;
import com.evolet.placementmanagement.Classes.Data;
import com.evolet.placementmanagement.Classes.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {
    FirebaseDatabase fb;
    DatabaseReference db;
    ArrayList<Contacts> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<Contacts>();
        setContentView(R.layout.activity_friends);
        fb = FirebaseDatabase.getInstance();
        db = fb.getReference("referred");
        final ListView lv = findViewById(R.id.re);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childsnapshot : dataSnapshot.getChildren()) {
                    Contacts d = childsnapshot.getValue(Contacts.class);
                        data.add(d);
                        //  System.out.println(d.getSkills());
                }
                CustomAdapter1 adapter = new CustomAdapter1(getApplicationContext(),R.layout.list_item1,data);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

