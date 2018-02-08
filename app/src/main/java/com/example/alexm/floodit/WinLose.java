package com.example.alexm.floodit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WinLose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("won", true)){
            setContentView(R.layout.content_win);
        }else if(!getIntent().getBooleanExtra("won",true)){
            setContentView(R.layout.content_lose);
        }
    }

    public void onItem(View view) {
        if (view.getId() == R.id.btnReturnToMenu) {
            Intent intent = new Intent(getBaseContext(), MainMenu.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Could not find the buttons, something went wrong.",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
