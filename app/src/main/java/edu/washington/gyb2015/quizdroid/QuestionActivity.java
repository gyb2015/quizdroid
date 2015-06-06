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
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionActivity extends ActionBarActivity {
    public int number = 1;
    public int checking = 0;
    public int total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent launchedMe = getIntent();
        // get data that was passed from first activity
        String questnum = launchedMe.getStringExtra("Questnum");
        String question = launchedMe.getStringExtra("Question");
        String a1 = launchedMe.getStringExtra("true");
        String a2 = launchedMe.getStringExtra("false1");
        String a3 = launchedMe.getStringExtra("false2");
        String a4 = launchedMe.getStringExtra("false3");
        int num = launchedMe.getIntExtra("tscore", 0);
        total = num;

        // add the extra data into the text view of the 2nd activity (this layout)
        TextView questionnum = (TextView) findViewById(R.id.questnum);
        questionnum.setText(questnum);
        TextView quest = (TextView) findViewById(R.id.question);
        quest.setText(question);
        RadioButton ans1 = (RadioButton) findViewById(R.id.a1);
        ans1.setText(a1);
        RadioButton ans2 = (RadioButton) findViewById(R.id.a2);
        ans2.setText(a2);
        RadioButton ans3  = (RadioButton) findViewById(R.id.a3);
        ans3.setText(a3);
        RadioButton ans4 = (RadioButton) findViewById(R.id.a4);
        ans4.setText(a4);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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

    public void chosen(View view) {
        // Is the button now checked?
        Intent nextActivity = new Intent(QuestionActivity.this, SummaryActivity.class);
        Intent launchedMe = getIntent();
        if(launchedMe.getStringExtra("Questnum").equals("Question 1")) {
            nextActivity.putExtra("number", "1");
        } else {
            nextActivity.putExtra("number", "2");
        }
        number++;
        nextActivity.putExtra("Questvar", launchedMe.getStringExtra("Question"));
        if(checking == 1) {
            nextActivity.putExtra("Total",  total++);
            nextActivity.putExtra("ans", "correct");
            nextActivity.putExtra("chosen", launchedMe.getStringExtra("true"));
            nextActivity.putExtra("score", 1);
        } else if(checking == 2) {
            nextActivity.putExtra("ans", "wrong");
            nextActivity.putExtra("chosen", launchedMe.getStringExtra("false1"));
            nextActivity.putExtra("score", 0);
        } else if(checking == 3) {
            nextActivity.putExtra("ans", "wrong");
            nextActivity.putExtra("chosen", launchedMe.getStringExtra("false2"));
            nextActivity.putExtra("score", 0);
        } else if(checking == 4) {
            nextActivity.putExtra("ans", "wrong");
            nextActivity.putExtra("chosen", launchedMe.getStringExtra("false3"));
            nextActivity.putExtra("score", 0);
        }

        if (nextActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(nextActivity); // opens a new activity
        }
        // code still runs asynchronously

    }

    public void clicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Button b = (Button) findViewById(R.id.submit);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.a1:
                if (checked)
                    checking = 1;
                b.setVisibility(view.VISIBLE);
                break;
            case R.id.a2:
                if (checked)
                    checking = 2;
                b.setVisibility(view.VISIBLE);
                break;
            case R.id.a3:
                if (checked)
                    checking = 3;
                b.setVisibility(view.VISIBLE);
                break;
            case R.id.a4:
                if (checked)
                    checking = 4;
                b.setVisibility(view.VISIBLE);
                break;
        }

    }
}
