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


package com.mssola.retrogames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * The Activity for the Games tab.
 */
public class GamesActivity extends Activity
{
	/**
	 * On create setup two buttons to access to the available games.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);
        
        /* The button to select the Pong game */
        Button bttn1 = (Button) findViewById(R.id.button1);
        bttn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), PongActivity.class);
				startActivity(intent);
			}
		});
        
        /* The button to select the Space Invaders game */
        Button bttn2 = (Button) findViewById(R.id.button2);
        bttn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), SIActivity.class);
				startActivity(intent);
			}
		});
    }
}
