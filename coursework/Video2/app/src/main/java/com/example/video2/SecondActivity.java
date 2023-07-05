package com.example.video2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button goBack = (Button) findViewById(R.id.goBackBtn);
        TextView textView = (TextView) findViewById(R.id.textView);

        if(getIntent().hasExtra("something")){
            textView.setText(getIntent().getExtras().getString("something"));
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}