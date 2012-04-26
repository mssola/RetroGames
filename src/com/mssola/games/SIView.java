
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


public class SIView extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener
{
    private SIThread _thread;
    private Context _ctx;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    public SIView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        
        _ctx = context;
        mSensorManager = (SensorManager) _ctx.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    	//So we can listen for events...
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true); 
    }  

    //Implemented as part of the SurfaceHolder.Callback interface
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
    {
		//Mandatory, just swallowing it for this example
    }

    //Implemented as part of the SurfaceHolder.Callback interface
	public void surfaceCreated(SurfaceHolder holder)
    {
		_thread = new SIThread(holder, _ctx, new Handler());
		_thread.setRunning(true);
		_thread.start();
    }

    //Implemented as part of the SurfaceHolder.Callback interface
	public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }
    }

	protected void onResume()
	{
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		_thread.start();
	}

	protected void onPause()
	{
		mSensorManager.unregisterListener(this);
		_thread.stop();
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		/* There's nothing to do here */
	}
	
	public void onSensorChanged(SensorEvent sensorEvent)
	{
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        	_thread.getGameState().accelerateX(sensorEvent.values[0]);
        }
	}
}
