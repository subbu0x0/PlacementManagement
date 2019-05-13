package com.evolet.placementmanagement;

import android.content.Context;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.placementmanagement.Classes.Data;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Details extends AppCompatActivity {
    private TextView n,a,f,e,p,as,sx,h,ca,ce,dt,poy,w,slk,s,c,ts,sk;
    private static Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        data = (Data) bundle.getSerializable("data");
        n = findViewById(R.id.n);
        a = findViewById(R.id.a);
        f = findViewById(R.id.f);
        e = findViewById(R.id.e);
        p = findViewById(R.id.p);
        as = findViewById(R.id.as);
        sx = findViewById(R.id.sx);
        h = findViewById(R.id.h);
        ca = findViewById(R.id.ca);
        ce = findViewById(R.id.ce);
        dt = findViewById(R.id.dt);
        poy = findViewById(R.id.poy);
        w = findViewById(R.id.w);
        slk = findViewById(R.id.slk);
        s = findViewById(R.id.s);
        c = findViewById(R.id.c);
        ts = findViewById(R.id.ts);
        sk = findViewById(R.id.status);
        sk.setText(""+data.getStatus());
        n.setText(""+data.getApplicantsName());
        a.setText(""+data.getAge());
        f.setText(""+data.getFathersName());
        e.setText(""+data.getEmail());
        p.setText(""+data.getPhoneNo());
        as.setText(""+data.getAddress());
        sx.setText(""+data.getSex());
        h.setText(""+data.getHighestQualification());
        ca.setText(""+data.getCgpa());
        ce.setText(""+data.getCollegeName());
        dt.setText(""+data.getDept());
        poy.setText(""+data.getPassedOut());
        w.setText(""+data.getWorkExperience());
        slk.setText(""+data.getSoftwareLanguages());
        c.setText(""+data.getStartImmediately());
        s.setText(""+data.getScore());
        ts.setText(""+data.getSkills());

    }
    public void updateStatus(View v){
        Intent i = new Intent(this,UStatus.class);
        Bundle b = new Bundle();
        b.putString("name",data.getApplicantsName());
        i.putExtras(b);
        startActivity(i);
    }
}
