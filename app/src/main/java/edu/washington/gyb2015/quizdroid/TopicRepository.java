package edu.washington.gyb2015.quizdroid;

import java.util.List;


public interface TopicRepository {

    public String getTitle();

    public void setTitle(String a);

    public String getShortd();

    public void setShortd(String a);

    public String getLongd();

    public void setLongd(String a);

    public List<Question> getQuestion();

    public void setQuestion(List<Question> a);

}
