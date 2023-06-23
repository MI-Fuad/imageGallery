package com.sg.imageGallery.dao;

import com.sg.imageGallery.model.Artist;

import java.util.List;

public interface ArtistDao {

    Artist getArtistById(int id);
    List<Artist> getAllArtists();
    void updateArtist(Artist artist);


    // maybe later ADD

}
