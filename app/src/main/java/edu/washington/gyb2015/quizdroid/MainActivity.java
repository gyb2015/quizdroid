package edu.washington.gyb2015.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void mathclick(View view) {
        Intent nextActivity = new Intent(MainActivity.this, TopicSummary.class); // cannot use just this cuz this refers to the listener, not the outer this

        // add data to be passed to next activity
        nextActivity.putExtra("title",  "Math");
        nextActivity.putExtra("description", "Time to do some Math!");
        nextActivity.putExtra("number", "2 Questions in Topic");


        if (nextActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(nextActivity); // opens a new activity
        }

        // code still runs asynchronously

    }

    public void physicsclick(View view) {
        Intent nextActivity = new Intent(MainActivity.this, TopicSummary.class); // cannot use just this cuz this refers to the listener, not the outer this

        // add data to be passed to next activity
        nextActivity.putExtra("title",  "Physics");
        nextActivity.putExtra("description", "Time to do some Physics!");
        nextActivity.putExtra("number", "2 Questions in Topic");


        if (nextActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(nextActivity); // opens a new activity
        }
        // code still runs asynchronously
    }

    public void marvelclick(View view) {
        Intent nextActivity = new Intent(MainActivity.this, TopicSummary.class); // cannot use just this cuz this refers to the listener, not the outer this

        // add data to be passed to next activity
        nextActivity.putExtra("title",  "Marvel Super Heroes!");
        nextActivity.putExtra("description", "Avengers Assemble!");
        nextActivity.putExtra("number", "2 Questions in Topic");


        if (nextActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(nextActivity); // opens a new activity
        }
        // code still runs asynchronously
    }
}
