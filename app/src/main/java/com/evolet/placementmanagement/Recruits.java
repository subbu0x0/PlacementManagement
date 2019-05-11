package com.evolet.placementmanagement;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.evolet.placementmanagement.Classes.Data;
import com.evolet.placementmanagement.Classes.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Recruits extends AppCompatActivity {
    FirebaseDatabase fb;
    DatabaseReference db;
    static ArrayList<Data> data;
    private static final String CSV_SEPARATOR = ",";
    private static final int PERMISSION_REQUEST_CODE = 1;
    Button b;
    String TAG = "permission.err";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       data = new ArrayList<Data>();
        setContentView(R.layout.activity_recruits);
        fb = FirebaseDatabase.getInstance();
        db = fb.getReference("Data");
        final ListView lv = findViewById(R.id.re);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childsnapshot : dataSnapshot.getChildren()) {
                    Data d = childsnapshot.getValue(Data.class);
                    Double f = Double.valueOf(d.getScore());
                    if (d.getAppliedFor().equals("placement") && f > 2 && f < 4) {
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
    public void export_data(View v){
        Toast.makeText(getApplicationContext(),"hello there",Toast.LENGTH_LONG).show();
    }
}
