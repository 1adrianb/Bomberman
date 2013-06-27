package com.example.bomberman;

import android.content.res.Resources;

public class Bomberman extends GfxObject {
	public Bomberman(Resources resources, int xPos, int yPos){
		// We should init the bitmap here
		x = xPos;
		y = yPos;
	}
}
