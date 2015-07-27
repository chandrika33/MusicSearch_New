package com.example.jsondemoapp.helper;

import com.example.jsondemoapp.activity.LyricsDisplayActivity;
import com.example.jsondemoapp.activity.MainActivity;


public class AppMediator {
	
	private static AppMediator appMediator = null;
	private AppMediator(){}
	
	public static AppMediator getInstance() {
		if(appMediator == null) {
			appMediator = new AppMediator();
		}
		return appMediator;
	}
	
	private MainActivity mainActivityContext = null;
	private LyricsDisplayActivity lyricsDisplayActivityContext = null;
	
	public MainActivity getMainActivityContext() {
		return mainActivityContext;
	}

	public void setMainActivityContext(MainActivity mainActivityContext) {
		this.mainActivityContext = mainActivityContext;
	}

	public LyricsDisplayActivity getLyricsDisplayActivityContext() {
		return lyricsDisplayActivityContext;
	}

	public void setLyricsDisplayActivityContext(
			LyricsDisplayActivity lyricsDisplayActivityContext) {
		this.lyricsDisplayActivityContext = lyricsDisplayActivityContext;
	}

}
