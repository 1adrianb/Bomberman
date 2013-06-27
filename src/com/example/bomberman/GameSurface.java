package com.example.bomberman;

import android.content.Context;
import android.util.Log;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface  extends SurfaceView implements SurfaceHolder.Callback{
	GameEngine gameEngine;
	SurfaceHolder surfaceHolder;
	Context context;
	private GameThread gameThread;
	
	public GameSurface(Context context, AttributeSet attrs, int defStyle){
		super(context,attrs,defStyle);
		this.context = context;
		InitView();
	}
	
	public GameSurface(Context context, AttributeSet attrs){
		super(context,attrs);
		this.context = context;
		InitView();
	}
	
	void InitView(){
		SurfaceHolder surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		gameEngine = new GameEngine();
		gameEngine.Init(context);
		gameThread = new GameThread(surfaceHolder, context, new Handler(), gameEngine);
		setFocusable(true);
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceHolder){
		boolean retry = true;
		gameThread.state = GameThread.PAUSED;
		while (retry) {
			try{
				gameThread.join();
				retry = false;
			}catch(InterruptedException e){
			}		
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
		gameEngine.setSurfaceDimensions(width, height);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if(gameThread.state == GameThread.PAUSED){
			gameThread = new GameThread(getHolder(), context, new Handler(), gameEngine);
			gameThread.start();
		}else {
			gameThread.start();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent){
		final int action = motionEvent.getAction();
		int xEvent=(int)motionEvent.getX();
		int yEvent=(int)motionEvent.getY();
		if(isInside(xEvent,yEvent, 2*gameEngine.getButtonSize()-50, gameEngine.getButtonSize()-20)){
			//Log.e("My activity", "Up");
			gameEngine.bomberman.direction=0;
			//move up
		}
		if(isInside(xEvent,yEvent,0, gameEngine.getButtonSize()-20)){
			//Log.e("My activity", "Down");
			gameEngine.bomberman.direction=1;
			//move down
		}
		if(isInside(xEvent,yEvent,gameEngine.getButtonSize()-30, 10)){
			//Log.e("My activity", "Left");
			gameEngine.bomberman.direction=2;
			//move Left
		}
		if(isInside(xEvent,yEvent,gameEngine.getButtonSize()-30,2*gameEngine.getButtonSize()-45)){
			//Log.e("My activity", "Right");
			gameEngine.bomberman.direction=3;
			//move Right
		}
		return false;	
	}
	
	public boolean isInside(int xEvent, int yEvent,int xBitmap, int yBitmap){
		if (xEvent >= xBitmap && xEvent < (xBitmap + gameEngine.getButtonSize())
                && yEvent >= yBitmap && yEvent < (yBitmap + gameEngine.getButtonSize()))
			return true;
		else return false;
	}
}
