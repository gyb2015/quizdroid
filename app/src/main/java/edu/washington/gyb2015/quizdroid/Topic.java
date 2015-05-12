package edu.washington.gyb2015.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Topic implements Serializable{
    private String title;
    private String shortd;
    private String longd;
    private List<Question> question;

    public Topic() {


    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String a) {
        this.title = a;
    }

    public String getShortd() {
        return this.shortd;
    }

    public void setShortd(String a) {
        this.shortd = a;
    }

    public String getLongd() {
        return this.longd;
    }

    public void setLongd(String a) {
        this.longd = a;
    }

    public List<Question> getQuestion() {
        return this.question;
    }

    public void setQuestion(ArrayList<Question> b ) {
        this.question = b;
    }
}