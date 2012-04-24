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

import com.mssola.helpers.Settings;
import com.mssola.helpers.Statistics;
import android.app.Application;


/**
 * The application for this Android app. It extends the android.app.Application
 * class in order to implement global settings and statistics.
 */
public class RetroGamesApplication extends Application
{
    /**
     * The singleton Settings instance. 
     */
    private Settings settings;
    
    /**
     * The singleton Statistics instance.
     */
    private Statistics stats;

    /**
     * Constructor.
     * Sets the default values for the global settings.
     */
    public RetroGamesApplication()
    {
        super();
        this.settings = new Settings();
        this.stats = new Statistics();
    }

    /**
     * @return The Settings singleton instance. 
     */
    public Settings getSettings()
    {
    	return this.settings;
    }

    /**
     * @return The Statistics singleton instance. 
     */
    public Statistics getStatistics()
    {
    	return this.stats;
    }
}
