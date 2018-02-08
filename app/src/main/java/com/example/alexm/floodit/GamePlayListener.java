package com.example.alexm.floodit;

/**
 * Listener interface for games handling color changes
 */

public  interface GamePlayListener {
    /**
     * SAM function invoked when the game state has changed.
     * @param game The game that changed.
     * @param round The round the game is in.
     */
    void onGameChanged(final GameLogic game, int round);



}
