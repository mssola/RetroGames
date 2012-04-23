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
 * This class provides the access to all the configurable parameters
 * of the application. 
 */
public class Settings
{
    /**
     * Stores the level of difficulty.
     */
    private int level;

    /**
     * How many balls in the Pong game will be used.
     */
    private int balls;

    /**
     * Stores if the balls can suddenly change their directions in
     * the Pong game.
     */
    private boolean sudden;

    /**
     * The invaders can shoot the user in the Space Invaders game.
     */
    private boolean attacked;

    /**
     * The user controls the invader team in the Space Invaders game.
     */
    private boolean invader;

    /**
     * Constructor. 
     */
    public Settings()
    {
        this.level = 1;
        this.balls = 1;
        this.sudden = false;
        this.attacked = false;
        this.invader = false;
    }
    
    /**
     * @return the difficulty level.
     */
    public int getLevel()
    {
        return this.level;
    }

    /**
     * @param level The new level of difficulty.
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return the balls being used.
     */
    public int getBalls()
    {
        return this.balls;
    }

    /**
     * @param balls the number of balls to be used.
     */
    public void setBalls(int balls)
    {
        this.balls = balls;
    }

    /**
     * @return if balls can change its directions all the sudden.
     */
    public boolean getSudden()
    {
        return this.sudden;
    }

    /**
     * @param sudden set if balls can change its directions all the sudden.
     */
    public void setSudden(boolean sudden)
    {
        this.sudden = sudden;
    }

    /**
     * @return if the invaders can shoot to the user.
     */
    public boolean getAttacked()
    {
        return this.attacked;
    }

    /**
     * @param attacked set if the invaders can shoot to the user.
     */
    public void setAttacked(boolean attacked)
    {
        this.attacked = attacked;
    }

    /**
     * @return if the user controls the invader team.
     */
    public boolean getInvader()
    {
        return this.invader;
    }

    /**
     * @param invader set if the user controls the invader team.
     */
    public void setInvader(boolean invader)
    {
        this.invader = invader;
    }
}
