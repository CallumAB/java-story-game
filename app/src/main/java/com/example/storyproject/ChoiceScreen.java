package com.example.storyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import com.example.backend.NodeMap;

import java.io.InputStream;

public class ChoiceScreen extends AppCompatActivity {

    NodeMap nodeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hides the action bar
        statusBarColour();
        setContentView(R.layout.activity_choice_screen);

        InputStream prc = getCSVRes();
        nodeMap = new NodeMap(prc);
    }

        //Sets the status bar colour to black
    private void statusBarColour(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().setStatusBarColor(getResources().getColor(R.color.black,this.getTheme()));
    }

    protected InputStream getCSVRes(){
        Resources res = getResources();
        return res.openRawResource(R.raw.data);
    }



    //NAVIGATE

        //Option 1
    public void optionHandler(View view){
        nodeMap.decision(1);
        updateText(view);
        updateHearts(view);
    }

        //Option 2
    public void optionHandler2(View view){
        nodeMap.decision(2);
        updateText(view);
        updateHearts(view);
    }

        //Bag Button
    public void bagHandler(View view){
        Intent myIntent = new Intent(ChoiceScreen.this,BagScreen.class);
        myIntent.putExtra("items", nodeMap.getItems());
        startActivity(myIntent);
    }



    //GAME UPDATES

        //Updates description and question text on the screen
    public void updateText(View view){
        String desc = nodeMap.currentNode().getDescription();
        String question;
        if(nodeMap.currentNode().getQuestion().equals("-")){
            question = "Press any button to continue!";
        }
        else{question = nodeMap.currentNode().getQuestion();}


        TextView tv = (TextView) findViewById(R.id.description);
        tv.setText(desc);

        tv = (TextView) findViewById(R.id.question);
        tv.setText(question);
    }


        //Changes alpha of hearts depending on how much health the player has
    public void updateHearts(View view){

        int hearts = nodeMap.getHearts();
        ImageView heart;

        switch(hearts) {
            case 0:
                heart = (ImageView) findViewById(R.id.heartOne);
                heart.setImageAlpha(0);
                break;
            case 1:
                heart = (ImageView) findViewById(R.id.heartTwo);
                heart.setImageAlpha(0);
                break;
            case 2:
                heart = (ImageView) findViewById(R.id.heartThree);
                heart.setImageAlpha(0);
                break;
            case 3:
                restoreHearts(view);
                break;
        }
    }

        //Resets the alpha of all hearts
    public void restoreHearts(View view){
        View[] heartViews = {findViewById(R.id.heartOne), findViewById(R.id.heartTwo), findViewById(R.id.heartThree)};
        for(View heart:heartViews){
            ImageView iv = (ImageView) heart;
            iv.setImageAlpha(255);
        }
    }



}