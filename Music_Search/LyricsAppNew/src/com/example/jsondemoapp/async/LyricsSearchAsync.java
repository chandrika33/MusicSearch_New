package com.example.jsondemoapp.async;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jsondemoapp.conf.APPCONSTANTS;
import com.example.jsondemoapp.dto.LyricsDTO;
import com.example.jsondemoapp.dto.SongsDTO;
import com.example.jsondemoapp.helper.AppMediator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * sending request of url and getting the response using JSON.
 */
public class LyricsSearchAsync extends AsyncTask<String, String, String> {
	
	private String TAG = LyricsSearchAsync.class.getCanonicalName();
	@Override
	protected String doInBackground(String... params) {
		String strRespMsg = null;
		try {
			if(params[0].equalsIgnoreCase("songSearch")) {
				String paramsEncoded = URLEncoder.encode(params[1], "utf-8");
				String url = APPCONSTANTS.TERM_SEARCH_URL+"?term="+paramsEncoded;
				Log.i(TAG , "Songs Search URL -->"+url);
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
				nameValuePairs.add(new BasicNameValuePair("term", params[0].replace(" ", "+")));
				HttpClient httpClient = new DefaultHttpClient();
				 HttpPost  httppost = new HttpPost(url);

				// Execute HTTP Post Request
			    HttpResponse response = httpClient.execute(httppost);
			    HttpEntity entity = response.getEntity();
			    if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
			    	 InputStream isobj = entity.getContent();
					 String strData = convertServerInputStreamToString(isobj);
					 Log.e(TAG, "songSearch Response Data  ------> " + strData);
					 
					 Gson gson = new GsonBuilder().create();
					 AppMediator.getInstance().getMainActivityContext().songsDTO = gson.fromJson(strData, SongsDTO.class);
					 Log.e(TAG, "SongsDTO Response Data  ------> " + AppMediator.getInstance().getMainActivityContext().songsDTO );
					 strRespMsg = params[0];
						 
			    }
			    
			} else if(params[0].equalsIgnoreCase("lyricsSearch")) {
				String paramsEncoded = URLEncoder.encode(params[1], "utf-8");
				String params2Encoded = URLEncoder.encode(params[2], "utf-8");
				String url = APPCONSTANTS.LYRICS_SEARCH_URL+"?artist="+paramsEncoded+"&song="+params2Encoded+"&fmt=json";
				Log.i(TAG , "URL -->"+url);
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
				nameValuePairs.add(new BasicNameValuePair("artist", params[0].replace(" ", "+")));
				nameValuePairs.add(new BasicNameValuePair("song", params[1]));
				nameValuePairs.add(new BasicNameValuePair("fmt", "json"));
				HttpClient httpClient = new DefaultHttpClient();
				 HttpPost  httppost = new HttpPost(url);

				// Execute HTTP Post Request
			    HttpResponse response = httpClient.execute(httppost);
			    HttpEntity entity = response.getEntity();
			    if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
			    	 InputStream isobj = entity.getContent();
					 String strData = convertServerInputStreamToString(isobj);
					 strData = strData.substring(strData.indexOf("=")+1).trim();
					 Log.e(TAG, "userLogin  ------> " + strData);
					 
					 Gson gson = new GsonBuilder().create();
					 AppMediator.getInstance().getLyricsDisplayActivityContext().lyricsDto = gson.fromJson(strData, LyricsDTO.class);
					 strRespMsg = params[0];
						 
			    }
			    
			}
				
		} catch (Exception e) {
			Log.e(TAG, "Exception in doInBackground --- " + e);
		}
		return strRespMsg;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			Log.i(TAG, "inside onPostExecute result -->"+result);
			if(result != null) {
				
				if(result.equalsIgnoreCase("songSearch")) {
					AppMediator.getInstance().getMainActivityContext().displaySongsScreen();
					
				} else if(result.equalsIgnoreCase("lyricsSearch")) {
					
					AppMediator.getInstance().getLyricsDisplayActivityContext().displayLyrics();
					
				}
			}
			
		} catch (Exception e) {
			Log.e(TAG, "Exception in onPostExecute ------>"+e);
		}
	}
	
	private String convertServerInputStreamToString(InputStream ins) {
	    Scanner sc = null;
	    String inputStreamString = "";
	    try {
	    	sc = new Scanner(ins, "UTF-8");
	       	inputStreamString = sc.useDelimiter("\\A").next();
	       Log.i(TAG, "Data From Server ----------------------> "+inputStreamString.trim());
	       ins.reset();
        }catch(Exception e) {
        	Log.e(TAG, "Exception in convertServerInputStreamToString :: "+e);
        }finally {
	        try {
	       	sc.close();
	       	sc = null;
	        } catch(Exception e) {
	        	Log.i(TAG, "Exception in convertServerInputStreamToString finally:: "+e);
	        }
        }
	    return inputStreamString;
	}
}