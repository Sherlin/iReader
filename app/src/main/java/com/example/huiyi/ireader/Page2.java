package com.example.huiyi.ireader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by huiyi on 8/4/15.
 */
public class Page2  extends ActionBarActivity {

    private MusicHandler musicHandler1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

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
        if (id == R.id.action_page) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void backPage(View view)
    {
        Intent intent = new Intent(this, Page1.class);
        startActivity(intent);
    }
    public void nextPage(View view)
    {
        musicHandler1.fadeOut(5000);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
