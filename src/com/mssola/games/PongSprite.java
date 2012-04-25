
package com.mssola.games;

import com.mssola.helpers.Sprite;
import java.util.Random;
import android.graphics.Rect;


public class PongSprite extends Sprite
{
	public int _vx, _vy;
	private int _height, _width, _goal;

	public PongSprite(int v, int height, int width, int size)
	{
		_vx = _vy = v;
		_height = height;
		_width = width;
		_size = size;
		new_posx();
		_posy = height / 2;
	}

	public void update()
	{
		_posx += _vx;
		_posy += _vy;
		_goal = 0;
		if (_posy > _height) {
			new_posx();
			_posy = _height / 2;
			_goal = 1;
		} else if (_posy < 0) {
			new_posx();
			_posy = _height / 2;
			_goal = -1;
		}
	}
	
	public void change_if_not_between(int left, int right)
	{
		if (_posx < left || _posx > right)
			_vx *= -1;
	}
	
	public void change()
	{
		_vy *= -1;
	}
	
	public int scored()
	{
		return _goal;
	}
	
	public Rect get_sprite()
	{
		return new Rect(_posx, _posy, _posx + _size, _posy + _size);
	}
	
	private void new_posx()
	{
		Random r = new Random();
		_posx = r.nextInt(_width - 20);
	}
}
