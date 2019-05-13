package com.evolet.placementmanagement;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class Trainers extends AppCompatActivity {
    FirebaseDatabase fb;
    DatabaseReference db;
    static ArrayList<Data> data;
    private static final String CSV_SEPARATOR = ",";
    private static final int REQUEST_WRITE_PERMISSION = 786;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<Data>();
        setContentView(R.layout.activity_recruits);
        TextView tv = findViewById(R.id.tv);
        tv.setText("Applied for Training");
        fb = FirebaseDatabase.getInstance();
        db = fb.getReference("Data");
        db.keepSynced(false);
        final ListView lv = findViewById(R.id.re);
        db.orderByChild("status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
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
    public void export_data(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            writeFile();
        }
        }
  /*  private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Trainers.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
            }
        else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Trainers.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Trainers.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Trainers.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

     //  @Override
   public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.d("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }*/
       @Override
       public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
           if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               writeFile();
           }
       }
    private void writeFile(){
        String folder_main = "/Data_Trainees";
        String rootPath = Environment.getExternalStorageDirectory()
                +folder_main;
        File root = new File(rootPath);
        if (!root.exists()) {
            root.mkdirs();
        }
        try
        {
            File f = new File(rootPath + "/trainees.csv");
            if (f.exists()) {
                f.delete();
            }
              f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            bw.newLine();
            bw.write("Name,Age,Father's Name,Email,Phone No,Address,Sex,Highest Qualification,CGPA,Passed Out Year," +
                    "College Name,Department,Work Experience,Software Languages,Assessment Score,Can Start Immediately,Skills,Status");

            for (Data product : data)
            {

                StringBuffer oneLine = new StringBuffer();
                oneLine.append(product.getApplicantsName().trim().length() == 0? "" : removeCommas(product.getApplicantsName()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getAge().trim().length() == 0? "" : product.getAge());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getFathersName().trim().length() == 0? "" : removeCommas(product.getFathersName()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getEmail().trim().length() == 0? "" : removeCommas(product.getEmail()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getPhoneNo().trim().length() == 0? "" : product.getPhoneNo());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getAddress().trim().length() == 0? "" : product.getAddress().replaceAll(","," ").replaceAll("\\r", " ").replaceAll("\\n", " "));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getSex().trim().length() == 0? "" : product.getSex());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getHighestQualification().trim().length() == 0? "" : removeCommas(product.getHighestQualification()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getCgpa().trim().length() == 0? "" : product.getCgpa());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getPassedOut().trim().length() == 0? "" : product.getPassedOut());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getCollegeName().trim().length() == 0? "" : removeCommas(product.getCollegeName()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getDept().trim().length() == 0? "" : removeCommas(product.getDept()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getWorkExperience().trim().length() == 0? "" : product.getWorkExperience());
                oneLine.append(CSV_SEPARATOR);
                String str1 = product.getSoftwareLanguages().replaceAll(","," ").replaceAll("\\r", "").replaceAll("\\n", "");
                oneLine.append(str1.trim().length() == 0? "" : str1);
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getScore().trim().length() == 0? "" : product.getScore());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getStartImmediately().trim().length() == 0? "" : product.getStartImmediately());
                oneLine.append(CSV_SEPARATOR);
                String str2 = product.getSkills().replaceAll(","," ").replaceAll("\\r", " ").replaceAll("\\n", " ");
                oneLine.append(str2.trim().length() == 0? "" : str2);
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getStatus().trim().length() == 0? "" : removeCommas(product.getStatus()));
                oneLine.append(CSV_SEPARATOR);
                bw.newLine();
                bw.write(oneLine.toString());
            }
            bw.flush();
            bw.close();
            Toast.makeText(getApplicationContext(),"sucessfull",
                    Toast.LENGTH_LONG).show();
        }
        catch (UnsupportedEncodingException e) {
            Toast.makeText(getApplicationContext()," unot sucessfull",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            Toast.makeText(getApplicationContext()," fnot sucessfull",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext()," inot sucessfull",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public String removeCommas(String str){
        String splitted[] = str.split(",");
        StringBuffer sb = new StringBuffer();
        String retrieveData = "";
        for(int i =0; i<splitted.length; i++){
            retrieveData = splitted[i];
            if((retrieveData.trim()).length()>0){

                if(i!=0){
                    sb.append(" ");
                }
                sb.append(retrieveData);
            }
        }
        String str1 = sb.toString();
        //System.out.println(str);
        return str1;
    }
}

