package com.evolet.placementmanagement;

import android.content.Intent;
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
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        FragmentManager fm = getSupportFragmentManager();
         ft = fm.beginTransaction();

        // The id specified here identifies which ViewGroup to
        // append the Fragment to.
        ft.add(R.id.frames, new Fragment1());
        ft.commit();
    }
    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            Intent i  = new Intent(this,MainActivity.class);
            startActivity(i);
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}
