package com.example.video2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startSecondActivity = (Button) findViewById(R.id.startActivityBtn);
        Button startGoogle = (Button) findViewById(R.id.startGoogleBtn);

        startSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("something", "Hello World!");
                startActivity(intent);
            }
        });
        startGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webPage = Uri.parse("https://www.facebook.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                if (webIntent.resolveActivity(getPackageManager()) == null){
                    startActivity(webIntent);
                }else{
                    System.out.println("no vittu ei");
                }
            }
        });
    }
}