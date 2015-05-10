package edu.washington.gyb2015.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Overview extends ActionBarActivity {
    public static Qmaster q;
    public static int Qcount;
    public static int numcorrect;
    public static int totalq;
    public static String[] quest;
    public static boolean correct;
    public static String answ;
    public static Intent launchedMe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        launchedMe = getIntent();
        String title =  launchedMe.getStringExtra("title");
        q = (Qmaster) launchedMe.getSerializableExtra("questions");
        totalq = launchedMe.getIntExtra("total", 10);
        String desc = launchedMe.getStringExtra("desc");
        numcorrect = launchedMe.getIntExtra("correct", 0);

        TextView title1 = (TextView) findViewById(R.id.textView);
        title1.setText(title);
        TextView description = (TextView) findViewById(R.id.textView2);
        description.setText(desc);
        TextView numberq = (TextView) findViewById(R.id.textView8);
        numberq.setText("Questions in Topic: " + totalq);

        //button to next frag
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v.findViewById(R.id.button);
                b.setVisibility(v.INVISIBLE);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.topicid, new Question());
                ft.commit();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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

    public class Question extends Fragment {
        public View view;

        public Question() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_question, container, false);

            launchedMe = getIntent();
            Qcount = launchedMe.getIntExtra("number", 1);
            quest = q.getAnswers(Qcount);

            TextView question = (TextView) view.findViewById(R.id.textView6);
            question.setText(quest[0]);
            TextView num = (TextView) view.findViewById(R.id.textView7);
            num.setText("Quesion " + Qcount);

            //Radio group set text
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
            int rad = radioGroup.getChildCount();
            for (int i = 1; i <= rad; i++) {
                View o = radioGroup.getChildAt(i - 1);
                if (o instanceof RadioButton) {

                    RadioButton radioBtn =  (RadioButton)o;
                    radioBtn.setText(quest[i]);
                }
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    Button b = (Button) view.findViewById(R.id.button3);
                    b.setVisibility(view.VISIBLE);
                    RadioButton radio = (RadioButton) view.findViewById(checkedId);
                    answ = radio.getText().toString();
                    if(answ.equals(quest[quest.length - 1]) ) {
                        correct = true;
                        numcorrect++;
                    } else {
                        correct = false;
                    }

                }
            });

            Button btn = (Button) view.findViewById(R.id.button3);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v.findViewById(R.id.button3);
                    b.setVisibility(v.INVISIBLE);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.question_id, new Summary());
                    ft.commit();
                }

            });

            return view;
        }

        public class Summary extends Fragment {
            public Button b;
            public View view;

            public Summary() {

            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                view = inflater.inflate(R.layout.fragment_summary, container, false);

                TextView ans = (TextView) view.findViewById(R.id.textView4);
                ans.setText("You have chosen: " + answ);
                TextView numcorr = (TextView) view.findViewById(R.id.textView5);

                numcorr.setText("You have " + numcorrect + " out of " + Qcount + " correct");
                TextView rightnum = (TextView) view.findViewById(R.id.textView9);
                rightnum.setText("Correct Answer: " + quest[quest.length - 1]);
                b = (Button) view.findViewById(R.id.button2);
                if(Qcount == totalq) {
                    b.setText("Finish");
                }
                b.setOnClickListener(new View.OnClickListener() {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();


                    @Override
                    public void onClick(View v) {
                        if(b.getText().toString().equals("Next")) {
                            Button b = (Button) v.findViewById(R.id.button2);
                            b.setVisibility(v.INVISIBLE);
                            launchedMe.putExtra("number", 2);
                            ft.replace(R.id.summary_id, new Question());
                            ft.commit();
                        } else {
                            launchedMe.putExtra("correct", 0);
                            Intent nextActivity = new Intent(getActivity(), Topics.class);
                            startActivity(nextActivity); // opens a new activity
                        }
                    }
                });


                return view;


            }

        }
    }
}