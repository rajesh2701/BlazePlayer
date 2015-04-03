package com.rajesh.blazeplayer.activities;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.rajesh.blazeplayer.R;
import com.rajesh.blazeplayer.adapter.SongsAdapter;
import com.rajesh.blazeplayer.model.Song;
import com.rajesh.blazeplayer.services.MusicController;
import com.rajesh.blazeplayer.services.MusicplayerService;

import java.util.ArrayList;

/**
 * Created by Rajesh on 3/28/2015.
 */
//changes for git

public class SongsActivity extends ActionBarActivity
{
    ArrayList<Song> songsList;
    ListView listSongs;


    MusicplayerService musicplayerService;
    private boolean bind = false;
    MusicController musicController ;

    Intent playIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Songs");

        songsList = new ArrayList<Song>();

        listSongs = (ListView)findViewById(R.id.songsList);
        listSongs.setFastScrollEnabled(true);

        getSongsList();

        SongsAdapter songsAdapter = new SongsAdapter(SongsActivity.this,songsList);
        listSongs.setAdapter(songsAdapter);

    }

    public void songPicked(View view)
    {
        String number = view.getTag().toString();
        Toast.makeText(SongsActivity.this, number, Toast.LENGTH_SHORT).show();
    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            MusicplayerService.MusicBinder binder = (MusicplayerService.MusicBinder)service;
            musicplayerService = binder.getService();
            musicplayerService.setSongsList(songsList);
            bind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
             bind = false;
        }
    };


    public ArrayList<Song> getSongsList()
    {
        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor musicCursor = contentResolver.query(musicUri,null,null,null, MediaStore.Audio.Media.TITLE+" ASC");

        if(musicCursor != null && musicCursor.moveToFirst())
        {
            int idColoumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int songNameColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtistColoumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do
            {
                long songId = musicCursor.getLong(idColoumn);
                String songName = musicCursor.getString(songNameColumn);
                String songArtist  = musicCursor.getString(songArtistColoumn);

                songsList.add( new Song(songId,songName,songArtist));
            }
            while (musicCursor.moveToNext());
        }

        return songsList;

    }
}
