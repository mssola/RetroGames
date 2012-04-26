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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * The view for the Pong game.
 */
public class PongView extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener
{
    private PongThread _thread;
    private Context _ctx;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    /**
     * Constructor.
     */
    public PongView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        
        /* Take care of the accelerometer instance */
        _ctx = context;
        mSensorManager = (SensorManager) _ctx.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    	/* So we can listen for events */
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true); 
    }

    /* Implemented as part of the SurfaceHolder.Callback interface */
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
    {
		/* There's nothing to do here */
    }

    /* When the surface gets created, launch a brand new Pong thread. */
	public void surfaceCreated(SurfaceHolder holder)
    {
		_thread = new PongThread(holder, _ctx, new Handler());
		_thread.setRunning(true);
		_thread.start();
    }

    /* When the surface gets destroyed, join the thread. */
	public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
                /* If we reach here, it means that we're fucked */
            }
        }
    }

	/* On resume register the accelerometer */
	protected void onResume()
	{
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		_thread.start();
	}

	/* On pause unregister the accelerometer */
	protected void onPause()
	{
		mSensorManager.unregisterListener(this);
		_thread.stop();
	}
	
	/* Implemented as part of the SensorEventListener interface */
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		/* There's nothing to do here */
	}
	
	/* On sensor changed, accelerate the bat. */
	public void onSensorChanged(SensorEvent sensorEvent)
	{
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        	_thread.getGameState().accelerateX(sensorEvent.values[0]);
        }
	}
}
