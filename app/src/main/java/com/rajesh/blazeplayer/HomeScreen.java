package com.rajesh.blazeplayer;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rajesh.blazeplayer.adapter.SongsAdapter;
import com.rajesh.blazeplayer.model.Song;

import java.util.ArrayList;


public class HomeScreen extends ActionBarActivity
{

    ArrayList<Song> songsList;
    ListView listSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        songsList = new ArrayList<Song>();

        listSongs = (ListView)findViewById(R.id.songsList);

        getSongsList();

        SongsAdapter songsAdapter = new SongsAdapter(HomeScreen.this,songsList);
        listSongs.setAdapter(songsAdapter);

    }


    public ArrayList<Song> getSongsList()
    {
        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor musicCursor = contentResolver.query(musicUri,null,null,null,"ASC");

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
