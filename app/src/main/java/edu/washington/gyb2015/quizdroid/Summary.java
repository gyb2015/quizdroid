package edu.washington.gyb2015.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Summary extends ActionBarActivity {
    public int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent launchedMe = getIntent();
        String qnums = launchedMe.getStringExtra("number");  // get data that was passed from first activity
        String answer = launchedMe.getStringExtra("ans");
        String chosen = launchedMe.getStringExtra("chosen");

        if (answer.equals("correct")) {
            total = launchedMe.getIntExtra("Total", 1);
            total++;
        } else {
            total = launchedMe.getIntExtra("Total", 0);
        }
        // add the extra data into the text view of the 2nd activity (this layout)
        TextView ans = (TextView) findViewById(R.id.answer);
        ans.setText(chosen);
        TextView corr = (TextView) findViewById(R.id.correct);
        corr.setText("You have " + total + " out of " + qnums + " correct");
        if (qnums.equals("1")) {
            Button b = (Button) findViewById(R.id.next);
            b.setText("Next");
        } else {
            Button b = (Button) findViewById(R.id.next);
            b.setText("Finish");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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

    public void nextq(View view) {
        Intent launchedMe = getIntent();
        String qnums = launchedMe.getStringExtra("number");
        if (qnums.equals("1")) {
            Intent nextActivity = new Intent(Summary.this, Question.class);
            nextActivity.putExtra("Questnum", "Question 2");
            nextActivity.putExtra("tscore", total++);
            if (launchedMe.getStringExtra("Questvar").equals("What is 1 + 1 =")) {
                nextActivity.putExtra("Question", "What is 3 x 3 =");
                nextActivity.putExtra("true", "9");
                nextActivity.putExtra("false1", "6");
                nextActivity.putExtra("false2", "3");
                nextActivity.putExtra("false3", "12");
            } else if (launchedMe.getStringExtra("Questvar").equals("What is Force equals?")) {
                nextActivity.putExtra("Question", "What is Velocity equals?");
                nextActivity.putExtra("false1", "V = m/H");
                nextActivity.putExtra("false2", "V = m/f");
                nextActivity.putExtra("false3", "V = N");
                nextActivity.putExtra("true", "V = dx/dt");
            } else if (launchedMe.getStringExtra("Questvar").equals("Who plays Ironman?")) {
                nextActivity.putExtra("Question", "Whos is the girl in the avengers?");
                nextActivity.putExtra("false1", "Rainbow Widow");
                nextActivity.putExtra("true", "Black Widow");
                nextActivity.putExtra("false2", "White Widow");
                nextActivity.putExtra("false3", "Yellow Widow");
            }
            if (nextActivity.resolveActivity(getPackageManager()) != null) {
                startActivity(nextActivity); // opens a new activity
            }
            // code still runs asynchronously

            finish(); // kill this instance self (this activity)
        } else {
            Intent nextActivity = new Intent(Summary.this, MainActivity.class);
            if (nextActivity.resolveActivity(getPackageManager()) != null) {
                startActivity(nextActivity); // opens a new activity
            }
            // code still runs asynchronously

            finish(); // kill this instance self (this activity)
        }
    }
}
