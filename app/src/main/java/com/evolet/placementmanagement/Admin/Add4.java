package com.evolet.placementmanagement.Admin;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.evolet.placementmanagement.Classes.recruiters;
import com.evolet.placementmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add4 extends AppCompatActivity {
    EditText n,e,pno,doj;
    TextInputEditText p;
    String type,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add4);
        n = findViewById(R.id.name);
        e = findViewById(R.id.email);
        p = findViewById(R.id.pwd);
        pno = findViewById(R.id.phone);
        doj = findViewById(R.id.doj);
        type = "trainers";
    }
    public void addOp(View v) {
        final String name, email, pwd, phone, date;
        name = n.getText().toString();
        email = e.getText().toString();
        pwd = p.getText().toString();
        phone = pno.getText().toString();
        date = doj.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(date)) {

        } else {
            final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            final DatabaseReference dbRef = firebaseDatabase.getReference("users").child(type);
            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Add4.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){

                    }
                    else{
                        uid = mAuth.getCurrentUser().getUid();
                        DatabaseReference dbref1 = firebaseDatabase.getReference("login").child(uid);
                        dbref1.child("email").setValue(email);
                        dbref1.child("id").setValue(uid);
                        dbref1.child("type").setValue(type);
                        recruiters r = new recruiters(name,email,phone,date,type);
                        dbRef.child(uid).setValue(r);
                        Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
    }
}
