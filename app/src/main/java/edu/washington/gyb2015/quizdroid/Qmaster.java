package edu.washington.gyb2015.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Qmaster implements Serializable {
    private HashMap<Integer, String[]> questions;

    public Qmaster() {
        this.questions = new HashMap<Integer, String[]>();
    }

    public HashMap<Integer, String[]> add(int i, String a, String b, String c, String d, String e, String answer) {
        String[] s = new String[6];
        s[0] = a;
        s[1] = b;
        s[2] = c;
        s[3] = d;
        s[4] = e;
        s[5] = answer;
        questions.put(i, s);
        return questions;
    }

    public int getSize() {
        return questions.size();
    }

    public String[] getAnswers(int i) {
        return questions.get(i);
    }

    public String correct(int i) {
        String[] a = questions.get(i);
        return a[questions.size() - 1];
    }

    public void delete( int i) {
        questions.remove(i);
    }


}