package com.example.todo;

import static com.example.todo.R.id.removeBtn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailItemActivity extends AppCompatActivity {

    FloatingActionButton goBackButton;
    Button editButton, removeButton;
    EditText editTitle, editDescription;
    TextView infoText, itemTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        int index = getIntent().getIntExtra("ITEM_INDEX", -1);

        goBackButton = findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editButton = findViewById(R.id.editBtn);
        removeButton = findViewById(removeBtn);
        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        infoText = findViewById(R.id.infotext2);
        itemTitle = findViewById(R.id.itemTitleTextView);

        itemTitle.setText(Storage.getInstance().getItems().get(index).getHeader());
        editTitle.setVisibility(View.GONE);
        editDescription.setVisibility(View.GONE);
        infoText.setVisibility(View.GONE);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoText.setText("REMOVING TASK");
                infoText.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        infoText.setVisibility(View.GONE);
                        Storage.getInstance().removeItem(index);
                        Storage.getInstance().saveList(getApplicationContext());
                        Storage.getInstance().loadList(getApplicationContext());
                        MainActivity.notifyListView();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                infoText.setVisibility(View.VISIBLE);
                infoText.setText("EDITING MODE ACTIVE");

                removeButton.setVisibility(View.GONE);

                editTitle.setVisibility(View.VISIBLE);
                editDescription.setVisibility(View.VISIBLE);

                editTitle.setText(Storage.getInstance().getItems().get(index).getHeader());
                editDescription.setText(Storage.getInstance().getItems().get(index).getDesc());

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infoText.setText("EDITING SUCCESSFULL");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                infoText.setVisibility(View.GONE);
                                removeButton.setVisibility(View.VISIBLE);
                                editTitle.setVisibility(View.GONE);
                                editDescription.setVisibility(View.GONE);
                                Storage.getInstance().editItem(index, editTitle.getText().toString(), editDescription.getText().toString());
                                Storage.getInstance().saveList(getApplicationContext());
                                itemTitle.setText(Storage.getInstance().getItems().get(index).getHeader());
                                MainActivity.notifyListView();
                            }
                        }, 1500);
                    }
                });
            }
        });

    }
}