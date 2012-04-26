
package com.mssola.games;

import com.mssola.retrogames.RetroGamesApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;



public class SIThread extends Thread
{
    private SurfaceHolder _surfaceHolder;
    private Paint _paint;
    private SIState _state;
    static private boolean _running;
	
    /**
     * Constructor.
     */
    public SIThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        _surfaceHolder = surfaceHolder;
        _paint = new Paint();
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        RetroGamesApplication app = (RetroGamesApplication) context.getApplicationContext();
        _state = new SIState(height, width, app);
    }
	
    /**
     * The run method. Take care of the drawing and update the game.
     */
    @Override
    public void run()
    {
        while(_running) {
            Canvas canvas = _surfaceHolder.lockCanvas();
            _state.update();
            _state.draw(canvas,_paint);
            _surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
	
    /**
     * @return the current game state.
     */
    public SIState getGameState()
    {
        return _state;
    }
    
    public void setRunning(boolean running)
    {
    	_running = running;
    }
    
    public boolean getRunning()
    {
    	return _running;
    }
}
