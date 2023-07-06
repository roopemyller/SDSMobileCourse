package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class AddNewItem extends AppCompatActivity {

    EditText titleText, descText;
    Button createButton;
    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        titleText = findViewById(R.id.editTitle);
        descText = findViewById(R.id.editDesc);
        createButton = findViewById(R.id.addButton);
        infoText = findViewById(R.id.infotext);

        infoText.setVisibility(View.GONE);
        infoText.setText("CREATING TASK...");

        Storage s = Storage.getInstance();
        s.loadList(this);

        FloatingActionButton goBackButton = findViewById(R.id.goBackButton2);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.notifyListView();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleText.getText().toString().isEmpty()){
                    titleText.setTextColor(Color.RED);
                    titleText.setText("ADD TITLE!");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            titleText.setText("");
                            titleText.setTextColor(Color.BLACK);
                        }
                    }, 1500);
                }else{
                    infoText.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            infoText.setVisibility(View.GONE);
                            s.addItem(new TodoItem(titleText.getText().toString(), descText.getText().toString()));
                            s.saveList(getApplicationContext());
                            titleText.setText("");
                            descText.setText("");
                        }
                    }, 500);

                }

            }
        });

    }
}