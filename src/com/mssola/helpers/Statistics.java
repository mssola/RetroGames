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


package com.mssola.helpers;


/**
 * This class provides the access to the games statistics.
 */
public class Statistics
{
	public int winned, lost;
	public int scores, escores;
	public int last_scores, last_escores;
	
	public int games, screwed;
	public int enemies, shots;
	
	public Statistics()
	{
		winned = 0;
		lost = 0;
		scores = 0;
		escores = 0;
		last_scores = 0;
		last_escores = 0;
		
		games = 0;
		screwed = 0;
		enemies = 0;
		shots = 0;
	}
}
