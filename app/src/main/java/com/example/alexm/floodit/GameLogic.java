package com.example.alexm.floodit;


public class GameLogic {


    private static int[][] mgrid; //Default value of 15 (medium)
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

    public int getGridColour(int x, int y) {
        return mgrid[x][y];
    }

    public int[][] getGrid() {
        return mgrid;
    }

    public boolean[][] getVisited() {
        return mvisited;
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
     int random(int min, int max) {
        return (int) (min + (Math.random() * max));
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