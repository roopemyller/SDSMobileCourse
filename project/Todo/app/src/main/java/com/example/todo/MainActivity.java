package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    static ListView listView;
    FloatingActionButton addNewItem;
    TextView informationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Storage storage = Storage.getInstance();
        storage.loadList(this);

        informationText = findViewById(R.id.informationTextView);
        listView = findViewById(R.id.listView);
        addNewItem = findViewById(R.id.addNewItemButton);

        ItemAdapter itemAdapter = new ItemAdapter(this, storage.getItems());
        listView.setAdapter(itemAdapter);

        if(listView.getAdapter().getCount() > 0){
            informationText.setText(listView.getAdapter().getCount() + " TASKS REMAINING");
        }


        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewItem.class);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailItemActivity.class);
                intent.putExtra("ITEM_INDEX", i);
                startActivity(intent);
                finish();
            }
        });
    }

    public static void notifyListView() {
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }
}