package com.example.huiyi.ireader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
    //ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] actressArray = {"The Lonely Serpent", "Beauty & The Beast", "Cinderella",
                "The Lion King" };
        ListView lv = (ListView)findViewById(R.id.actresslist);

        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.list_item,actressArray));
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView< ?> parent, View view, int position, long id) {

                String story = ((TextView) view.findViewById(R.id.list_item)).getText().toString();
                if(story == "The Lonely Serpent") {
                    Intent intent = new Intent(MainActivity.this, Page1.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Story In Progress", Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_page) {
            openAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void openAbout() {
        Intent intent = new Intent(this, Page1.class);
        startActivity(intent);
    }





}