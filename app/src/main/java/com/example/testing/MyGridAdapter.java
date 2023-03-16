package com.example.testing;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyGridAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;
    private final String[] descriptions;

    public MyGridAdapter(Activity context, String[] names, String[] descriptions) {
        super(context, R.layout.customgrid, names);
        this.context = context;
        this.names = names;
        this.descriptions = descriptions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v1 = inflater.inflate(R.layout.customgrid, null, true);
        TextView nameTextView = v1.findViewById(R.id.name);
        TextView descTextView = v1.findViewById(R.id.description);
        nameTextView.setText(names[position]);
        descTextView.setText(descriptions[position]);
        return v1;
    }
}
