
package com.mssola.games;

import com.mssola.helpers.Sprite;


public class Bullet extends Sprite
{
	public boolean _direction, _valid;

	public Bullet(int x, int y, boolean direction)
	{
		super(x, y, 5);
		_direction = direction;
		_valid = false;
	}
	
	public void update()
	{
		if (_valid) {
			if (_direction)
				_posy -= 5;
			else
				_posy += 5;
		}
	}
	
	public void watch_the_walls(int height)
	{
		if (_posy < 0 || _posy > height)
			_valid = false;
	}
}
