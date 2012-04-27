

package com.mssola.games;

import com.mssola.retrogames.R;


import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class InvadersMatrix
{
	Invader[] _invaders;
	private static final int ISIZE = 41;
	public int n_alives;
	private boolean going_right;
	private int left, right, bottom, _width, _height;
	private int ax;

	public InvadersMatrix(int height, int width, Resources res)
	{
		_invaders = new Invader[16];
		n_alives = 16;
		int padding = (width - (4* ISIZE)) / 5;
		int posx, posy = 10;
		for (int i = 0, k = 0; i < 4; i++, posy += 45) {
			posx = 0;
			for (int j = 0; j < 4; j++, k++) {
				posx += padding;
				_invaders[k] = new Invader(posx, posy);
				_invaders[k]._bitmap = BitmapFactory.decodeResource(res, R.drawable.invader);
				posx += ISIZE;
			}
		}
		going_right = true;
		left = 0;
		right = 3;
		_width = width;
		_height = height;
		bottom = 15;
		ax = 1;
	}
	
	public void update()
	{
		if (n_alives == 0)
			return;

		int accx = 0;
		int accy = 0;
		if (going_right) {
			if (_invaders[right]._posx + ISIZE < _width - 10)
				accx += ax;
			else {
				accy = 5;
				going_right = false;
			}
		} else {
			if (_invaders[left]._posx > 10)
				accx -= ax;
			else {
				accy = 5;
				going_right = true;
			}
		}

		for (int i = 0; i < 16; i++) {
			_invaders[i]._posx += accx;
			_invaders[i]._posy += accy;
		}
	}
	
	public boolean gotcha(Bullet bullet)
	{
		if (!bullet._valid)
			return false;

		for (int i = 0; i < 16; i++) {
			if (_invaders[i]._valid && contact(_invaders[i], bullet)) {
				_invaders[i]._valid = false;
				n_alives--;
				if (n_alives == 8)
					ax++;
				else if (n_alives == 4)
					ax++;
				if (i == right)
					pick_right();
				else if (i == left)
					pick_left();
				else if (i == bottom)
					pick_bottom();
				return true;
			}
		}
		return false;
	}
	
	public void draw(Canvas canvas, Paint paint)
	{
		for (int i = 0; i < 16; i++) {
			if (_invaders[i]._valid) {
				canvas.drawBitmap(	_invaders[i]._bitmap, _invaders[i]._posx,
									_invaders[i]._posy, paint);
			}
		}
	}
	
	public int get_bottom()
	{
		return _invaders[bottom]._posy;
	}
	
	private boolean contact(Invader inv, Bullet b)
	{
		return (b.between(inv._posx - 10, inv._size) && inv._posy > b._posy);
	}
	
	private void pick_right()
	{
		if (n_alives == 0)
			return;
		
		for (int i = 3; i >= 0; i--) {
			for (int j = 0; j < 16; j += 4) {
				if (_invaders[i+j]._valid) {
					right = i+j;
					return;
				}
			}
		}
	}
	
	private void pick_left()
	{
		if (n_alives == 0)
			return;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j += 4) {
				if (_invaders[i+j]._valid) {
					left = i+j;
					return;
				}
			}
		}
	}
	
	private void pick_bottom()
	{
		if (n_alives == 0)
			return;
		
		for (int j = 12; j >= 0; j -= 4) {
			for (int i = 3; i >= 0; i--) {
				if (_invaders[i+j]._valid) {
					bottom = i+j;
					return;
				}
			}
		}
	}
}
