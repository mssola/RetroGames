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
import android.app.Application;


public class RetroGamesApplication extends Application
{
	private int level;
	private int balls;
	private boolean sudden;
	private boolean attacked;
	private boolean invader;
	
	public RetroGamesApplication() 
	{
		super();
		this.level = 1;
		this.balls = 1;
		this.sudden = false;
		this.attacked = false;
		this.invader = false;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getBalls()
	{
		return this.balls;
	}
	
	public void setBalls(int balls)
	{
		this.balls = balls;
	}

	public boolean getSudden()
	{
		return this.sudden;
	}
	
	public void setSudden(boolean sudden)
	{
		this.sudden = sudden;
	}
	
	public boolean getAttacked()
	{
		return this.attacked;
	}
	
	public void setAttacked(boolean attacked)
	{
		this.attacked = attacked;
	}
	
	public boolean getInvader()
	{
		return this.invader;
	}
	
	public void setInvader(boolean invader)
	{
		this.invader = invader;
	}
}
