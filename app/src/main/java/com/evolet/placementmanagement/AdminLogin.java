package com.evolet.placementmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    EditText mail, pwd;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        mail = findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        Button Login = findViewById(R.id.login);
        TextView fp = findViewById(R.id.fp);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("login");
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
        }
       /* mAuth.createUserWithEmailAndPassword("spravan0@gmail.com","subha123").addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    //do whatever
                }
                else{
                    user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference dbref = databaseReference.child(user_id);
                    dbref.child("email").setValue("spravan0@gmail.com");
                    dbref.child("id").setValue(user_id);
                    dbref.child("type").setValue("admin");
                }
            }
        });*/
    }
            public void onClick(View v) {
                if(TextUtils.isEmpty(mail.getText().toString())||TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"fields shouldn't be empty",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    String m = mail.getText().toString();
                    String p = pwd.getText().toString();
                    mAuth.signInWithEmailAndPassword(m,p).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Incorrect password and gmail or network failure",
                                        Toast.LENGTH_LONG).show();
                            }
                            else {
                                String id = mAuth.getCurrentUser().getUid();

                                DatabaseReference db = databaseReference.child(id).child("type");
                                db.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String t = dataSnapshot.getValue(String.class);
                                        if (t.equals("admin")) {
                                            Intent i = new Intent(getApplicationContext(), AdminHome.class);
                                            startActivity(i);
                                        }
                                        else if(t.equals("operators")){
                                            Intent i1 = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(i1);
                                        }
                                        else if(t.equals("recruiters")){
                                            Intent i2 = new Intent(getApplicationContext(),Recruits.class);
                                           // startActivity(i2);
                                        }
                                        else if(t.equals("hr")){
                                            Intent i3 = new Intent(getApplicationContext(),Hr.class);
                                            //startActivity(i3);
                                        }
                                        else if(t.equals("trainers")){
                                           Intent i4 = new Intent(getApplicationContext(),Train.class);
                                            startActivity(i4);

                                        }
                                     //   System.out.println("value" +t);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Log.d("type","db Error");
                                    }
                                });
                            }
                        }
                    });
                }
            }
}
