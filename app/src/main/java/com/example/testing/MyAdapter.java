package com.example.testing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testing.R;
import com.squareup.picasso.Picasso;

public class MyAdapter extends ArrayAdapter<String> {
    Activity context;
    String [] code;
    String [] description;
    String [] image;

    public MyAdapter(Activity context, String[] code, String[] description, String[] image) {
        super(context, R.layout.customblog, code);
        this.context=context;
        this.code=code;
        this.description=description;
        this.image=image;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowv=inflater.inflate(R.layout.customblog,parent,false);
        TextView txtCode=rowv.findViewById(R.id.code);
        ImageView imageView=rowv.findViewById(R.id.image);
        TextView txtdes=rowv.findViewById(R.id.description);
        txtCode.setText(code[position]);
        Picasso.get().load(image[position]).into(imageView);
        txtdes.setText(description[position]);
        return rowv;

    }
}
