package com.example.huiyi.ireader;

/**
 * Created by huiyi on 29/3/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Page1 extends ActionBarActivity {


    public static MusicHandler musicHandler;
    public static int seek = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        musicHandler = new MusicHandler(this);

        //musicHandler.load(R.raw.fatigue, false);
        musicHandler.load(R.raw.love_song, false);

        musicHandler.fadeIn(5000);

        final Button muteButton = (Button) findViewById(R.id.Mutebutton);
        muteButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                mute();
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
        if (id == R.id.about_page) {
            openAbout();
            return true;
        }else if(id == R.id.setting_page){

            Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void nextPage(View view)
    {


        musicHandler.fadeOut(5000);
        //Bundle b = new Bundle();
        //b.putParcelable("MUSICHANDLER", musicHandler2);
        Intent intent = new Intent();
        //intent.putExtras(b);
        intent.setClass(this,Page2.class);
        startActivity(intent);
    }
    public void fadeOut(View view)
    {
        musicHandler.load(R.raw.prayer, false);
        musicHandler.fadeOut(5000);
    }


    public void home(View view)
    {

        musicHandler.fadeOut(5000);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAbout() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        seek = musicHandler.getCurrentPosition();
        musicHandler.pause(1000);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        musicHandler.seekTo(seek);
        musicHandler.fadeIn(1000);
    }

    /** WORK IN PROGRESS **/
    public void mute() {
        seek = musicHandler.getCurrentPosition();
        musicHandler.pause(1000);

    }
}
