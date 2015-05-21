package edu.washington.gyb2015.quizdroid;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("url");

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Quiz");
        request.setDescription("File is being downloaded");

        //request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        String filename = "questions.json";
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);

        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        try {
            BufferedReader in = new BufferedReader(new FileReader("questions.json"));

            PrintWriter out = new PrintWriter(new FileOutputStream("questions.json"));

            String str;
            while ((str = in.readLine()) != null) {
                Log.v("hihi", "Writing downloaded " + str);
                out.println(str);
            }
            Intent nextIntent = new Intent(context, Topics.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, nextIntent, 0);

            // Pop a notification
            Notification note = new Notification.Builder(context).
                    setTicker("Quiz content downloaded!").
                    setSmallIcon(android.R.drawable.ic_menu_report_image).
                    setContentTitle("QuizDroid").
                    setContentText("New questions downloaded").
                    setContentIntent(pi).
                    setAutoCancel(true).getNotification();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, note);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
