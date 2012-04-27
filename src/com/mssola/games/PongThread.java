/*
 * Copyright 2012 Miquel Sabaté <mikisabate@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.mssola.games;

import com.mssola.helpers.Statistics;
import com.mssola.retrogames.RetroGamesApplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;


/**
 * The game thread.
 */
public class PongThread extends Thread
{
    private SurfaceHolder _surfaceHolder;
    private Paint _paint;
    private PongState _state;
    static private boolean _running;
    private Activity _act;
    private Statistics _stats;
	
    /**
     * Constructor.
     */
    public PongThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        _surfaceHolder = surfaceHolder;
        _paint = new Paint();
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        _act = (Activity) context;
        RetroGamesApplication app = (RetroGamesApplication) context.getApplicationContext();
        _stats = app.getStatistics();
        _state = new PongState(height, width, app);
    }
	
    /**
     * The run method. Take care of the drawing and update the game.
     */
    @Override
    public void run()
    {
        while(_running) {
            Canvas canvas = _surfaceHolder.lockCanvas();
            if (_state.should_finish) {
            	_stats.scores += _stats.last_scores;
            	_stats.escores += _stats.last_escores;
            	if (_stats.last_scores > _stats.last_escores)
            		_stats.winned++;
            	else
            		_stats.lost++;
            	_act.finish();
            } else {
	            _state.update();
	            _state.draw(canvas,_paint);
            }
            _surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
	
    /**
     * @return the current game state.
     */
    public PongState getGameState()
    {
        return _state;
    }
    
    /**
     * Set if this thread running attribute.
     * @param running The new value for the running attribute.
     */
    public void setRunning(boolean running)
    {
    	_running = running;
    }
    
    /**
     * @return the value of the running attribute.
     */
    public boolean getRunning()
    {
    	return _running;
    }
}
