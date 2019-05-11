package com.evolet.placementmanagement.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.evolet.placementmanagement.Classes.Data;
import com.evolet.placementmanagement.R;

public class Fragment2 extends Fragment {
    private Data data;
    EditText h,c,cn,d,p,w;
    String hq,cg,cna,dp,pa,we;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment2, container, false);
        Button b1 = view.findViewById(R.id.next1);
        h = view.findViewById(R.id.q);
        c = view.findViewById(R.id.gpa);
        cn = view.findViewById(R.id.cname);
        d = view.findViewById(R.id.dept);
        p = view.findViewById(R.id.year);
        w = view.findViewById(R.id.exp);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hq = h.getText().toString();
                cg = c.getText().toString();
                cna = cn.getText().toString();
                dp = d.getText().toString();
                pa = p.getText().toString();
                we = w.getText().toString();
                if(TextUtils.isEmpty(hq) || TextUtils.isEmpty(cg) ||
                        TextUtils.isEmpty(cna) ||TextUtils.isEmpty(dp) ||
                        TextUtils.isEmpty(pa)||TextUtils.isEmpty(we)){
                    Toast.makeText(getActivity(),"All fields must be filled",Toast.LENGTH_LONG).show();
                }
                else {
                    data =(Data) getArguments().getSerializable("d1");
                    data.setHighestQualification(hq);
                    data.setCgpa(cg);
                    data.setCollegeName(cna);
                    data.setDept(dp);
                    data.setPassedOut(pa);
                    data.setWorkExperience(we);
                    switch_fragment();
                }
            }
        });
        return view;
    }
    public void switch_fragment() {
        Fragment newFragment = new Fragment3();
        Bundle bundle=new Bundle();
        bundle.putSerializable("d1",data);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frames, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
