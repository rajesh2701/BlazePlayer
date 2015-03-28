package com.rajesh.blazeplayer.model;

/**
 * Created by Rajesh on 3/28/2015.
 */
public class Artist
{
    long artsitID;
    String artistName;
    String artistAlbumstotal;

    public Artist(String artistname,String artisttotalalbums)
    {
        this.artistName=artistname;
        this.artistAlbumstotal=artisttotalalbums;
    }

    public long getArtsitID() {
        return artsitID;
    }

    public String getArtistAlbumstotal() {
        return artistAlbumstotal;
    }

    public String getArtistName() {
        return artistName;
    }
}
