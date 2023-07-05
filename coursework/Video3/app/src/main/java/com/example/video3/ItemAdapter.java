package com.example.video3;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] items;
    String[] prices;
    String[] descriptions;

    public ItemAdapter(Context c, String[] i, String[] p, String[] d){
        items = i;
        prices = p;
        descriptions = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.listview_item, null);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView price = (TextView) v.findViewById(R.id.price);
        TextView description = (TextView) v.findViewById(R.id.description);

        String nam = items[i];
        String prc = prices[i];
        String desc  = descriptions[i];

        name.setText(nam);
        price.setText(prc);
        description.setText(desc);

        return v;
    }
}
