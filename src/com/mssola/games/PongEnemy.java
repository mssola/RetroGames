
package com.mssola.games;

import com.mssola.helpers.Sprite;


public class PongEnemy extends Sprite
{
	private int _level, _counter, _watched;
	private boolean _direction;
	
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
	
	public void next_move()
	{
		switch (_level) {
			case 1: level1(); break;
			case 2: level2(); break;
			case 3: level3(); break;
		}
	}
	
	public void watch_position(int pos)
	{
		_watched = pos;
	}
	
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
	
	private void level3()
	{
		if (_watched > _posx)
			_posx += 3;
		else
			_posx -= 3;
	}
}
