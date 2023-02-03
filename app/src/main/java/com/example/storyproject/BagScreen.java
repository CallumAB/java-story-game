package com.example.storyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BagScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        statusBarColour();
        setContentView(R.layout.activity_bag_screen);
        updateBag();
    }

        //Sets the status bar colour to black
    private void statusBarColour(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
    }

        //Display all items in the bags by updating the imageviews
    public void updateBag(){
        Intent myIntent = getIntent();
        String[] items = myIntent.getStringArrayExtra("items");
        System.out.println(items[0]);

        int count = 1;
        for(String item: items){
            if(item != null){
                displayItem(count, item);
                count += 1;
            }
        }
    }


    public void displayItem(int id, String item){
        ImageView iv = findViewById(R.id.imageView+id);
        if(item.equalsIgnoreCase("invisPotion")){iv.setImageResource(R.drawable.invispotion);}
        else if(item.equalsIgnoreCase("cheatDeath")){iv.setImageResource(R.drawable.cheatdeath);}
        else if(item.equalsIgnoreCase("map")){iv.setImageResource(R.drawable.dungeonmap);}
        else if(item.equalsIgnoreCase("sword")){iv.setImageResource(R.drawable.sword);}
        else if(item.equalsIgnoreCase("strengthPotion")){iv.setImageResource(R.drawable.strengthpotion);}

    }
}