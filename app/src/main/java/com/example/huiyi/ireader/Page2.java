package com.example.huiyi.ireader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by huiyi on 8/4/15.
 */
public class Page2  extends ActionBarActivity {

    public static MusicHandler musicHandler1;
    public static int seek = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        musicHandler1 = new MusicHandler(this);
        musicHandler1.load(R.raw.prayer, false);
        musicHandler1.fadeIn(5000);

        final Button muteButton = (Button) findViewById(R.id.Mutebutton);
        muteButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                if(musicHandler1.isPlaying())
                {
                    mute();
                }
                else
                {
                    musicHandler1.seekTo(seek);
                    musicHandler1.fadeIn(1000);
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
        if (id == R.id.about_page) {
            openAbout();
            return true;
        }else if(id == R.id.setting_page){

            Toast.makeText(getApplicationContext(), "Under construction", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void backPage(View view)
    {
        musicHandler1.fadeOut(5000);
        Intent intent = new Intent(this, Page1.class);
        startActivity(intent);
    }
    public void nextPage(View view)
    {
        musicHandler1.fadeOut(5000);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAbout() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void mute() {
        seek = musicHandler1.getCurrentPosition();
        musicHandler1.pause(1000);
    }

    public void onPause()
    {
        super.onPause();
        seek = musicHandler1.getCurrentPosition();
        musicHandler1.pause(1000);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        musicHandler1.seekTo(seek);
        musicHandler1.fadeIn(1000);
    }

}
