package com.rajesh.blazeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.rajesh.blazeplayer.activities.AlbumsActivity;
import com.rajesh.blazeplayer.activities.ArtistActivity;
import com.rajesh.blazeplayer.activities.SongsActivity;


public class HomeScreen extends ActionBarActivity implements View.OnClickListener
{

    Button btn_Album,btn_Artist,btn_Playlist,btn_Songs,btn_Genre;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getString(R.string.app_name));

        btn_Album = (Button)findViewById(R.id.button_album);
        btn_Artist = (Button)findViewById(R.id.button_artist);
        btn_Playlist = (Button)findViewById(R.id.button_playlist);
        btn_Songs = (Button)findViewById(R.id.button_songs);
        btn_Genre = (Button)findViewById(R.id.button_genre);

        btn_Album.setOnClickListener(this);
        btn_Artist.setOnClickListener(this);
        btn_Playlist.setOnClickListener(this);
        btn_Songs.setOnClickListener(this);
        btn_Genre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int viewId = v.getId();
        switch (viewId)
        {
            case R.id.button_album:

                startActivity(new Intent(HomeScreen.this, AlbumsActivity.class));
                break;

            case R.id.button_artist:

                startActivity(new Intent(HomeScreen.this, ArtistActivity.class));
                break;

            case R.id.button_playlist:

                break;

            case R.id.button_songs:

                startActivity(new Intent(HomeScreen.this, SongsActivity.class));

                break;

            case R.id.button_genre:

                break;
        }

    }
}
