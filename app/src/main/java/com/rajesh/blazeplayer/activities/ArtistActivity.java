package com.rajesh.blazeplayer.activities;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.rajesh.blazeplayer.R;
import com.rajesh.blazeplayer.adapter.ArtistAdapter;
import com.rajesh.blazeplayer.model.Artist;
import java.util.ArrayList;

/**
 * Created by Rajesh on 3/28/2015.
 */
public class ArtistActivity extends ActionBarActivity
{
    ArrayList<Artist> artistList;
    ListView albumListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.album_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Albums");

        artistList = new ArrayList<Artist>();
        albumListView = (ListView)findViewById(R.id.albumsList);
        albumListView.setFastScrollEnabled(true);

        getAlbumsList();

        ArtistAdapter albumsAdapter = new ArtistAdapter(ArtistActivity.this,artistList);
        albumListView.setAdapter(albumsAdapter);
    }



    public ArrayList<Artist> getAlbumsList()
    {
        Cursor cursor = getAlbumAlbumcursor();

        if(cursor != null && cursor.moveToFirst())
        {
            int albumnamecoloumn = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
            int albumartistcoloumn = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);

            do
            {
                String albumName = cursor.getString(albumnamecoloumn);
                String albumArtist = cursor.getString(albumartistcoloumn);

                artistList.add(new Artist(albumName, albumArtist));

            }
            while (cursor.moveToNext());
        }

        return artistList;
    }

    public Cursor getAlbumAlbumcursor()
    {
        String where = null;
        ContentResolver cr =getContentResolver();
        final Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        final String _id = MediaStore.Audio.Artists._ID;
        final String album_totalalbums = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
        final String album_name =MediaStore.Audio.Albums.ARTIST;
        final String[]columns={_id,album_totalalbums,album_name};
        Cursor cursor = cr.query(uri,columns,where,null, null);
        return cursor;
    }
}
