package com.rajesh.blazeplayer.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.rajesh.blazeplayer.R;
import com.rajesh.blazeplayer.adapter.AlbumsAdapter;
import com.rajesh.blazeplayer.model.Album;

import java.util.ArrayList;

/**
 * Created by Rajesh on 3/28/2015.
 */

public class AlbumsActivity extends ActionBarActivity
{
    ArrayList<Album> albumList;
    ListView albumListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.album_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Albums");

        albumList = new ArrayList<Album>();
        albumListView = (ListView)findViewById(R.id.albumsList);
        albumListView.setFastScrollEnabled(true);

        getAlbumsList();

        AlbumsAdapter albumsAdapter = new AlbumsAdapter(AlbumsActivity.this,albumList);
        albumListView.setAdapter(albumsAdapter);
    }

    public void albumPicked(View view)
    {

        int position = Integer.parseInt(view.getTag().toString());
       // Toast.makeText(AlbumsActivity.this,albumList.get(position).getAlbumName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AlbumsActivity.this,AlbumSongsActivity.class);
        intent.putExtra("album_name",albumList.get(position).getAlbumName().trim());
        startActivity(intent);
    }


    public ArrayList<Album> getAlbumsList()
    {
        Cursor cursor = getAlbumAlbumcursor();

        if(cursor != null && cursor.moveToFirst())
        {
            int albumid = cursor.getColumnIndex(MediaStore.Audio.Albums._ID);
            int albumnamecoloumn = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int albumartistcoloumn = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);

            do
            {
                String albumName = cursor.getString(albumnamecoloumn);
                String albumArtist = cursor.getString(albumartistcoloumn);

                albumList.add(new Album(albumName, albumArtist));

            }
            while (cursor.moveToNext());
        }

        return albumList;
    }

    public Cursor getAlbumAlbumcursor()
    {
        String where = null;
        ContentResolver cr =getContentResolver();
        final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        final String _id = MediaStore.Audio.Albums._ID;
        final String album_id = MediaStore.Audio.Albums.ALBUM_ID;
        final String album_name =MediaStore.Audio.Albums.ALBUM;
        final String artist = MediaStore.Audio.Albums.ARTIST;
        final String[]columns={_id,album_name,artist};
        Cursor cursor = cr.query(uri,columns,where,null, null);
        return cursor;
    }
}
