package edu.washington.gyb2015.quizdroid;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class QuizApp extends Application implements TopicRepository {
    public static QuizApp instance; // singleton
    public List<Topic> list;

    public int currentTopic;
    public int currentQuestion;

    public QuizApp() {
        if(instance== null) {
            instance = this;
        }
    }

    public static void setInstance(){
        QuizApp.instance = instance;
    }

    public static QuizApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Start", "Quiz Loaded");
        setCurrentQuestion(0);
        list = new ArrayList<Topic>();

        Topic a = new Topic();
        a.setTitle("Math");
        a.setShortd("Difficulty: Medium");
        a.setLongd("Medium level Math Question(s).");

        String json = null;

            //Questions
            ArrayList<Question> mathq = getMath();
            a.setQuestion(mathq);
            list.add(a);

            Topic b = new Topic();
            b.setTitle("Physics");
            b.setShortd("Difficulty: Hard");
            b.setLongd("Contains some of the hardest Physics Question(s) known to mankind.");

            //Questions
            ArrayList<Question> physicq = getPhysics();
            b.setQuestion(physicq);
            list.add(b);

            Topic c = new Topic();
            c.setTitle("Marvel Super Heroes!!");
            c.setShortd("Difficulty: Easy");
            c.setLongd("Easy Marvel related Question(s)");
            //Questions
            ArrayList<Question> marvelq = getMarvel();
            c.setQuestion(marvelq);
            list.add(c);

    }

    // reads InputStream of JSON file and returns the file in JSON String format
    public String readJSONFile(InputStream inputStream) throws IOException {

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }

    public ArrayList<Question> getMath() {
        ArrayList<Question> mathq = new ArrayList<Question>();
        Question math1 = new Question();
        math1.setQuestion("What is 1 + 1?");
        math1.setAnswers("4", "3", "2", "1");
        math1.setCorr(2);
        mathq.add(math1);
        Question math2 = new Question();
        math2.setQuestion("What is 2 x 2?");
        math2.setAnswers("2", "4", "6", "8");
        math2.setCorr(1);
        mathq.add(math2);
        return mathq;
    }

    public ArrayList<Question> getPhysics() {
        ArrayList<Question> physicq = new ArrayList<Question>();
        Question physics1 = new Question();
        physics1.setQuestion("What is Force equals?");
        physics1.setAnswers("F = ma", "F = D", "F = ms", "F = O(n)");
        physics1.setCorr(0);
        physicq.add(physics1);
        Question physics2 = new Question();
        physics2.setQuestion("What is Velocity equals?");
        physics2.setAnswers("V = Kelvin", "V = ms^2", "V = P", "V = dx/dt");
        physics2.setCorr(3);
        physicq.add(physics2);
        return physicq;
    }

    public ArrayList<Question> getMarvel() {
        ArrayList<Question> marvelq = new ArrayList<Question>();
        Question marvel1 = new Question();
        marvel1.setQuestion("What does the Hulk like to do?");
        marvel1.setAnswers("Knit", "SMASH!!", "Cry", "Dance");
        marvel1.setCorr(1);
        marvelq.add(marvel1);
        Question marvel2 = new Question();
        marvel2.setQuestion("Who is the girl in the Avengers?");
        marvel2.setAnswers("Black Widow", "White Widow", "Rainbow Widow", "Yellow Widow");
        marvel2.setCorr(0);
        marvelq.add(marvel2);
        return marvelq;
    }

    public void setCurrentQuestion(int question) {
        this.currentQuestion = question;
    }
    public int getCurrentQuestion() {
        return this.currentQuestion;
    }

    public void setCurrentTopic(int a) {
        this.currentTopic = a;
    }

    @Override
    public String getTitle() {
        return list.get(currentTopic).getTitle();
    }

    @Override
    public void setTitle(String a) {
        list.get(currentTopic).setTitle(a);
    }

    @Override
    public String getShortd() {
        return list.get(currentTopic).getShortd();
    }

    @Override
    public void setShortd(String a) {
        list.get(currentTopic).setShortd(a);
    }

    @Override
    public String getLongd() {
        return list.get(currentTopic).getLongd();
    }

    @Override
    public void setLongd(String a) {
        list.get(currentTopic).setLongd(a);
    }

    @Override
    public List<Question> getQuestion() {
        return list.get(currentTopic).getQuestion();
    }

    @Override
    public void setQuestion(List<Question> a) {

    }

    public String[] getTopic() {
        String t1 = list.get(0).getTitle() + ":  " + list.get(0).getShortd();
        String t2 = list.get(1).getTitle() + ":  " + list.get(1).getShortd();
        String t3 = list.get(2).getTitle() + ":  " + list.get(2).getShortd();
        String[] topics = new String[] {t1, t2, t3};;
        return topics;
    }

}
