package com.travel.rotalocal.model.entity;

import java.util.Dictionary;
import java.util.Hashtable;

public class Feedbacks {
    
    private static Feedbacks instance;

    public Dictionary<Long, String> feedbackList;

    public Feedbacks(){
        Dictionary<Long, String> feedbackList = new Hashtable<Long, String>();
        this.feedbackList = feedbackList;
    }

    public static Feedbacks getInstance() {
        if (instance == null) {
            instance = new Feedbacks();
        }
        return instance;
    }

}
