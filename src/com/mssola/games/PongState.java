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

import com.mssola.helpers.Settings;
import com.mssola.helpers.Statistics;
import com.mssola.retrogames.RetroGamesApplication;
import java.util.Timer;
import java.util.TimerTask;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * Handles the logic of the Pong game.
 */
public class PongState
{
    /* Screen width and height */
    int _screenWidth;
    int _screenHeight;

    /* The user's bat */
    int _batLength = 75;
    int _batHeight = 10;
    int _batSpeed = 7;
    int _bottomBatX, _bottomBatY;
    
    /* Handling some issues around the accelerometer */
    boolean last;
    int noise = 4;
    
    /* Others */
    Settings _settings;
    Statistics _stats;
    PongSprite[] _balls;
    PongEnemy _enemy;


    /**
     * Constructor.
     */
    public PongState(int height, int width, RetroGamesApplication app)
    {
        _screenHeight = height;
        _screenWidth = width;
        _bottomBatX = (_screenWidth/2) - (_batLength / 2);
        _bottomBatY = _screenHeight - 20;
        last = false;
        _settings = app.getSettings();
        int n = _settings.getBalls();
        _stats = app.getStatistics();
        _balls = new PongSprite[n];
        for (int i = 0; i < _balls.length; i++) {
        	_balls[i] = new PongSprite(3, _screenHeight, _screenWidth, 10);
        	_balls[i]._posx += i * 10;
        	if (i % 2 == 0)
        		_balls[i]._vy = -_balls[i]._vy;
        }
        int level = _settings.getLevel();
        _enemy = new PongEnemy((_screenWidth/2), 20, 75, level);
        if (_settings.getSudden()) {
        	new Timer().scheduleAtFixedRate(new TimerTask() {
                public void run()
                {
                	for (int i = 0; i < _balls.length; i++)
                		_balls[i]._vx = -_balls[i]._vx;
                }
            }, 0, 1500);
        }
    }

    /**
     * The update method. Update the positions of the balls and the position
     * of the enemy.
     */
    public void update()
    {
    	for (int i = 0; i < _balls.length; i++) {
    		_balls[i].update();
    		// TODO handle scored
    		_balls[i].change_if_not_between(0, _screenWidth);
    		if (_balls[i].between(_enemy._posx, _batLength) && _balls[i]._posy < _enemy._posy)
    			_balls[i].change();
    		if (_balls[i].between(_bottomBatX, _batLength) && _balls[i]._posy > _bottomBatY)
    			_balls[i].change();
    	}
    	_enemy.watch_position(_balls[0]._posx);
    	_enemy.next_move();
    }
    
    /**
     * Accelerate the user's bat.
     * @param accc The given acceleration (from the accelerometer).
     */
    public void accelerateX(float accx)
    {
    	if (accx > 0 && accx < noise && !last)
    		accx = -1;
    	else if (accx < 0 && accx > -noise && last)
    		accx = 1;
    	if (accx > 0) {
    		_bottomBatX -= _batSpeed;
    		last = true;
    	} else if (accx < 0) {
    		_bottomBatX += _batSpeed;
    		last = false;
    	}
    }

    /**
     * Draw the scenario.
     */
    public void draw(Canvas canvas, Paint paint)
    {
    	/* Refresh the display */
        canvas.drawRGB(20, 20, 20);
        paint.setARGB(200, 0, 200, 0);
        paint.setColor(Color.rgb(255, 255, 255));

        /* draw the ball */
        for (int i = 0; i < _balls.length; i++)
        	canvas.drawRect(_balls[i].get_sprite(), paint);

        /* draw the bats */
        canvas.drawRect(new Rect(_enemy._posx, _enemy._posy, _enemy._posx + _batLength,
        								_enemy._posy + _batHeight), paint);
        canvas.drawRect(new Rect(_bottomBatX, _bottomBatY, _bottomBatX + _batLength,
                                              _bottomBatY + _batHeight), paint);
    }
}
