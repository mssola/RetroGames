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

import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


/**
 * The Activity for the History tab.
 */
public class RGTabActivity extends TabActivity
{
    /**
     * Add a new tab to our tabHost.
     *
     * @param labelId The id of the tab.
     * @param activity The Activity to be set for this tab.
     */
    protected void addTab(int labelId, Class<?> activity)
    {
        Intent intent = new Intent(this, activity);
        TabHost.TabSpec spec = getTabHost().newTabSpec("tab" + labelId);
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);

        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.tab_info);

        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        getTabHost().addTab(spec);
    }
}
