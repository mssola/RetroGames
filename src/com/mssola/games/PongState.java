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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class PongState
{
    //screen width and height
    int _screenWidth;
    int _screenHeight;

    //The ball
    final int _ballSize = 10;
    int _ballX, _ballY;
    int _ballVelocityX = 3;
    int _ballVelocityY = 3;

    //The bats
    int _batLength = 75;
    int _batHeight = 10;
    int _batSpeed = 7;
    int _topBatX, _topBatY;
    int _bottomBatX, _bottomBatY;
    
    boolean last;
    int noise = 4;
    
    Settings _settings;
    Statistics _stats;
    PongSprite[] _balls;


    public PongState(int height, int width, RetroGamesApplication app)
    {
        _screenHeight = height;
        _screenWidth = width;
        _topBatX = (_screenWidth/2) - (_batLength / 2);
        _topBatY = 20;
        _bottomBatX = (_screenWidth/2) - (_batLength / 2);
        _bottomBatY = _screenHeight - 20;
        _ballX = _screenWidth / 2;
        _ballY = _screenHeight / 2;
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
    }

    //The update method
    public void update()
    {
    	for (int i = 0; i < _balls.length; i++) {
    		_balls[i].update();
    		// TODO handle scored
    		_balls[i].change_if_not_between(0, _screenWidth);
    		if (_balls[i].between(_topBatX, _batLength) && _balls[i]._posy < _topBatY)
    			_balls[i].change();
    		if (_balls[i].between(_bottomBatX, _batLength) && _balls[i]._posy > _bottomBatY)
    			_balls[i].change();
    	}
//        _ballX += _ballVelocityX;
//        _ballY += _ballVelocityY;
//
//        if(_ballY > _screenHeight || _ballY < 0) {
//            _ballX = _screenWidth / 2;
//            _ballY = _screenHeight / 2;        
//        }

//        if(_ballX > _screenWidth || _ballX < 0)
//            _ballVelocityX *= -1;

//        if(_ballX > _topBatX && _ballX < _topBatX+_batLength && _ballY < _topBatY)
//            _ballVelocityY *= -1;
//
//        if(_ballX > _bottomBatX && _ballX < _bottomBatX+_batLength && _ballY > _bottomBatY)
//          _ballVelocityY *= -1;
    }
    
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

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawRGB(20, 20, 20);
        paint.setARGB(200, 0, 200, 0);
        paint.setColor(Color.rgb(255, 255, 255));

        /* draw the ball */
        for (int i = 0; i < _balls.length; i++)
        	canvas.drawRect(_balls[i].get_sprite(), paint);

        /* draw the bats */
        canvas.drawRect(new Rect(_topBatX, _topBatY, _topBatX + _batLength,
                                              _topBatY + _batHeight), paint); //top bat
        canvas.drawRect(new Rect(_bottomBatX, _bottomBatY, _bottomBatX + _batLength,
                                              _bottomBatY + _batHeight), paint); //bottom bat
    }
}
