
package com.mssola.retrogames;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SettingsActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Settings tab");
        setContentView(textview);
    }
}
