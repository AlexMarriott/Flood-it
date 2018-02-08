package com.example.alexm.floodit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WinLose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("won", true)){
            setContentView(R.layout.activity_win);
        }else if(!getIntent().getBooleanExtra("won",true)){
            setContentView(R.layout.activity_lost);
        }
    }

}
