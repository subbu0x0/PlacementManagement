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
import android.widget.TextView;

import com.evolet.placementmanagement.Classes.Data;
import com.evolet.placementmanagement.Classes.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Trainers extends AppCompatActivity {
    FirebaseDatabase fb;
    DatabaseReference db;
    ArrayList<Data> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<Data>();
        setContentView(R.layout.activity_recruits);
        TextView tv = findViewById(R.id.tv);
        tv.setText("Applied for Training");
        fb = FirebaseDatabase.getInstance();
        db = fb.getReference("Data");
        final ListView lv = findViewById(R.id.re);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childsnapshot : dataSnapshot.getChildren()) {
                    Data d = childsnapshot.getValue(Data.class);
                    Double f = Double.valueOf(d.getScore());
                    if (d.getAppliedFor().equals("training")) {
                        data.add(d);
                        //  System.out.println(d.getSkills());
                    }
                }
                ArrayList<Items> names = new ArrayList<>();
                // System.out.println(data);
                for(int i =0; i<data.size();i++){
                    names.add(new Items(data.get(i).getApplicantsName(),data.get(i).getWorkExperience(), data.get(i).getSkills()));
                    //System.out.println(names.get(i).getSkills());
                }
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(),R.layout.list_item,names);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Details.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data.get(position) );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
