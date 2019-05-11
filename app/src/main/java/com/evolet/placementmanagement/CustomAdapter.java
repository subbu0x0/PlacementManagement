package com.evolet.placementmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evolet.placementmanagement.Classes.Items;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    ArrayList<Items> items = new ArrayList<>();
    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Items> objects) {
        super(context, resource, objects);
        items = objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_item, null);
        TextView textView = v.findViewById(R.id.na);
        TextView textView1 = v.findViewById(R.id.ag);
        TextView textView2 = v.findViewById(R.id.sk);
        textView.setText(items.get(position).getN());
        textView1.setText("work experience: "+items.get(position).getAge());
        textView2.setText("skills: "+items.get(position).getSkills());
        return v;

    }
}
