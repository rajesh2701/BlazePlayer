package com.rajesh.blazeplayer.model;

/**
 * Created by Rajesh on 3/28/2015.
 */
public class Album
{
    long albumId;
    String albumName;
    String albumArtist;
    String albumTotalSongs;

    public Album(String albumname,String albumartist)
    {

        this.albumName=albumname;
        this.albumArtist=albumartist;

    }

    public long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getAlbumTotalSongs() {
        return albumTotalSongs;
    }
}

