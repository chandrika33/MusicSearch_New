package com.example.jsondemoapp.dto;

import java.io.Serializable;

public class SongsDTO implements Serializable {
	
	private int resultCount;
	private Results[] results;
	public int getResultCount() {
		return resultCount;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	public Results[] getResults() {
		return results;
	}
	public void setResults(Results[] results) {
		this.results = results;
	}
	
}


