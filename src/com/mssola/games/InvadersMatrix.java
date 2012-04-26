

package com.mssola.games;


public class InvadersMatrix
{
	Invader[] _invaders;

	// 11 columns 5 rows
	public InvadersMatrix()
	{
		for (int i = 0, k = 0; i < 5; i++) {
			for (int j = 0; j < 11; j++, k++)
				_invaders[k] = new Invader(0, 0, 0, i);
		}
	}
}
