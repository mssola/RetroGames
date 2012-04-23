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

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;


/**
 * The Activity for the Pong History tab.
 */
public class PongHistoryActivity extends Activity
{
	/**
	 * On create show the Pong History view. 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);        
        TextView view = new TextView(this);
        String text = getResources().getString(R.string.first_pong);
        text += getResources().getString(R.string.eol);
        text += getResources().getString(R.string.second_pong);
        text += getResources().getString(R.string.eol);
        text += getResources().getString(R.string.third_pong);
        text += getResources().getString(R.string.eol);
        text += getResources().getString(R.string.last_pong);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(Html.fromHtml(text));
        setContentView(view);
    }
}
