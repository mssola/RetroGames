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

import com.mssola.helpers.Sprite;
import java.util.Random;
import android.graphics.Rect;


/**
 * The Sprite that represents the ball.
 */
public class PongSprite extends Sprite
{
	public int _vx, _vy, _goal;
	private int _height, _width, _lives;

	/**
	 * Constructor. 
	 */
	public PongSprite(int v, int height, int width, int size)
	{
		_vx = _vy = v;
		_height = height;
		_width = width;
		_size = size;
		new_posx();
		_posy = height / 2;
		_lives = 3;
	}

	/**
	 * Update the current position and how many lives this ball still has.
	 */
	public void update()
	{
		_posx += _vx;
		_posy += _vy;
		_goal = 0;
		if (_posy > _height) {
			--_lives;
			_goal = 1;
			if (_lives > 0) {
				new_posx();
				_posy = _height / 2;
			}
		} else if (_posy < 0) {
			--_lives;
			_goal = -1;
			if (_lives > 0) {
				new_posx();
				_posy = _height / 2;
			}
		}
	}
	
	/**
	 * If this ball is not between the given integers, change the
	 * acceleration on the x axis.
	 * @param left The minimum value.
	 * @param right The maximum value.
	 */
	public void change_if_not_between(int left, int right)
	{
		if (_posx < left || _posx > right)
			_vx *= -1;
	}
	
	/**
	 * Change the acceleration on the y axis.
	 */
	public void change()
	{
		_vy *= -1;
	}
	
	/**
	 * Return true if a team has scored with thi ball, false otherwise.
	 */
	public int scored()
	{
		return _goal;
	}
	
	/**
	 * @return a Rect representing this ball.
	 */
	public Rect get_sprite()
	{
		return new Rect(_posx, _posy, _posx + _size, _posy + _size);
	}
	
	/**
	 * Set a random value to the position on the x axis.
	 */
	private void new_posx()
	{
		Random r = new Random();
		_posx = r.nextInt(_width - 20);
	}
	
	/**
	 * Get how many lives has this ball.
	 */
	public int get_lives()
	{
		return _lives;
	}
}
