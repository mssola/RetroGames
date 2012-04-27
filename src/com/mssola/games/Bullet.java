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


/**
 * Class that represents a bullet. It handles the collisions with walls
 * and it tells if this bullet is valid (can be display). 
 */
public class Bullet extends Sprite
{
	/**
	 * Set to true if this is a bullet from our Hero. Set to false if this
	 * is an invader's bullet. 
	 */
	private boolean _direction;
	
	/**
	 * A bullet is valid if it can be displayed. That is, if it's contrained
	 * in the display's dimensions and it hasn't reached a body yet.
	 */
	public boolean _valid;

	/**
	 * Constructor.
	 */
	public Bullet(int x, int y, boolean direction)
	{
		super(x, y, 5);
		_direction = direction;
		_valid = false;
	}
	
	/**
	 * The update method. It moves the bullet according to the direction
	 * set in the constructor. 
	 */
	public void update()
	{
		if (_valid) {
			if (_direction)
				_posy -= 5;
			else
				_posy += 5;
		}
	}
	
	/**
	 * Invalidate this bullet if it's out of the display.
	 * @param height The height of the display.
	 */
	public void watch_the_walls(int height)
	{
		if (_posy < 0 || _posy > height)
			_valid = false;
	}
}
