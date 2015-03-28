package com.rajesh.blazeplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rajesh.blazeplayer.R;
import com.rajesh.blazeplayer.model.Album;
import com.rajesh.blazeplayer.model.Song;

import java.util.ArrayList;

/**
 * Created by Rajesh on 3/28/2015.
 */
public class AlbumsAdapter extends BaseAdapter
{

    ArrayList<Album> songList;
    LayoutInflater songInflater;

    public AlbumsAdapter(Context context, ArrayList<Album> songsList)
    {
        this.songList = songsList;
        songInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return songList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout songLayout = (LinearLayout)songInflater.inflate(R.layout.albumview,parent,false);

        TextView tv_SongName = (TextView)songLayout.findViewById(R.id.tv_Songname);
        TextView tv_SongArtist = (TextView)songLayout.findViewById(R.id.tv_Songartist);

        Album song = songList.get(position);

        tv_SongName.setText(song.getAlbumName());
        tv_SongArtist.setText(song.getAlbumArtist());

        songLayout.setTag(position);

        return songLayout;
    }
}
