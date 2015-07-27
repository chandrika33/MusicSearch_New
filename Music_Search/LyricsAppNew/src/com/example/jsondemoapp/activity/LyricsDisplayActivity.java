package com.example.jsondemoapp.activity;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jsondemoapp.async.LyricsSearchAsync;
import com.example.jsondemoapp.dto.LyricsDTO;
import com.example.jsondemoapp.helper.AppMediator;
import com.example.lyricsappnew.R;

public class LyricsDisplayActivity extends Activity {
	
	public LyricsDTO lyricsDto = null;
	static String TAG = LyricsDisplayActivity.class.getCanonicalName();
	String strPreviewUrl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		AppMediator.getInstance().setLyricsDisplayActivityContext(this);
		setContentView(R.layout.activity_lyrics);
		
		if(getIntent().getStringExtra("trackName") != null && getIntent().getStringExtra("artistName") != null) {
			strPreviewUrl = getIntent().getStringExtra("previewUrl");
			sendServerRequest(getIntent().getStringExtra("trackName") , getIntent().getStringExtra("artistName"));
		}
	}
	
	public void sendServerRequest(String strTrackName , String strArtistName) {
		try {
			findViewById(R.id.ll_progressbar).setVisibility(View.VISIBLE);
			String params[] = {"lyricsSearch" , strArtistName , strTrackName};
			new LyricsSearchAsync().execute(params);
		} catch(Exception e) {
			Log.e(TAG , "Exception in sendServerRequest -->"+e);
		}
	}
	
	public void displayLyrics() {
		try {
			Log.i(TAG, "inside displayLyrics lyrics -->"+lyricsDto.getLyrics());
			findViewById(R.id.ll_progressbar).setVisibility(View.GONE);
			findViewById(R.id.root_layout).setVisibility(View.VISIBLE);
			
			if(lyricsDto.getLyrics().equalsIgnoreCase("Not found")) {
				((TextView)findViewById(R.id.txt_lyrics)).setText("Sorry! No Lyrics found");
			} else {
				((TextView)findViewById(R.id.txt_lyrics)).setText(lyricsDto.getLyrics());
			}
			((TextView)findViewById(R.id.txt_song)).setText(lyricsDto.getSong());
			((TextView)findViewById(R.id.txt_artist)).setText(lyricsDto.getArtist());
			String imagePath = Environment.getExternalStorageDirectory()+"/LyricsApp";
			String filename=String.valueOf(strPreviewUrl.hashCode());
			File file = new File(imagePath , filename);
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			((ImageView)findViewById(R.id.iv_album)).setImageBitmap(bitmap);
		} catch(Exception e) {
			Log.e(TAG , "Exception in displayLyrics -->"+e);
		}
	}

}
