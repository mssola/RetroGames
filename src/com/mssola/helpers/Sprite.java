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


package com.mssola.helpers;


/**
 * The base class for a widget on a game. 
 */
public class Sprite
{
	public int _posx, _posy;
	protected int _size;
	
	/**
	 * Constructor.
	 */
	public Sprite()
	{
		/* There's nothing to do here */
	}

	/**
	 * Constructor.
	 */
	public Sprite(int x, int y, int size)
	{
		_posx = x;
		_posy = y;
		_size = size;
	}
	
	/**
	 * Check if the position of this sprite is between the given
	 * position and the given position plus the width.
	 * @param left The given position.
	 * @param width The width of the other element.
	 */
	public boolean between(int left, int width)
	{
		return (_posx > left && _posx < (left + width));
	}
}
