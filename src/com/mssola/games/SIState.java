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

import com.mssola.helpers.Settings;
import com.mssola.helpers.Sprite;
import com.mssola.helpers.Statistics;
import com.mssola.retrogames.R;
import com.mssola.retrogames.RetroGamesApplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;


/**
 * The class that handle the state of the Space Invaders game.
 */
public class SIState
{
    /* Screen width and height */
    int _screenWidth;
    int _screenHeight;

    /* Handling some issues around the accelerometer */
    boolean last;
    int noise = 4;
    int speed = 7;
    
    /* Our hero! */
    Sprite hero;
    Bullet hero_bullet;
    
    /* Bunkers */
    Sprite[] bunkers;
    int n_bunkers;
    
    /* Invaders */
    InvadersMatrix invaders;
    
    /* Others */
    Resources _res;
    Statistics _stats;
    public boolean should_finish;

    /**
     * Constructor.
     */
    public SIState(RetroGamesApplication app, Resources res)
    {
        DisplayMetrics displaymetrics = res.getDisplayMetrics();
        _screenHeight = displaymetrics.heightPixels;
        _screenWidth = displaymetrics.widthPixels;
        _res = res;
        last = false;
        _stats = app.getStatistics();
        
        /* Initialize our hero! */
        int x = _screenWidth - 40;
        int y = _screenHeight - 50;
        hero = new Sprite(x, y, 32);
        Bitmap bitmap = BitmapFactory.decodeResource(_res, R.drawable.hero);
        
        /* And now the heroe's bullet */
        hero._bitmap = bitmap;
        hero_bullet = new Bullet(x, y, true);
        hero_bullet._valid = false;
        Bitmap bullet = BitmapFactory.decodeResource(_res, R.drawable.bullet);
        hero_bullet._bitmap = bullet;
        
        /* Setup bunkers */
        Settings s = app.getSettings();
        if (s.getLevel() == 1)
        	n_bunkers = 2;
        else if (s.getLevel() == 2)
        	n_bunkers = 1;
        bunkers = new Sprite[n_bunkers];
        int padding = (_screenWidth - 160) / 3;
        int ax = 0;
        Bitmap bunker = BitmapFactory.decodeResource(_res, R.drawable.bunker);
        for (int i = 0; i < n_bunkers; i++, ax += 80) {
        	ax += padding;
        	bunkers[i] = new Sprite(ax, y - 90, 80);
        	bunkers[i]._bitmap = bunker;
        }
        
        /* Invaders matrix */
        invaders = new InvadersMatrix(_screenHeight, _screenWidth, _res, s.getAttacked());
        
        should_finish = false;
    }

    /**
     * The update method. Update the positions of the balls and the position
     * of the enemy.
     */
    public void update()
    {
    	hero_bullet.update();
    	hero_bullet.watch_the_walls(_screenHeight);
    	if (invaders.gotcha(hero_bullet)) {
    		_stats.enemies++;
    		hero_bullet._valid = false;
    		if (invaders.n_alives == 0)
    			should_finish = true;
    	}
    	invaders.update();
    	if (invaders.got_us(hero)) {
    		should_finish = true;
    		_stats.screwed++;
    	} else if (invaders.got_blocked(bunkers)) {
    		invaders.bullet._valid = false;
    	}
    	if (invaders.get_bottom() + 35 > _screenHeight - 140) {
    		should_finish = true;
    		_stats.screwed++;
    	}
    }
    
    /**
     * Accelerate the user's bat.
     * @param accc The given acceleration (from the accelerometer).
     */
    public void accelerateX(float accx)
    {
    	if (accx > 0 && accx < noise && !last)
    		accx = -1;
    	else if (accx < 0 && accx > -noise && last)
    		accx = 1;
    	if (accx > 0) {
    		hero._posx -= speed;
    		last = true;
    	} else if (accx < 0) {
    		hero._posx += speed;
    		last = false;
    	}
    }
    
    /**
     * The hero attempts to shoot. Only one bullet from the hero can be
     * displayed at the time, like the original game. 
     */
    public void bangBang()
    {
    	if (!hero_bullet._valid) {
	    	hero_bullet._posx = hero._posx + (hero._size / 2) - 2;
	    	hero_bullet._posy = hero._posy;
	    	hero_bullet._valid = true;
	    	_stats.shots++;
    	}
    }

    /**
     * Draw the scenario.
     */
    public void draw(Canvas canvas, Paint paint)
    {
    	/* Refresh the display */
        canvas.drawRGB(20, 20, 20);
        paint.setARGB(200, 0, 200, 0);
        
        invaders.draw(canvas, paint);
        
        for (int i = 0; i < n_bunkers; i++)
        	canvas.drawBitmap(bunkers[i]._bitmap, bunkers[i]._posx, bunkers[i]._posy, paint);
        
        if (hero_bullet._valid)
        	canvas.drawBitmap(hero_bullet._bitmap, hero_bullet._posx, hero_bullet._posy, paint);
    	canvas.drawBitmap(hero._bitmap, hero._posx, hero._posy, paint);
    }
}
