package com.example.jsondemoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.jsondemoapp.async.LyricsSearchAsync;
import com.example.jsondemoapp.dto.SongsDTO;
import com.example.jsondemoapp.helper.AppMediator;
import com.example.lyricsappnew.R;

public class MainActivity extends Activity {
	
	static String TAG = MainActivity.class.getCanonicalName();
	public SongsDTO songsDTO = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AppMediator.getInstance().setMainActivityContext(this);
		
	}
	
	public void searchTerm(View view) {
		try {
			EditText et_term = (EditText) findViewById(R.id.et_term);
			if(validateInputFields(et_term)) {
				findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
				sendServerRequest(et_term);
			}
		} catch(Exception e) {
			Log.e(TAG , "Exception in searchTerm -->"+e);
		}
	}
	
	public void sendServerRequest(final EditText et_term) {
		try {
			String params[] = {"songSearch" , et_term.getText().toString().trim()};
			new LyricsSearchAsync().execute(params);
		} catch(Exception e) {
			Log.e(TAG , "Exception in sendServerRequest -->"+e);
		}
	}
	
	
	public boolean validateInputFields(final EditText et_term) {
		boolean flag = true;
		try {
			if(et_term.getText().toString() == null || ( et_term.getText().toString() != null && et_term.getText().toString().trim().equals(""))) {
				et_term.requestFocus();
				et_term.setError(getString(R.string.valid_songname));
				return false;
			}
		} catch(Exception e) {
			Log.e(TAG, "Exception in validateInputFields : "+e);
		}
		return flag;
	}
	
	
	public void displaySongsScreen() {
		try {
			findViewById(R.id.progressbar).setVisibility(View.GONE);
			Intent intent = new Intent(AppMediator.getInstance().getMainActivityContext() , SongsDisplayActivity.class);
			startActivity(intent);
		} catch(Exception e) {
			Log.e(TAG, "Exception in displaySongsScreen : "+e);
		}
	}




}
