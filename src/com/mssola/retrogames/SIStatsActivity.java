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

import com.mssola.helpers.Statistics;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * The Activity for the Space Invaders tab.
 */
public class SIStatsActivity extends Activity
{
    /**
     * On create show the Space Invaders History view. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.si_stats);

        Statistics stats = ((RetroGamesApplication) getApplicationContext()).getStatistics();
        TextView txt = (TextView) findViewById(R.id.defended);
        txt.setText(to_s(stats.games - stats.screwed));
        
        txt = (TextView) findViewById(R.id.invaded);
        txt.setText(to_s(stats.screwed));
        
        txt = (TextView) findViewById(R.id.enemies);
        txt.setText(to_s(stats.enemies));
        
        txt = (TextView) findViewById(R.id.bullets);
        txt.setText(to_s(stats.shots));
    }
    
    /**
     * Apparently, Java is a complete mess (not that I'm surprised). Let's 
     * provide some goodness.
     * @param n The integer.
     * @return the integer represented as a string.
     */
    private String to_s(int n)
    {
    	return new Integer(n).toString();
    }
}
