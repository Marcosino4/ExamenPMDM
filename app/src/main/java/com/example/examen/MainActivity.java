package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeToAdd(View view) {
        Intent nIntent = new Intent(MainActivity.this, AddToFireBaseActivity.class);
        startActivity(nIntent);
    }
    public void changeToShow(View view) {
        Intent nIntent = new Intent(MainActivity.this, ShowFireBaseDataActivity.class);
        startActivity(nIntent);
    }
}