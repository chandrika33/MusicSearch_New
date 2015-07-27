package com.example.jsondemoapp.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import android.util.Log;

public class Results implements Serializable {
	
	private String artistName;
	private String trackName;
	@SerializedName("artworkUrl100")
	private String previewUrl;
	
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	
	
	
	
}