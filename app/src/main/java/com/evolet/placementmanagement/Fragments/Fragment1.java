package com.evolet.placementmanagement.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.placementmanagement.Classes.Data;
import com.evolet.placementmanagement.R;

public class Fragment1 extends Fragment {
    Data d = new Data();
    EditText n,a,f,e,p,add;
    String na,ag,fa,em,ph,ad,se;
    RadioGroup s;
    RadioButton sex;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1, container, false);
        Button b1 = view.findViewById(R.id.next);
        n = view.findViewById(R.id.name);
        a = view.findViewById(R.id.age);
        f = view.findViewById(R.id.fname);
        e = view.findViewById(R.id.email);
        p = view.findViewById(R.id.phone);
        add = view.findViewById(R.id.address);
        s = view.findViewById(R.id.rg);
        s.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton2:
                        se = "Male";
                        break;
                    case R.id.radioButton3:
                        se = "Female";
                        break;
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                na = n.getText().toString();
                ag = a.getText().toString();
                fa = f.getText().toString();
                em = e.getText().toString();
                ph = p.getText().toString();
                ad = add.getText().toString();
                if(TextUtils.isEmpty(na) || TextUtils.isEmpty(ag) ||
                        TextUtils.isEmpty(fa) ||TextUtils.isEmpty(em) ||
                        TextUtils.isEmpty(ph)||TextUtils.isEmpty(ad)||TextUtils.isEmpty(se)||ph.length()!=10){
                    Toast.makeText(getActivity(),"All fields must be filled or mobile number should have 10 digits",Toast.LENGTH_LONG).show();
                }
                else {
                    d.setApplicantsName(na);
                    d.setAge(ag);
                    d.setFathersName(fa);
                    d.setAddress(ad);
                    d.setPhoneNo(ph);
                    d.setEmail(em);
                    d.setSex(se);
                    switch_fragment();
                }
            }
        });
        return view;
}
    public void switch_fragment() {
        Fragment newFragment = new Fragment2();
        Bundle bundle=new Bundle();
        bundle.putSerializable("d1",d);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frames, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
