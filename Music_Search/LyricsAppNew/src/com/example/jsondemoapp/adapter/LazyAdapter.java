package com.example.jsondemoapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jsondemoapp.dto.Results;
import com.example.jsondemoapp.helper.ImageLoader;
import com.example.lyricsappnew.R;

/**
 * Adapter class to set the values in list view
 */
public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<Results> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<Results> data) {
        activity = a;
        this.data=data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        // Setting all values in listview
        title.setText(data.get(position).getTrackName());
        artist.setText(data.get(position).getArtistName());
    	imageLoader.DisplayImage(data.get(position).getPreviewUrl() , thumb_image);
        return vi;
    }
}