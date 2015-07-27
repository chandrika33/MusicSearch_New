package com.example.jsondemoapp.activity;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.jsondemoapp.adapter.LazyAdapter;
import com.example.jsondemoapp.dto.Results;
import com.example.jsondemoapp.dto.SongsDTO;
import com.example.jsondemoapp.helper.AppMediator;
import com.example.lyricsappnew.R;

public class SongsDisplayActivity extends Activity {
	
	ListView list;
    LazyAdapter adapter;
    ArrayList<Results> songsList  = new ArrayList<Results>();
    static String TAG = SongsDisplayActivity.class.getCanonicalName();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_songs);
		list=(ListView)findViewById(R.id.list);
		SongsDTO songsDTO = AppMediator.getInstance().getMainActivityContext().songsDTO;
		songsList = new ArrayList<Results>(Arrays.asList(songsDTO.getResults()));
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);        
        list.setAdapter(adapter);
        
     // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view , int position, long id) {
				Intent intent = new Intent(SongsDisplayActivity.this , LyricsDisplayActivity.class);
				intent.putExtra("trackName", songsList.get(position).getTrackName());
				intent.putExtra("artistName", songsList.get(position).getArtistName());
				intent.putExtra("previewUrl", songsList.get(position).getPreviewUrl());
				startActivity(intent);
			}
		});		
	}

}
