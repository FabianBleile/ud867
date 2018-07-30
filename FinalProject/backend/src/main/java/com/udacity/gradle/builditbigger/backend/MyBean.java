package com.udacity.gradle.builditbigger.backend;

import com.fabianbleile.javajokes.Joker;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData() {
        Joker joker = new Joker();
        myData = joker.getJoke();
    }
}