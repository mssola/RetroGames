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
 * The Invader class is just a Sprite with a fixed width (41) and a
 * _valid attribute.
 */
public class Invader extends Sprite
{
	/**
	 * If this attribute is set to false, it means that this invader is dead.
	 * Otherwise it means that this invader is still kicking our ass.
	 */
	public boolean _valid;

	/**
	 * Constructor.
	 */
	public Invader(int x, int y)
	{
		super(x, y, 41);
		_valid = true;
	}
}
