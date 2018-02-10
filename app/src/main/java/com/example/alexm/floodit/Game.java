package com.example.alexm.floodit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * <h1> Game</h1>
 * add a description of the class
 *
 * @author Alex Marriott s4816928
 * @version 1.0
 * @since 06/01/2018
 */
public class Game extends AppCompatActivity implements SurfaceHolder.Callback {
    //creates an two, two dimensional arrays which are used to create the grid and check the visited tiles.
    private GameGridView gridView;
    private static int[][] grid; //Default value of 15 (medium)
    private static int[] options = {};
    private GameLogic gameSettings;
    private int roundMode;
    private TextView textView;
    private TextView playerTextView;
    private boolean[][] visited;

    private String playerName;
    private int gridSize;
    private int gameMode;
    private int colours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_game);



        String prefsFile = "FloodGamePrefs";
        SharedPreferences settings = getSharedPreferences(prefsFile, 0);

        playerName = settings.getString("PlayerName", "Anno");
        gridSize = settings.getInt("GridSize", 0);
        gameMode = settings.getInt("GameMode",0 );
        colours = settings.getInt("Colours",0);

        if (gameMode == 0 && colours == 0 && gridSize ==0) {
            grid = new int[20][20];
            visited = new boolean[20][20];
            setContentView(R.layout.sm_activity_game);
            textView = findViewById(R.id.smRoundsRemainingTextView);
            roundMode = gameSettings.random(1,3);
        }
        //if either
        else if (gridSize == 0 || colours == 0) {
            grid = new int[gameMode][gameMode];
            visited = new boolean[gameMode][gameMode];
            switch(gameMode){
                case 10:
                    setContentView(R.layout.sm_activity_game);
                    textView = findViewById(R.id.smRoundsRemainingTextView);
                    roundMode = 1;
                    break;
                case 15:
                    setContentView(R.layout.md_activity_game);
                    textView = findViewById(R.id.mdRoundsRemainingTextView);
                    roundMode = 3;
                    break;
                case 20:
                    setContentView(R.layout.larg_activity_game);
                    textView = findViewById(R.id.largRoundsRemainingTextView);
                    roundMode = 5;
                    break;
            }
            //TODO make sure this block of code checks to see what the settings are and runs them correctly.
        } else{
            grid = new int[gridSize][gridSize];
            visited = new boolean[gridSize][gridSize];
            switch (gridSize){
                case 10:
                    roundMode = 1;
                    break;
                case 20:
                    roundMode = 2;
                    break;
                case 30:
                    roundMode = 3;
                    break;
            }
            switch (colours) {
                case 4:
                    setContentView(R.layout.sm_activity_game);
                    textView = findViewById(R.id.smRoundsRemainingTextView);
                    roundMode += 1;
                    break;
                case 6:
                    setContentView(R.layout.md_activity_game);
                    textView = findViewById(R.id.mdRoundsRemainingTextView);
                    roundMode += 2;
                    break;
                case 8:
                    setContentView(R.layout.larg_activity_game);
                    textView = findViewById(R.id.largRoundsRemainingTextView);
                    roundMode += 3;
                    break;
            }
        }

        gridView = findViewById(R.id.gridView);

        //Checks the colour buttons in the sm_activity_game.xml, and adds to a buttonLayout, this is then added to a list view
        //which is used to add the colors which will be used in the grid.
        //small= 4, mid = 6, max = 8
        RelativeLayout buttonLayout = findViewById(R.id.ColourButtonLayout);
        List<View> buttons = buttonLayout.getTouchables();

        //adds all of the colours from the xml layout to the option int array.
        options = new int[buttons.size()];

        for (int i = 0; i < buttons.size(); i++) {
            Drawable background = buttons.get(i).getBackground();
            if (background instanceof ColorDrawable) {
                options[i] = ((ColorDrawable) background).getColor();
            }
        }
        gameSettings = new GameLogic(grid, visited, options);

        gameSettings.setRound(roundMode);

        textView.setText(String.valueOf(gameSettings.getRound()));

        playerTextView = findViewById(R.id.PlayerNameTextView);
        playerTextView.setText(playerName);
    gridView.getHolder().addCallback(this);
    }

    public void onItem(View view) {
        //Gets the starting colour from 0,0 in the grid array and passes it into the flood method to work out
        // which new areas of the board need to be flooded with the colour.

        int baseColor = gameSettings.getGridColour(0,0);
        gameSettings.flood(0, 0, baseColor, ((ColorDrawable) view.getBackground()).getColor());
        gameSettings.calulateWinState();
        gridView.repaint(gameSettings.getGrid(), gameSettings.getVisited());

        gameSettings.removeRound();
        textView.setText(String.valueOf(gameSettings.getRound()));

        //checks the amount of rounds remaining and if the cells are all flooded.
        if(gameSettings.getHasWon()){
            Intent intent = new Intent(getBaseContext(), WinLose.class);
            intent.putExtra("won", true);
            startActivity(intent);
        } else if (gameSettings.getRound() <= 0){
            Intent intent = new Intent(getBaseContext(), WinLose.class);
            intent.putExtra("won", false);
            startActivity(intent);
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gridView.repaint(gameSettings.getGrid(), gameSettings.getVisited());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
