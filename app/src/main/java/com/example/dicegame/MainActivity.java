package com.example.dicegame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mainText;

    ImageView die1Image;
    ImageView die2Image;
    ImageView die3Image;
    TextView text2;

    // Holds the score
    int score;

    Random r;

    int die1,die2,die3;

    // Arraylist to hold all the dice values
    ArrayList<Integer> dice;

    // ArrayList to hold the dice Image Views
    ArrayList<ImageView> diceImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               rollDice(view);
            }
        });

        // Set the initial score
        score=0;



        mainText=(TextView) findViewById(R.id.mainText);

        die1Image=(ImageView)findViewById(R.id.die1Image);
        die2Image=(ImageView)findViewById(R.id.die2Image);
        die3Image=(ImageView)findViewById(R.id.die3Image);
        text2=(TextView)findViewById(R.id.text2);

        r=new Random();
        dice=new ArrayList<Integer>();
        diceImages=new ArrayList<ImageView>();
        diceImages.add(die1Image);
        diceImages.add(die2Image);
        diceImages.add(die3Image);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void rollDice(View v){
        mainText.setText("");

        die1=r.nextInt(6)+1;
        die2=r.nextInt(6)+1;
        die3=r.nextInt(6)+1;

        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int dieOfSet=0;dieOfSet<dice.size();dieOfSet++){
            String imageName="die_"+dice.get(dieOfSet)+".png";

            try {
                // Access files from assets
                InputStream stream=getAssets().open(imageName);

                Drawable d=Drawable.createFromStream(stream,null);
                diceImages.get(dieOfSet).setImageDrawable(d);

            }catch (IOException e){
               e.printStackTrace();
            }
        }

             if(die1==die2 && die1==die3){
                 // Score triples
                 int scoreData=die1*100;
                 score+=scoreData;
                 text2.setText("Congratulations,You made a triple!!");

             }else if (die1==die2 || die1==die3 || die2==die3){
                 // Scores a double
                 score+=50;
                 text2.setText("Congratulations,You made a double!!");

             }else{
                 Toast.makeText(getApplicationContext(),"You didn't score,try again",Toast.LENGTH_SHORT).show();
             }
        mainText.setText("Your Score : "+Integer.toString(score));



    }


}
