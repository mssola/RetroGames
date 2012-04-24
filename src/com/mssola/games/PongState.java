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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;


public class PongState
{
    //screen width and height
    final int _screenWidth = 300;
    final int _screenHeight = 420;

    //The ball
    final int _ballSize = 10;
    int _ballX = 100;       int _ballY = 100;
    int _ballVelocityX = 3;         int _ballVelocityY = 3;

    //The bats
    final int _batLength = 75;      final int _batHeight = 10;
    int _topBatX = (_screenWidth/2) - (_batLength / 2);
    final int _topBatY = 20;
    int _bottomBatX = (_screenWidth/2) - (_batLength / 2);
    final int _bottomBatY = 400;
    final int _batSpeed = 3;


    public PongState()
    {
        // TODO
    }

    //The update method
    public void update()
    {
        _ballX += _ballVelocityX;
        _ballY += _ballVelocityY;

        //DEATH!
        if(_ballY > _screenHeight || _ballY < 0)
        {_ballX = 100;  _ballY = 100;}          //Collisions with the sides

        if(_ballX > _screenWidth || _ballX < 0)
                        _ballVelocityX *= -1;   //Collisions with the bats

        if(_ballX > _topBatX && _ballX < _topBatX+_batLength && _ballY < _topBatY)
                        _ballVelocityY *= -1;  //Collisions with the bats

        if(_ballX > _bottomBatX && _ballX < _bottomBatX+_batLength
                        && _ballY > _bottomBatY)
                              _ballVelocityY *= -1;
    }

    public boolean keyPressed(int keyCode, KeyEvent msg)
    {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            _topBatX += _batSpeed; _bottomBatX -= _batSpeed;
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            _topBatX -= _batSpeed; _bottomBatX += _batSpeed;
        }
        return true;
    }

    //the draw method
    public void draw(Canvas canvas, Paint paint)
    {
        //Clear the screen
        canvas.drawRGB(20, 20, 20);

        //set the colour
        paint.setARGB(200, 0, 200, 0);

        //draw the ball
        canvas.drawRect(new Rect(_ballX,_ballY,_ballX + _ballSize,_ballY + _ballSize),
                                    paint);

        //draw the bats
        canvas.drawRect(new Rect(_topBatX, _topBatY, _topBatX + _batLength,
                                              _topBatY + _batHeight), paint); //top bat
        canvas.drawRect(new Rect(_bottomBatX, _bottomBatY, _bottomBatX + _batLength,
                                              _bottomBatY + _batHeight), paint); //bottom bat
    }
}
