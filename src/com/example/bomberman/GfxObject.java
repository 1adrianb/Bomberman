package com.example.bomberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GfxObject {
	protected Bitmap bitmap; // Will be used for draw a desired image
	protected int x;
	protected int y;
	protected int speed;
	protected int direction;

	// Function is unsafe on move
	// Check if you can do that crazy move first before you call it
	// in BotsHandler or user
	public void move(){
		switch (direction) {
		case 0: // Up
			x++;
			break;
		case 1: // Down
			x--;
			break;
		case 2: // Left
			y--;
			break;
		case 3: // Right
			y++;
			break;
		default: // doNothing
			break;
		}
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void draw(Canvas canvas, int blockSize, Paint paint){
		//Convert cordonates into pixel location
		int xPoz = (int) (x* blockSize);
		int yPoz = (int) (y*blockSize);
		
		canvas.drawBitmap(bitmap, xPoz, yPoz, paint);
		//canvas.drawRect(xPoz,yPoz,xPoz+blockSize,yPoz+blockSize, paint);
	}
}
