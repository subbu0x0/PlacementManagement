package com.evolet.placementmanagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.evolet.placementmanagement.Classes.Contacts;

import java.util.ArrayList;

public class CustomAdapter1 extends ArrayAdapter {
    ArrayList<Contacts> items = new ArrayList<>();
    Context c;
    public CustomAdapter1(@NonNull Context context, int resource, @NonNull ArrayList<Contacts> objects) {
        super(context, resource, objects);
        items = objects;
        c = context;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_item1, null);
        TextView textView = v.findViewById(R.id.rb);
        TextView textView1 = v.findViewById(R.id.dname);
        ImageView iv = v.findViewById(R.id.call);
        textView1.setText(items.get(position).getDname());
        textView.setText("Referred by: "+items.get(position).getRname());
        iv.setImageResource(R.mipmap.callb);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                Intent s = new Intent(Intent.ACTION_SENDTO);
                String m = "sms:" + items.get(position).getPno();
                s.setData(Uri.parse(m));
                String p = "tel:" + items.get(position).getPno();
                i.setData(Uri.parse(p));
                Intent chooserIntent = Intent.createChooser(i, "Call or SMS");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { s });
                c.startActivity(chooserIntent);
            }
        });
        return v;

    }
}
