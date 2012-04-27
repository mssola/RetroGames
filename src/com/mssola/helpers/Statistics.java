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
 * This class provides the access to the games statistics. Note that all
 * attributes are public. Yes, exactly like good old struct thingies as in C.
 */
public class Statistics
{
	/* Statistics from the Pong game */
	
	/**
	 * How many matches the user has winned.
	 */
	public int winned;
	
	/**
	 * How many matches the user has lost.
	 */
	public int lost;
	
	/**
	 * How many goals the user has scored.
	 */
	public int scores;
	
	/**
	 * How many goals the enemy has scored.
	 */
	public int escores;
	
	/**
	 * How many goals the user has scored in its last match.
	 */
	public int last_scores;
	
	/**
	 * How many goals the user has scored in its last match.
	 */
	public int last_escores;
	
	/* Statistics from the Space Invaders game */
	
	/**
	 * How many games the user defended us from the great villains of the space.
	 */
	public int games;
	
	/**
	 * Sooo, how many times the invaders kicked our ass.
	 */
	public int screwed;
	
	/**
	 * How many enemies the user has killed.
	 */
	public int enemies;
	
	/**
	 * How many bullets has the user shot.
	 */
	public int shots;
	
	/**
	 * Constructor.
	 */
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
