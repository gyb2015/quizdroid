package edu.washington.gyb2015.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Preferences extends ActionBarActivity {
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        /* Retrieve a PendingIntent that will perform a broadcast */
        final Intent alarmIntent = new Intent(Preferences.this, MyReceiver.class);


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText link = (EditText) findViewById(R.id.url);
                String url = "http://tednewardsandbox.site44.com/questions.json";
                //link.getText().toString();

                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                //For 3G check
                boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                        .isConnectedOrConnecting();

                //For WiFi Check
                boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .isConnectedOrConnecting();

                System.out.println(is3g + " net " + isWifi);

                if (!is3g && !isWifi)
                {
                    Toast.makeText(getApplicationContext(),"Please make sure your Network Connection is ON ",Toast.LENGTH_LONG).show();
                } else if(isAirplaneModeOn(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "Ariplane mode is on", Toast.LENGTH_SHORT).show();
                    Intent AirIntent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                    AirIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(AirIntent);

                } else {
                    alarmIntent.putExtra("url", url);


                    pendingIntent = PendingIntent.getBroadcast(Preferences.this, 0, alarmIntent, 0);
                    start();
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
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

    @Override
    protected void onDestroy() {

        super.onDestroy();
        cancel();
    }

    public void start() {
        EditText time = (EditText) findViewById(R.id.time);
        String timer = time.getText().toString();
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        int minute = Integer.parseInt(timer) * 60000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), minute, pendingIntent);
        Toast.makeText(this, "Sit tight, will start download at next availability", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }
    private static boolean isAirplaneModeOn(Context context) {

        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) != 0;

    }
}
