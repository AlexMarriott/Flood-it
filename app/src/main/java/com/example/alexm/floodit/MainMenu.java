package com.example.alexm.floodit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * <h1>MainMenu</h1>
 * add a description of the class
 *
 * @author Alex Marriott s4816928
 * @version 1.0
 * @since 06/01/2018
 */
public class MainMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private int gridSize;
    private int numOfColours;
    private String playerName;
    private int gameMode;
    private boolean gameModeSelected = false;
    private Spinner spinner;

    Spinner colourSpinner;
    Spinner gridSizeSpinner;
    Spinner gameSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        playerName = getIntent().getStringExtra("PlayerName");

        //sample code used from https://developer.android.com/guide/topics/ui/controls/spinner.html

         colourSpinner =   findViewById(R.id.idNumOfColours);
         gridSizeSpinner =   findViewById(R.id.idSizeOfGrid);
         gameSpinner =  findViewById(R.id.idGameSpinner);

        ArrayAdapter<CharSequence> adapterGameMode = ArrayAdapter.createFromResource(this,
                R.array.game_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        gameSpinner.setAdapter(adapterGameMode);
        gameSpinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapterNumOfColours = ArrayAdapter.createFromResource(this,
                R.array.numOfColours, android.R.layout.simple_spinner_item);
        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colourSpinner.setAdapter(adapterNumOfColours);
        colourSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterSizeOfGrid = ArrayAdapter.createFromResource(this,
                R.array.sizeOfGrid, android.R.layout.simple_spinner_item);
        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gridSizeSpinner.setAdapter(adapterSizeOfGrid);
        gridSizeSpinner.setOnItemSelectedListener(this);



    }
    public void onItem(View view){
        //In this method, we get the settings options from the main menu and pass them through to the game activity
        if (view.getId() == R.id.startGameBtn){
            Intent intent = new Intent(getBaseContext(), Game.class);
            intent.putExtra("PlayerName", playerName);

            if (gameModeSelected){
                intent.putExtra("GameMode", gameMode);
                startActivity(intent);
            }else{
            intent.putExtra("Colours", numOfColours);
            intent.putExtra("GridSize", gridSize);
            startActivity(intent);
            }
        }

        else if (view.getId() == R.id.highSoresBtn){
            Intent intent = new Intent(this, LeaderBoard.class);
            startActivity(intent);
        }else if (view.getId() == R.id.rulesBtn){
            Intent intent = new Intent(this, Rules.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Could not find the buttons, something went wrong.",
                    Toast.LENGTH_SHORT).show();        }
    }


    private void setGameMode(String gameModeName) {
            gameModeSelected = true;
            switch(gameModeName){
            case "Easy":
                gameMode = 10;
                break;
            case "Medium":
                gameMode = 15;
                break;
            case "Hard":
                gameMode = 20;
                break;
            case "Game Mode":
                gameModeSelected = false;
        }
    }

    private void setColourAmount(String colourAmount){
        switch(colourAmount){
            case "4":
                numOfColours = 4;
                break;
            case "6":
                numOfColours = 6;
                break;
            case "8":
                numOfColours = 8;
                break;
        }
    }
    private void setGridSize(String gridSizeName){
        switch(gridSizeName){
            case "Small":
                gridSize = 10;
                break;
            case "Medium":
                gridSize = 20;
                break;
            case "Hard":
                gridSize = 30;
                break;
        }
    }


        @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            //TODO Bug current doesn't allow the spinners to be changed.
            case R.id.idNumOfColours:
                //spinner = findViewById(R.id.idNumOfColours);
                //spinner.setOnItemSelectedListener(this);
                setColourAmount(colourSpinner.getSelectedItem().toString());
                gameSpinner.setSelection(0);
                colourSpinner.setSelection(i);
                break;
            case R.id.idSizeOfGrid:
                //spinner = findViewById(R.id.idSizeOfGrid);
                //gridSizeSpinner.setOnItemSelectedListener(this);
                setGridSize(gridSizeSpinner.getSelectedItem().toString());
                gameSpinner.setSelection(0);
                gridSizeSpinner.setSelection(i);
                break;
            case R.id.idGameSpinner:
                //spinner = findViewById(R.id.idGameSpinner);
                //spinner.setOnItemSelectedListener(this);
                setGameMode(gameSpinner.getSelectedItem().toString());
                colourSpinner.setSelection(0);
                gridSizeSpinner.setSelection(0);
                gameSpinner.setSelection(i);
                break;
        }
    }
}