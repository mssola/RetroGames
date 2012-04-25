
package com.mssola.helpers;


public class Sprite
{
	public int _posx, _posy;
	protected int _size;
	
	public Sprite()
	{
		/* There's nothing to do here */
	}

	public Sprite(int x, int y, int size)
	{
		_posx = x;
		_posy = y;
		_size = size;
	}
	
	public boolean between(int left, int width)
	{
		return (_posx > left && _posx < (left + width));
	}
}
