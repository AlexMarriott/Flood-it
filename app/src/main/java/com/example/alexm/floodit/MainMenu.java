package com.example.alexm.floodit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * <h1>MainMenu</h1>
 * add a description of the class
 *
 * @author Alex Marriott s4816928§
 * @version 1.0
 * @since 06/01/2018
 */
public class MainMenu extends AppCompatActivity {

    private int gridSize;
    private int numOfColours;
    private int gameMode;
    private boolean gameModeSelected = false;
    private RadioButton radioBtnEasy;
    private RadioButton radioBtnMedium;
    private RadioButton radioBtnHard;
    private CompoundButton customSwitch;
    private String prefsFile = "FloodGamePrefs";
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private RadioGroup grpRadioButtons;


    Spinner colourSpinner;
    Spinner gridSizeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        settings = getSharedPreferences(prefsFile, 0);
        editor = settings.edit();

        radioBtnEasy = findViewById(R.id.radioButtonEasyMode);
        radioBtnMedium = findViewById(R.id.radioButtonMediumMode);
        radioBtnHard = findViewById(R.id.radioButtonHardMode);
        //sample code used from https://developer.android.com/guide/topics/ui/controls/spinner.html
        colourSpinner = findViewById(R.id.idNumOfColours);
        gridSizeSpinner = findViewById(R.id.idSizeOfGrid);

        ArrayAdapter<CharSequence> adapterNumOfColours = ArrayAdapter.createFromResource(this,
                R.array.numOfColours, android.R.layout.simple_spinner_item);
        adapterNumOfColours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colourSpinner.setAdapter(adapterNumOfColours);
        colourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.idNumOfColours:
                        setColourAmount(colourSpinner.getSelectedItem().toString());
                        colourSpinner.setSelection(position);
                        if (customSwitch.isChecked()) {
                            customSwitch.performClick();
                        }
                        //onCheckedChanged(customSwitch, false);
                        editor.remove("GameMode");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterSizeOfGrid = ArrayAdapter.createFromResource(this,
                R.array.sizeOfGrid, android.R.layout.simple_spinner_item);
        adapterSizeOfGrid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gridSizeSpinner.setAdapter(adapterSizeOfGrid);
        gridSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.idSizeOfGrid:
                        setGridSize(gridSizeSpinner.getSelectedItem().toString());
                        gridSizeSpinner.setSelection(position);
                        if (customSwitch.isChecked()) {
                            customSwitch.performClick();
                        }
                        //onCheckedChanged(customSwitch, false);
                        editor.remove("GameMode");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        customSwitch = findViewById(R.id.CustomSwitch);
        //https://stackoverflow.com/questions/10057328/cant-set-oncheckedchangelistener-to-a-checkbox
        customSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioBtnEasy.setVisibility(View.VISIBLE);
                    radioBtnMedium.setVisibility(View.VISIBLE);
                    radioBtnHard.setVisibility(View.VISIBLE);

                } else if (!isChecked) {
                    radioBtnEasy.setVisibility(View.GONE);
                    radioBtnMedium.setVisibility(View.GONE);
                    radioBtnHard.setVisibility(View.GONE);
                }
            }

        });

        grpRadioButtons = findViewById(R.id.RadioButtonGroup);
        grpRadioButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //https://developer.android.com/guide/topics/ui/controls/radiobutton.html
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was clicked
                switch (checkedId) {
                    case R.id.radioButtonEasyMode:
                        setGameMode("Easy");
                        break;
                    case R.id.radioButtonMediumMode:
                        setGameMode("Medium");
                        break;
                    case R.id.radioButtonHardMode:
                        setGameMode("Hard");
                        break;
                }
                editor.remove("GridSize");
                editor.remove("Colours");
            }
        });

        //maybe put here set spinner selection to 0


    }


    public void onItem(View view) {
        //In this method, we get the settings options from the main menu and pass them through to the game activity
        if (view.getId() == R.id.startGameBtn) {
            Intent intent = new Intent(getBaseContext(), Game.class);
            editor.commit();
            startActivity(intent);
        } else if (view.getId() == R.id.highSoresBtn) {
            Intent intent = new Intent(this, LeaderBoard.class);
            startActivity(intent);
        } else if (view.getId() == R.id.rulesBtn) {
            Intent intent = new Intent(this, Rules.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Could not find the buttons, something went wrong.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void setGameMode(String gameModeName) {
        gameModeSelected = true;
        switch (gameModeName) {
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
        editor.putInt("GameMode", gameMode);
    }

    private void setColourAmount(String colourAmount) {
        switch (colourAmount) {
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
        editor.putInt("Colours", numOfColours);
    }

    private void setGridSize(String gridSizeName) {
        switch (gridSizeName) {
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
        editor.putInt("GridSize", gridSize);

    }
}


