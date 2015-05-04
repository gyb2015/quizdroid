package edu.washington.gyb2015.quizdroid;

import java.io.Serializable;
import java.util.HashMap;


public class Data implements Serializable{
    private int counter;
    private HashMap<String,String[]> questions;

    public Data() {
        questions = new HashMap<String,String[]>();
        counter = 0;
    }

    public HashMap<String,String[]> add(String key, String[] value) {
        counter++;
        questions.put(key, value);
        return questions;
    }

    public int count() {
        return counter;
    }

    public int setCount(int a) {
        return counter = a;
    }
}