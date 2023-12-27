package com.travel.rotalocal.config;

import java.util.Dictionary;
import java.util.Hashtable;

public class Tokens {
    
    private static Tokens instance;

    public Dictionary<Long, String> tokensList;

    public Tokens(){
        Dictionary<Long, String> tokensList = new Hashtable<Long, String>();
        this.tokensList = tokensList;
    }

    public static Tokens getInstance() {
        if (instance == null) {
            instance = new Tokens();
        }
        return instance;
    }

}
