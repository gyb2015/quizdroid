package edu.washington.gyb2015.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TopicSummaryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_summary);

        // Get the Intent that opened this activity
        Intent launchedMe = getIntent();
        String title1 = launchedMe.getStringExtra("title");  // get data that was passed from first activity
        String desc1 = launchedMe.getStringExtra("description");
        String queston = launchedMe.getStringExtra("number");


        // add the extra data into the text view of the 2nd activity (this layout)
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(title1);
        TextView desc = (TextView) findViewById(R.id.description);
        desc.setText(desc1);
        TextView quest = (TextView) findViewById(R.id.questiontotal);
        quest.setText(queston);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_summary, menu);
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

    public void beginquiz(View view) {
        Intent nextActivity = new Intent(TopicSummaryActivity.this, QuestionActivity.class); // cannot use just this cuz this refers to the listener, not the outer this
        Intent launchedMe = getIntent();
        // add data to be passed to next activity
        nextActivity.putExtra("Questnum", "Question 1");
        if(launchedMe.getStringExtra("title").equals("Math")) {
            nextActivity.putExtra("Question", "What is 1 + 1 =");
            nextActivity.putExtra("false1", "1");
            nextActivity.putExtra("true", "2");
            nextActivity.putExtra("false2", "3");
            nextActivity.putExtra("false3", "4");
        } else if (launchedMe.getStringExtra("title").equals("Physics")) {
            nextActivity.putExtra("Question", "What is Force equals?");
            nextActivity.putExtra("false1", "F = O(n)");
            nextActivity.putExtra("false2", "F = m/s");
            nextActivity.putExtra("false3", "F = N");
            nextActivity.putExtra("true", "F = ma");
        }   else if (launchedMe.getStringExtra("title").equals("Marvel Super Heroes!")) {
            nextActivity.putExtra("Question", "Who plays Ironman?");
            nextActivity.putExtra("false1", "Captain America");
            nextActivity.putExtra("true", "Tony Stark");
            nextActivity.putExtra("false2", "Thor");
            nextActivity.putExtra("false3", "Bruce Banner");
        }


        if (nextActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(nextActivity); // opens a new activity
        }
        // code still runs asynchronously

        finish(); // kill this instance self (this activity)
    }
}
