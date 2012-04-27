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
import com.mssola.retrogames.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * Matrix of invaders. It handles a lot of crazy things so the SIState class
 * has no worries about handling a particular invader.
 */
public class InvadersMatrix
{
	Invader[] _invaders;
	public Bullet bullet;
	private static final int ISIZE = 41;
	public int n_alives;
	private boolean going_right;
	private int left, right, bottom, _width, _height;
	private int ax, counter;
	protected int last_shooter;

	/**
	 * Constructor.
	 */
	public InvadersMatrix(int height, int width, Resources res, boolean attack)
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
		counter = 10;
		last_shooter = -1;
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.bullet);
		bullet = new Bullet(0, 0, false);
		bullet._valid = false;
		bullet._bitmap = bitmap;
	}
	
	/**
	 * The update method.
	 */
	public void update()
	{
		if (n_alives == 0)
			return;

		int accx = 0;
		int accy = 0;
		counter--;
		if (counter == 0)
			shoot();
		if (bullet._valid) {
			bullet.update();
			bullet.watch_the_walls(_height);
			if (!bullet._valid)
				counter = 10;
		}
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
	
	/**
	 * Check if the given bullet got an invader.
	 * @param bullet The hero's bullet. We cannot rely on the validity of it.
	 * @return false if these invaders are alright, true otherwise.
	 */
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
	
	/**
	 * Draw the matrix of invaders and the bullet shot by one of the invaders.
	 */
	public void draw(Canvas canvas, Paint paint)
	{
		for (int i = 0; i < 16; i++) {
			if (_invaders[i]._valid) {
				canvas.drawBitmap(	_invaders[i]._bitmap, _invaders[i]._posx,
									_invaders[i]._posy, paint);
			}
		}
		if (bullet._valid) {
			canvas.drawBitmap(bullet._bitmap, bullet._posx, bullet._posy, paint);
		}
	}
	
	/**
	 * Get the position on the y axis from the invader that is at the very
	 * bottom of the matrix.
	 */
	public int get_bottom()
	{
		return _invaders[bottom]._posy;
	}
	
	/**
	 * Check if a bullet shot by one of the invaders got the hero.
	 * @param h The hero.
	 * @return true if the hero got hit (and dead), false otherwise.
	 */
	public boolean got_us(Sprite h)
	{
		if (bullet._valid)
			return (bullet.between(h._posx - 10, h._size) && h._posy < bullet._posy);
		return false;
	}
	
	public boolean got_blocked(Sprite[] bunkers)
	{
		if (!bullet._valid)
			return false;

		for (int i = 0; i < bunkers.length; i++) {
			if (bullet.between(bunkers[i]._posx, bunkers[i]._size))
				return (bullet._posy < bunkers[i]._posy);
		}
		return false;
	}
	
	/**
	 * @param inv An invader from this matrix.
	 * @param b The hero's bullet.
	 * @return true if the hero's bullet got a particular invader from
	 * this matrix, false otherwise.
	 */
	private boolean contact(Invader inv, Bullet b)
	{
		return (b.between(inv._posx - 10, inv._size) && inv._posy > b._posy);
	}
	
	/**
	 * Pick the rightmost invader. 
	 */
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
	
	/**
	 * Pick the leftmost invader.
	 */
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
	
	/**
	 * Pick one invader from the very bottom.
	 */
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
	
	/**
	 * It's time to rock. Let one random invader to kick some ass by
	 * shooting the hero.
	 */
	private void shoot()
	{
		int id = 0;
		for (int i = 0; i < 16; i++) {
			if (_invaders[i]._valid) {
				id = i;
				break;
			}
		}
		bullet._posx = _invaders[id]._posx;
		bullet._posy = _invaders[id]._posy;
		bullet._valid = true;
	}
}
