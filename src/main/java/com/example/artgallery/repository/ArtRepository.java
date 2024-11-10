package com.example.artgallery.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.artgallery.model.*;

public interface ArtRepository {

    ArrayList<Art> getArts();

    Art getArtById(int artId);

    Art addArt(Art art);

    Art updateArt(int artId, Art art);

    void deleteArt(int artId);

    Artist getArtistByArtId(int artId);

}