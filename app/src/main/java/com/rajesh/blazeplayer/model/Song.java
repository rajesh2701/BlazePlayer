package com.rajesh.blazeplayer.model;

/**
 * Created by Rajesh on 3/28/2015.
 */
public class Song
{
    long songId;
    String songName;
    String songArtist;

    public Song(long id,String songName,String songArtist)
    {
        this.songId = id;
        this.songName = songName;
        this.songArtist = songArtist;
    }

    public long getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongArtist() {
        return songArtist;
    }
}
