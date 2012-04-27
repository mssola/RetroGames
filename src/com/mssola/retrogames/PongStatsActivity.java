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
public class PongStatsActivity extends Activity
{
    /**
     * On create show the Space Invaders History view. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pong_stats);

        Statistics stats = ((RetroGamesApplication) getApplicationContext()).getStatistics();
        TextView txt = (TextView) findViewById(R.id.lala);
        txt.setText(to_s(stats.winned + stats.lost));
        
        txt = (TextView) findViewById(R.id.won);
        txt.setText(to_s(stats.winned));
        
        txt = (TextView) findViewById(R.id.scores);
        txt.setText(to_s(stats.scores));
        
        txt = (TextView) findViewById(R.id.escores);
        txt.setText(to_s(stats.escores));
        
        txt = (TextView) findViewById(R.id.last_scores);
        txt.setText(to_s(stats.last_scores));
        
        txt = (TextView) findViewById(R.id.last_escores);
        txt.setText(to_s(stats.last_escores));
    }
    
    private String to_s(int n)
    {
    	return new Integer(n).toString();
    }
}