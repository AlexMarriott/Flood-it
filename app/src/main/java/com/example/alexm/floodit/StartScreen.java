package com.example.alexm.floodit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class StartScreen extends AppCompatActivity {
    /**
     * <h1>StartScreen</h1>
     * add a description of the class
     *
     * @author Alex Marriott s4816928
     * @version 1.0
     * @since 06/01/2018
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

    }
    public void onItem(View view) {
        if (view.getId() == R.id.startGameBtn) {
            Intent intent = new Intent(getBaseContext(), MainMenu.class);
            EditText editext = findViewById(R.id.editText);
            String playername = editext.getText().toString();
            String prefsFile = "FloodGamePrefs";
            SharedPreferences settings = getSharedPreferences(prefsFile, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("PlayerName", playername);
            editor.commit();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Could not find the buttons, something went wrong.",
                    Toast.LENGTH_SHORT).show();
        }
        onStop();

    }

}