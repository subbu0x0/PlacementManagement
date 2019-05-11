package com.evolet.placementmanagement.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.evolet.placementmanagement.Classes.Contacts;
import com.evolet.placementmanagement.Classes.Data;
import com.evolet.placementmanagement.MainActivity;
import com.evolet.placementmanagement.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    Data data;
    EditText l,s,tech,ms;
    RadioGroup start,a;
    RadioButton s1,a1;
    String lang,appl,score,st,skills,rid,ms1;
    static final int PICK_CONTACT = 1;
    ArrayList<Contacts> contacts = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment3, container, false);
       adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,names);
        ListView lv = view.findViewById(R.id.lv);
       lv.setAdapter(adapter);
        Button b1 = view.findViewById(R.id.finish);
        Button b =view.findViewById(R.id.add);
        l = view.findViewById(R.id.languages);
        a = view.findViewById(R.id.app);
        s = view.findViewById(R.id.score);
        start = view.findViewById(R.id.start);
        tech = view.findViewById(R.id.skills);
        ms = view.findViewById(R.id.score1);
        a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tr:
                        appl = "training";
                        break;
                    case R.id.pl:
                        appl = "placement";
                }
            }
        });
        start.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.yes:
                       st = "yes";
                       break;
                    case R.id.no:
                        st = "no";
                        break;
                }
            }
        });
        data =(Data) getArguments().getSerializable("d1");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = l.getText().toString();
                score = s.getText().toString();
                skills = tech.getText().toString();
                ms1 = ms.getText().toString();
                if(TextUtils.isEmpty(lang) || TextUtils.isEmpty(appl) ||
                        TextUtils.isEmpty(score) ||TextUtils.isEmpty(st) ||
                        TextUtils.isEmpty(skills)||TextUtils.isEmpty(ms1)){
                    Toast.makeText(getActivity(),"All fields must be filled",Toast.LENGTH_LONG).show();
                }
                else {
                    data.setSoftwareLanguages(lang);
                    data.setAppliedFor(appl);
                    Double per = Double.valueOf(score)*100/Double.valueOf(ms1);
                    data.setScore(String.valueOf(per));
                    data.setStartImmediately(st);
                    data.setSkills(skills);
                    addTodb();
                    Toast.makeText(getActivity(),"successfully added to database",Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
        return view;
    }
    public void addContact(){
        check();
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);

    }
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        switch(reqCode)
        {

            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK)
                {
                    Uri contactData = data.getData();
                    Cursor c = getActivity().getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst())
                    {
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =
                                c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1"))
                        {
                            Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            String cName = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            Contacts c1 = new Contacts(cNumber,"",cName,"");
                            if(!names.contains(cName)) {
                                contacts.add(c1);
                                names.add(cName);
                            }
                            else{
                                Toast.makeText(getContext(),"already added",Toast.LENGTH_LONG)
                                        .show();
                            }
                           adapter.notifyDataSetChanged();
                           // Toast.makeText(getContext(), cNumber, Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                default:System.out.println("default");
        }
    }
    private void check(){


        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
//                    getContacts();
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    Toast.makeText(getActivity(), "Read contacts permission is required to function app correctly", Toast.LENGTH_LONG).show();
                }else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_CONTACTS},
                            1);                }

            }
        }
    }
    public void addTodb(){
        FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
        DatabaseReference db = fbdb.getReference("Data");
        rid = db.push().getKey();
        data.setRid(rid);
        for(int i=0;i<contacts.size();i++){
            contacts.get(i).setRname(data.getApplicantsName());
            contacts.get(i).setId(rid);
        }
        db.child(data.getApplicantsName()).setValue(data);
        DatabaseReference db1 = fbdb.getReference("referred");
        for(int j =0 ;j<contacts.size();j++){
        db1.child(rid).setValue(contacts.get(j));
        }
    }
}
