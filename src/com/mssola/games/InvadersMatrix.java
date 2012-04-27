

package com.mssola.games;

import com.mssola.retrogames.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;


public class InvadersMatrix
{
	Invader[] _invaders;
	private static final int ISIZE = 41;
	public int n_alives;

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
	}
	
	public void update()
	{
		
	}
	
	public boolean gotcha(Bullet bullet)
	{
		for (int i = 0; i < 16; i++) {
			if (_invaders[i]._valid && contact(_invaders[i], bullet)) {
				_invaders[i]._valid = false;
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
	
	private boolean contact(Invader inv, Bullet b)
	{
		return (b.between(inv._posx - 10, inv._size) && inv._posy > b._posy);
	}
}
