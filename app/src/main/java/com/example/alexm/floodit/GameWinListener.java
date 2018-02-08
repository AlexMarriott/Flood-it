package com.example.alexm.floodit;

/**
 * Listener interface for games handling win conditions
 */

public  interface GameWinListener {
    /**
     * SAM function invoked when the game has been won.
     * @param game The game that changed.
     * @param rounds The rounds taken to win.
     */
    void onWon(final GameLogic game, int rounds);



}
