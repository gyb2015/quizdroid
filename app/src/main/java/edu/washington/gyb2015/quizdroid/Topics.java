package edu.washington.gyb2015.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Topics extends ActionBarActivity {
    public String[] topics;
    public Qmaster q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        q = new Qmaster();
        ListView listview = (ListView) findViewById(R.id.listView);

        topics = new String[] {"Math" , "Physics", "Marvel Super Heroes!"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, topics);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListView", "Position " + position + "Pressed");
                //set to next activity
                Intent nextActivity = new Intent(Topics.this, Overview.class);
                String desc;
                String selected = topics[position];
                if(position == 0) {
                    q.add(1,"What is 1 + 1?","1","2","3","4","2");
                    q.add(2,"What is 2 x 2?","2","4","6","8", "4");
                    desc = "Lets do some Math!";
                } else if (position == 1) {
                    q.add(1,"What is Force equals?","F = ma","F = D","F = ms","F = O(n)", "F = ma");
                    q.add(2,"What is velocity equals?","V = dx/dt"," V = ms^2","V = P","V = Kelvin", "V = dx/dt");
                    desc = "Lets do some Physics!";
                } else {
                    q.add(1,"What does the Hulk like to do?","Knit","SMASH","Cry","Dance", "SMASH");
                    q.add(2,"Who is the girl in the Avengers?","Black Widow","white Widow","Yellow Widow","Rainbow Widow","Black Widow");
                    desc = "Avengers Assemble!";
                }

                nextActivity.putExtra("correct", 0);
                nextActivity.putExtra("number", 1);
                nextActivity.putExtra("title", selected);
                nextActivity.putExtra("questions", q);
                nextActivity.putExtra("total", q.getSize());
                nextActivity.putExtra("desc", desc);

                //send intent
                if(nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topics, menu);
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
}
