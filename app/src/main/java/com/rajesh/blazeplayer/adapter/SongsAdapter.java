package com.rajesh.blazeplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rajesh.blazeplayer.R;
import com.rajesh.blazeplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajesh on 3/28/2015.
 */
public class SongsAdapter extends BaseAdapter
{

    ArrayList<Song> songList;
    LayoutInflater songInflater;

    public SongsAdapter(Context context,ArrayList<Song> songsList)
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
        LinearLayout songLayout = (LinearLayout)songInflater.inflate(R.layout.songview,parent,false);

        TextView tv_SongName = (TextView)songLayout.findViewById(R.id.tv_Songname);
        TextView tv_SongArtist = (TextView)songLayout.findViewById(R.id.tv_Songartist);

        Song song = songList.get(position);

        tv_SongName.setText(song.getSongName());
        tv_SongArtist.setText(song.getSongArtist());

        songLayout.setTag(position);

        return songLayout;
    }

   /* private class ViewHolder
    {
        TextView tv_SongName,tv_SongArtist;
    }*/
}
