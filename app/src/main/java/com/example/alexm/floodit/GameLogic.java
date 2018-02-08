package com.example.alexm.floodit;

import android.widget.TextView;

public class GameLogic {
    /**
     * The default amount of columns in the game (horizontal "pixel" amount).
     */
    public static int DEFAULT_WIDTH = 30;

    /**
     * The default amount of rows in the game (vertical "pixel" amount)
     */
    public static int DEFAULT_HEIGHT = 30;


    private GameGridView gridView;
    private static int[][] mgrid; //Default value of 15 (medium)
    private int roundCounter;
    private int roundMode = 0;
    private boolean[][] mvisited;
    private final int[] roundCap = {20, 30, 40};
    private int roundTotal;
    private boolean hasWon = false;



    public GameLogic(int[][] grid, boolean[][] visited, int[] options) {
        mgrid = grid;
        mvisited = visited;

        //Building a grid from two for loops where i = y corr
        for (int i = 0; i < grid.length; i++) {
            //grid[i] = new int[15];
            for (int j = 0; j < grid[i].length; j++) {
                //selects a random colour by using the random method. The parameters are 0 and the maximum length is the amount of options.
                mgrid[i][j] = options[random(0, options.length)];
            }
        }
        setRound(roundMode);

    }
    //creates an two, two dimensional arrays which are used to create the grid and check the visited tiles.


    public int getGridColour(int x, int y) {
        return mgrid[x][y];
    }

    public int[][] getGrid() {
        return mgrid;
    }

    public boolean[][] getVisited() {
        return mvisited;
    }

    public void setVisitedTrue(int x, int y) {
    }

    /**
     * Implement this function to return the current game round (starting with 1, every flood
     * operation updates the round.
     *
     * @return The current round
     */
    public int getRound() {
        return roundTotal;
    }

    public void removeRound() {
        roundTotal = roundTotal - 1;
    }

    public void setRound(int gameMode) {

        if (gameMode == 1) {
            roundTotal = roundCap[0];
        } else if (gameMode == 2) {
            roundTotal = roundCap[1];
        } else if (gameMode == 3) {
            roundTotal = roundCap[2];
        }
    }

    void flood(int x, int y, int currentColour, int buttonColour) {
        //checks to see if the x or y is outside of the screen area and checks if its the end of the grid array.
        if (x < 0 || y < 0 || x > mgrid.length - 1 || y > mgrid[x].length - 1) return;

        int base = mgrid[x][y];
        //checks to see if the current grid locations has been visited or if the colour is the players current colour
        if (!mvisited[x][y] && base == currentColour) {
            //if the tile matches the button colour, the button colour is added to the grid array, the location is then
            // set to true in the visited array and then we start to recursively check the following ties around the
            //tile we changed. method ends if the tile is already visited or the colours are not the selected colour.
            mgrid[x][y] = buttonColour;
            mvisited[x][y] = true;
            flood(x - 1, y, currentColour, buttonColour);
            flood(x + 1, y, currentColour, buttonColour);
            flood(x, y + 1, currentColour, buttonColour);
            flood(x, y - 1, currentColour, buttonColour);

        }
    }


    //Created own random because the standard built in random for java is bad.
    private static int random(int min, int max) {
        return (int) (min + (Math.random() * max));

    }

    /**
     * Implement this function to return the current game round (starting with 1, every flood
     * operation updates the round.
     *
     * @return The current round

    public int getRound() {
    return roundTotal;
    }

    public void removeRound(){
    roundTotal = roundTotal - 1;
    }
    public void setRound(int gameMode){

    if (gameMode == 1){
    roundTotal = roundCap[0];
    }else if (gameMode == 2){
    roundTotal = roundCap[1];
    }else if(gameMode == 3){
    roundTotal = roundCap[2];
    }
    }*/


    /**
     * Set the colour at position (x,y) to the colour identified by the colour parameter
     *
     * @param x      The column to change
     * @param y      The row to change
     * @param colour The new colour.
     */
    protected void setColor(int x, int y, int colour) {
        ;
    }

    /**
     * Get the colour at position (x,y)
     *
     * @param x The column to change
     * @param y The row to change
     * @return The colour at the coordinates.
     */
    public int getColor(int x, int y) {
        return 0;
    }


    /**
     * Determine whether the game has been won.
     *
     * @return <code>true</code> if won, <code>false</code> if the game has not yet been won.
     */
    public boolean calulateWinState() {
        for (int x = 0; x < mgrid.length; x++) {
            for (int y = 0; y < mgrid.length; y++) {
                if (mgrid[x][y] != mgrid[0][0] ) {
                    return hasWon = false;
                }
            }
        }
        return hasWon = true;
    }

    public boolean getHasWon() {
        return hasWon;
    }
}






/*
    /**
     * Get the set of objects that listen to game changes. You need this function to implement the
     * code that informs the listeners (loop...)
     * @return The set of listeners.

    protected Set<GamePlayListener> getGamePlayListeners() {
        return mGamePlayListeners;
    }

    /**
     * Remove the given listener from the game play listener set.
     * @param listener Listener to remove

    public void removeGamePlayListener(final GamePlayListener listener) {
        mGamePlayListeners.remove(listener);
    }

    /**
     * Add the given listener to the set of listeners that want to listen to game updates.
     * @param listener The listener to add (if it is not there yet).

    public void addGamePlayListener(final GamePlayListener listener) {
        mGamePlayListeners.add(listener);
    }

    /**
     * Get the set of objects that listen to game wins. You need this function to implement the
     * code that informs the listeners (loop...). The listener you register can be where you trigger a
     * dialogue to inform the user.
     *
     * @return The set of listeners.

    protected Set<GameWinListener> getGameWinListeners() {
        return mGameWinListeners;
    }

    /**
     * Remove the given listener from the game win listener set.
     * @param gameWinListener Listener to remove

    public void removeGameWinListener(final GameWinListener gameWinListener) {
        mGameWinListeners.remove(gameWinListener);
    }

    /**
     * Add the given listener to the set of listeners that want to listen to game wins.
     * @param gameWinListener The listener to add (if it is not there yet).

    public void addGameWinListener(final GameWinListener gameWinListener) {
        mGameWinListeners.add(gameWinListener);
    }

    /**
     * You should implement this function to call the gamePlayListeners with the given round.
     *
     * @param round The round that the game is in.

    void notifyMove(final int round) {

    }

    /**
     * You should implement this function to call the gameWinListeners with the given round.
     *
     * @param round The round that the game is in / the amount of rounds used.

    void notifyWin(final int round) {

    }
       /**
     * A set of objects that have registered to be informed when the game state changes (after a play
     * has been made).

private Set<GamePlayListener> mGamePlayListeners = new HashSet<>();

    /**
     * A set of objects that have registered to be informed when the game has been won.

    private Set<GameWinListener> mGameWinListeners = new HashSet<>();


/**
 * Function to use to actually implement a move (in the game the flow point is static (top left).
 * Implement this function to do a flood fill from the location with the given colour.
 *
 * @param clr The colour to fill with.

void playColour(final int clr) {

}
*/