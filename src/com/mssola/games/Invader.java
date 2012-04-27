
package com.mssola.games;

import com.mssola.helpers.Sprite;


public class Invader extends Sprite
{
	public boolean _valid;

	public Invader(int x, int y)
	{
		super(x, y, 41);
		_valid = true;
	}
}
