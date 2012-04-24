

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

    public PongView(Context context, AttributeSet attrs) {
        super(context, attrs);

    	//So we can listen for events...
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true); 

        //and instantiate the thread
        _thread = new PongThread(holder, context, new Handler());
    }  

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        return _thread.getGameState().keyPressed(keyCode, msg);
    }

    //Implemented as part of the SurfaceHolder.Callback interface
//	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//Mandatory, just swallowing it for this example

	}

    //Implemented as part of the SurfaceHolder.Callback interface
//	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		_thread.start();
	}

    //Implemented as part of the SurfaceHolder.Callback interface
//	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
        _thread.stop();
	}
}
