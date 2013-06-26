package com.example.bomberman;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private Canvas canvas;
	private long delay = 1000000000L/25;
	private long beforeTime = 0;
	private long afterTime = 0;
	private long timeDiff = 0;
	private long sleepTime;
	private long overSleepTime = 0;
	private long excess = 0;

	public final static int RUNNING = 1;
	public final static int PAUSED = 2;
	private static final int MAX_FRAME_SKIPS = 5;
	int state = RUNNING;
	
	GameEngine gameEngine;
	
	public GameThread(SurfaceHolder surfaceHolder, Context context, Handler handler, GameEngine gameEngine){
		this.surfaceHolder = surfaceHolder;
		this.gameEngine =  gameEngine;
	}
	
	@Override
	public void run(){
		while (state == RUNNING) {
			beforeTime = System.nanoTime();
			
			gameEngine.Update();
			
			canvas = null;
			try{
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					gameEngine.Draw(canvas);
				}
			}finally{
				if(canvas !=null){
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			
			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = ((delay) - timeDiff) - overSleepTime;
			
			if(sleepTime>0){
				try{
					sleep(sleepTime/1000000L);
				}catch(InterruptedException ex){		
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			} else {
				excess -= sleepTime;
				overSleepTime = 0L;
			}
			
			int skips = 0;
			while ((excess > delay) && (skips < MAX_FRAME_SKIPS)) {
				excess -= delay;
				gameEngine.Update();
				skips++;			
			}
		}
	}
}
