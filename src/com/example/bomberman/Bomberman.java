package com.example.bomberman;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bomberman extends GfxObject {
	public Bomberman(Resources resources, int xPos, int yPos){
		// We should init the bitmap here
		bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.bomberman),50,50,true);
		x = xPos;
		y = yPos;
	}
	public void resetSize(Resources resources,int blockSize){
		bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.bomberman),blockSize,blockSize,true);
	}
}
