package com.example.bomberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GfxObject {
	protected Bitmap bitmap; // Will be used for draw a desired image
	protected int x;
	protected int y;
	private int direction = 0;
	
	public void setDirection(int direction){
		this.direction = direction;
	}

	// Function is unsafe on move
	// Check if you can do that crazy move first before you call it
	// in BotsHandler or user
	public void move(int currentWidth,int currentHeight, int direction){
		switch (direction) {
		case 0: // Forward
			currentHeight++;
			break;
		case 1: // Backward
			currentHeight--;
			break;
		case 2: // Left
			currentWidth++;
			break;
		case 3: // Right
			currentWidth--;
			break;
		default: // doNothing
			break;
		}
	}
	
	public void draw(Canvas canvas, float x, float y, int blockSize, Paint paint){
		//Convert cordonates into pixel location
		int xPoz = (int) (x* blockSize);
		int yPoz = (int) (y*blockSize);
		
		canvas.drawRect(xPoz,yPoz,xPoz+blockSize,yPoz+blockSize, paint);
	}
}
