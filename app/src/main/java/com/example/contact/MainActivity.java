package com.example.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tekst;
    ImageView obraz;
    int pic, rand = 0, sound = 0;
    String name = "", temp = "";
    MediaPlayer media=null;
    boolean play=false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        obraz = findViewById(R.id.iv);
        tekst = findViewById(R.id.textView);

        randNum();

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(media != null)
                {
                    media.stop();
                    media = null;
                }
                else if(play) {
                    media = MediaPlayer.create(getApplicationContext(), sound);
                    media.start();

                    Snackbar.make(view, "playing...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    Snackbar.make(view, "You didn't choose sound", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
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

    public void randNum(){
        Random r = new Random();
        rand = r.nextInt(5);
        choosePicture(rand);
    }

    public void choosePicture(int i){
        switch (i){
            case 0 :
                pic = R.drawable.avatar_1;
                break;
            case 1:
                pic = R.drawable.avatar_2;
                break;
            case 2:
                pic = R.drawable.avatar_3;
                break;
            case 3:
                pic = R.drawable.avatar_4;
                break;
            case 4:
                pic = R.drawable.avatar_5;
                break;
        }
        obraz.setImageResource(pic);
    }

    public void changeContact(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeContact.class);
        startActivityForResult(intent,1);
    }

    public void changeSound(View view) {
        if(!tekst.getText().equals("")) {
            Intent intent = new Intent(getApplicationContext(), ChooseSound.class);
            startActivityForResult(intent, 2);
        }else
            Toast.makeText(this, "First choose contact", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                temp = name;
                name = data.getStringExtra("name");

                if(!temp.equals(name)){
                    tekst.setText(name);
                    randNum();
                }

            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "I'm back!", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                sound = data.getIntExtra("sound", 0);
                play=true;
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "I'm back!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
