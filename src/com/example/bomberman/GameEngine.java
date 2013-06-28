package com.example.bomberman;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class GameEngine  {
	public float screenWidth;
	public float screenHight;
	private Paint blackPaint;
	private Paint textPaint, invincibleWallPaint, roadPaint, buttonPaint;
	private int[][] matrix = new int[13][30];
	public Bomberman bomberman;
	private Resources resources;
	private int sizeOfBlock;
	private int buttonSize;
	private Bitmap bitmapInvincibleWall=null, bitmapRoad=null;
	private Context context;
	
	private Bitmap up=null,down=null,left=null,right=null,bombButton=null;
	
	public void Init(Context context){

		//setSurfaceDimensions(240,160);
		this.context = context;
		resources = context.getResources();
		
		blackPaint = new Paint();
		blackPaint.setColor(Color.BLACK);
		blackPaint.setStyle(Style.FILL);

		textPaint = new Paint();
		textPaint.setColor(Color.LTGRAY);
		textPaint.setTextSize(40);
		
		buttonPaint=new Paint();
		buttonPaint.setAlpha(80);
		
		bomberman = new Bomberman(resources, 10, 10);
		bomberman.setDirection(5);
		bomberman.setSpeed(0);
		
		
		
		for(int i=0;i<13;i++){
			matrix[i][0] = 1;
			matrix[i][19] = 1;
		}
		for(int i=2;i<10;i++){
			matrix[i][7] = 1;
		}
		
		for(int i=0;i<19;i++){
			matrix[0][i] = 1;
			matrix[12][i] = 1;
		}
	}
	
	public int[][] getMap(){
		return matrix;
	}
	
	public int getBlockSize(){
		return sizeOfBlock;
	}
	public int getButtonSize(){
		return buttonSize;
	}
	
	public void setSurfaceDimensions(int width, int height) {
		screenWidth = width;
		screenHight = height;
	}
	
	@SuppressLint("SimpleDateFormat")
	public void Update(){
		bomberman.move();
		bomberman.direction=5;
	}
	
	public void Draw(Canvas canvas){
		canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),blackPaint);
		
		sizeOfBlock =  (int) (screenWidth/13);
		buttonSize=2*sizeOfBlock;
		if(bitmapInvincibleWall==null)
			bitmapInvincibleWall = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.invincible_wall),sizeOfBlock,sizeOfBlock,true);
		if(bitmapRoad==null){
			bitmapRoad = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.road),sizeOfBlock,sizeOfBlock,true);
			bomberman.resetSize(resources,sizeOfBlock);
		}
		
		if(up==null){
			up = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.right),buttonSize,buttonSize,true);
			down = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.left),buttonSize,buttonSize,true);
			left = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.up),buttonSize,buttonSize,true);
			right = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.down),buttonSize,buttonSize,true);
			bombButton = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,com.example.bomberman.R.drawable.b),buttonSize,buttonSize,true);
		}
		int ni =  (int) (screenWidth/sizeOfBlock);
		int nj = (int) (screenHight/sizeOfBlock);
		for(int i=0;i<ni;i++){
			for(int j=0;j<nj;j++){
				if(matrix[i][j]==1){
					if(bitmapInvincibleWall!=null)
					canvas.drawBitmap(bitmapInvincibleWall,(i*sizeOfBlock),(j*sizeOfBlock),textPaint);
					//canvas.drawRect((i*sizeOfBlock),(j*sizeOfBlock), (i+1)*sizeOfBlock, (j+1)*sizeOfBlock, invincibleWallPaint);
				} else {
					canvas.drawBitmap(bitmapRoad,(i*sizeOfBlock),(j*sizeOfBlock),textPaint);
					//canvas.drawRect((i*sizeOfBlock),(j*sizeOfBlock), (i+1)*sizeOfBlock, (j+1)*sizeOfBlock, roadPaint);
				}
			}
		}
		bomberman.draw(canvas,sizeOfBlock, textPaint);
		
		canvas.drawBitmap(up, 2*buttonSize-55, buttonSize-20, buttonPaint);
		canvas.drawBitmap(down, 0, buttonSize-20, buttonPaint);
		canvas.drawBitmap(left, buttonSize-30, 8, buttonPaint);
		canvas.drawBitmap(right, buttonSize-30,2*buttonSize-45, buttonPaint);
		canvas.drawBitmap(bombButton, buttonSize-30,screenHight-2*buttonSize-45, buttonPaint);	
	}
}
