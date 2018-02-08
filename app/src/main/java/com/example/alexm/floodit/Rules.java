package com.example.alexm.floodit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Rules extends AppCompatActivity {
    /**
     * <h1>Rules</h1>
     * add a description of the class
     *
     * @author Alex Marriott s4816928
     * @version 1.0
     * @since 06/01/2018
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}