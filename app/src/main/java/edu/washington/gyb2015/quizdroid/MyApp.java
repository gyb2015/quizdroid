package edu.washington.gyb2015.quizdroid;

import android.app.Application;
import android.app.DownloadManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by iguest on 5/18/15.
 */

public class MyApp extends Application {
    private DownloadManager dm;
    private long enqueue;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("MyApp", "Quiz app is loaded and running");

        File myFile = new File(getFilesDir().getAbsolutePath(), "/data.json");  // this string is where you can specify what file you are looking for inside your data/ directory
        String json = null;

        // Let's get the JSON in the files directory! (aka data/data.json which is a hidden folder that you can't access or see unless its from the app itself)
        // check if data.json file exists in files directory
        if (myFile.exists()) {
            Log.i("MyApp", "data.json DOES exist");

            try {
                FileInputStream fis = openFileInput("data.json");      // sweet we found it. openFileInput() takes a string path from your data directory. no need to put 'data/' in your path parameter
                json = readJSONFile(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            // Can't find data.json file in files directory. Fetch data.json in assets
            Log.i("MyApp", "data.json DOESN'T exist. Fetch from assets");

            try {
                InputStream inputStream = getAssets().open("data.json");
                json = readJSONFile(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // DO SOMETHING WITH JSON HERE
        try {
            JSONArray jsonTopics = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Start the Download service (which is a class that you create. This class extends IntentService)
        DownloadService.startOrStopAlarm(this, true);
    }


    public String readJSONFile(InputStream inputStream) throws IOException {
        // grab code from previous assignment. I'm pretty sure i posted this method in another repository

        return "go 49ers!";
    }

    public String readJSONFile(FileInputStream fileInputStream) throws IOException {
        // grab code from previous assignment. I'm pretty sure i posted this method in another repository
        return "Ted Neward is cool";
    }

    public void writeToFile(String data) {
        try {
            Log.i("MyApp", "writing downloaded to file");

            File file = new File(getFilesDir().getAbsolutePath(), "data.json");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}
