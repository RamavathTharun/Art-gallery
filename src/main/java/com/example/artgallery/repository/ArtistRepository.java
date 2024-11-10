package com.example.artgallery.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.artgallery.model.*;

public interface ArtistRepository {
    ArrayList<Artist> getArtists();

    Artist getArtistById(int artistId);

    Artist addArtist(Artist artist);

    Artist updateArtist(int artistId, Artist artist);

    void deleteArtist(int artistId);

    public ArrayList<Art> getArtsByArtistId(int artistId);

    public ArrayList<Gallery> getGalleryByArtistId(int artistId);
}