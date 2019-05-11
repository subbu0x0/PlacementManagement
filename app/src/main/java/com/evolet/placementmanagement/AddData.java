package com.evolet.placementmanagement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.evolet.placementmanagement.Fragments.Fragment1;
import com.evolet.placementmanagement.Fragments.Fragment2;
import com.evolet.placementmanagement.Fragments.Fragment3;

public class AddData extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // The id specified here identifies which ViewGroup to
        // append the Fragment to.
        ft.add(R.id.frames, new Fragment1());
        ft.commit();
    }
}
