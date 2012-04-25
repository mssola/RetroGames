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
 * Implements the logic of the enemy on the Pong game. 
 */
public class PongEnemy extends Sprite
{
	private int _level, _counter, _watched;
	private boolean _direction;
	
	/**
	 * Constructor. 
	 */
	public PongEnemy(int x, int y, int size, int level)
	{
		super(x, y, size);
		_level = level;
		_counter = 0;
		_direction = true;
		if (level == 1)
			_posx -= 90;
		else if (level == 2)
			_posx -= 150;
	}
	
	/**
	 * Check the level of difficulty to select the required algorithm. 
	 */
	public void next_move()
	{
		switch (_level) {
			case 1: level1(); break;
			case 2: level2(); break;
			case 3: level3(); break;
		}
	}
	
	/**
	 * Store the given position.
	 * @param pos The given position.
	 */
	public void watch_position(int pos)
	{
		_watched = pos;
	}
	
	/**
	 * Go left and right, not too fast, not too smart.
	 */
	private void level1()
	{
		++_counter;
		if (_counter > 90) {
			_direction = !_direction;
			_counter = 0;
		}
		if (_direction)
			_posx += 1;
		else
			_posx -= 1;
	}
	
	/**
	 * Go left and right, faster than the first algorithm.
	 */
	private void level2()
	{
		++_counter;
		if (_counter > 80) {
			_direction = !_direction;
			_counter = 0;
		}
		if (_direction)
			_posx += 3;
		else
			_posx -= 3;
	}
	
	/**
	 * Go after the watched position.
	 */
	private void level3()
	{
		if (_watched > _posx)
			_posx += 3;
		else
			_posx -= 3;
	}
}
