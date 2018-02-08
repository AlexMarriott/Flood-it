package com.example.alexm.floodit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * <h1>GameGridView</h1>
 * add a description of the class
 *
 * @author Alex Marriott s4816928
 * @version 1.0
 * @since 06/01/2018
 */

public class GameGridView extends SurfaceView {

    public GameGridView(Context context) {
        super(context);
    }

    public GameGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void repaint(int[][] grid, boolean[][] visited) {

        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            Paint background = new Paint();
            background.setColor(Color.BLACK);

            canvas.drawRect(0, 0, getWidth(), getHeight(), background);

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int tileSize;
                    if (canvas.getHeight() > canvas.getWidth()) {
                        tileSize = canvas.getWidth() / grid.length;
                    } else {
                        tileSize = canvas.getHeight() / grid.length;
                    }
                    int x = j * tileSize;
                    int y = i * tileSize;
                    Paint paint = new Paint();
                    //Sets the colours according too the int within the grid array https://developer.android.com/reference/android/graphics/Color.html#RED
                    paint.setColor(grid[i][j]);
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);

                    canvas.drawRect(x, y, x + tileSize, y + tileSize, paint);
                    visited[i][j] = false;
                }
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

}

