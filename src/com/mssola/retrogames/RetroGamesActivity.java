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

import android.os.Bundle;


/**
 * The main activity.
 */
public class RetroGamesActivity extends RGTabActivity
{
    /**
     * onCreate setup the main tabs.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /* Add the four main tabs */
        addTab(R.string.games_tab, GamesActivity.class);
        addTab(R.string.stats_tab, StatisticsActivity.class);
        addTab(R.string.hist_tab, HistoryActivity.class);
        addTab(R.string.settings_tab, SettingsActivity.class);
    }
}
