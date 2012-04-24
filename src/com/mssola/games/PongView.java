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

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PongView extends SurfaceView  implements SurfaceHolder.Callback
{
    private PongThread _thread;

    public PongView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    	//So we can listen for events...
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true); 

        //and instantiate the thread
        _thread = new PongThread(holder, context, new Handler());
    }  

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg)
    {
        return _thread.getGameState().keyPressed(keyCode, msg);
    }

    //Implemented as part of the SurfaceHolder.Callback interface
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
    {
		//Mandatory, just swallowing it for this example
    }

    //Implemented as part of the SurfaceHolder.Callback interface
	public void surfaceCreated(SurfaceHolder holder)
    {
		_thread.start();
    }

    //Implemented as part of the SurfaceHolder.Callback interface
	public void surfaceDestroyed(SurfaceHolder holder)
    {
        _thread.stop();
    }
}
