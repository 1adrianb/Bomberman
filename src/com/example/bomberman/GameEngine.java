package com.example.bomberman;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;

public class GameEngine  {
	public float screenWidth;
	public float screenHight;
	private Paint blackPaint;
	private Paint textPaint, invincibleWallPaint, roadPaint;
	private String currentTimeString;
	private int[][] matrix = new int[13][20];
	
	public void Init(Context context){

		setSurfaceDimensions(240,160);
		
		blackPaint = new Paint();
		blackPaint.setColor(Color.BLACK);
		blackPaint.setStyle(Style.FILL);
		
		invincibleWallPaint = new Paint();
		invincibleWallPaint.setColor(Color.RED);
		invincibleWallPaint.setStyle(Style.STROKE);
		
		roadPaint = new Paint();
		roadPaint.setColor(Color.BLUE);
		roadPaint.setStyle(Style.STROKE);

		textPaint = new Paint();
		textPaint.setColor(Color.LTGRAY);
		textPaint.setTextSize(40);
		
		for(int i=0;i<13;i++){
			matrix[i][0] = 1;
			matrix[i][19] = 1;
		}
		for(int i=0;i<19;i++){
			matrix[0][i] = 1;
			matrix[12][i] = 1;
		}

	}

	public void setSurfaceDimensions(int width, int height) {
		screenWidth = width;
		screenHight = height;
	}
	
	@SuppressLint("SimpleDateFormat")
	public void Update(){
		currentTimeString = new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
	public void Draw(Canvas canvas){
		canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),blackPaint);
		int sizeOfBlock = (int) (screenWidth/13);
		int ni = (int) (screenWidth/sizeOfBlock);
		int nj = (int) (screenHight/sizeOfBlock);
		
		int startX = (int) (screenWidth - (sizeOfBlock*ni));
		int startY = (int) (screenHight - (sizeOfBlock*nj));
		
		for(int i=0;i<ni;i++){
			for(int j=0;j<nj;j++){
				if(matrix[i][j]==1){
					canvas.drawRect((i*sizeOfBlock),(j*sizeOfBlock), (i+1)*sizeOfBlock, (j+1)*sizeOfBlock, invincibleWallPaint);
				} else {
					canvas.drawRect((i*sizeOfBlock),(j*sizeOfBlock), (i+1)*sizeOfBlock, (j+1)*sizeOfBlock, roadPaint);
				}
			}
		}
	}
}
