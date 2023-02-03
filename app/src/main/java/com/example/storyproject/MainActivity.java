package com.example.storyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        statusBarColour();
        setContentView(R.layout.activity_main);
    }

        //Sets the status bar colour to black
    private void statusBarColour(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
    }

    public void acceptQuestHandler(View view){
        Intent myIntent = new Intent(MainActivity.this,ChoiceScreen.class);
        startActivity(myIntent);
    }
}