package com.example.video3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.ims.ImsMmTelManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] items;
    String[] prices;
    String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        listView = (ListView) findViewById(R.id.lv);
        items = res.getStringArray(R.array.items);
        prices = res.getStringArray(R.array.prices);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter itemAdapter = new ItemAdapter(this, items, prices, descriptions);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("ITEM_INDEX", i);

                startActivity(intent);
            }
        });

    }
}