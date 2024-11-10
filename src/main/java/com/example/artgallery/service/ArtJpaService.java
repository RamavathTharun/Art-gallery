package com.example.artgallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import com.example.artgallery.model.*;
import com.example.artgallery.repository.*;

@Service
public class ArtJpaService implements ArtRepository {

    @Autowired
    ArtJpaRepository artJpaRepository;

    @Autowired
    ArtistJpaRepository artistJpaRepository;

    @Override
    public ArrayList<Art> getArts() {
        List<Art> artsList = artJpaRepository.findAll();
        ArrayList<Art> arts = new ArrayList<>(artsList);

        return arts;
    }

    @Override
    public Art getArtById(int artId) {
        try {
            Art art = artJpaRepository.findById(artId).get();

            return art;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Art addArt(Art art) {
        Artist artist = art.getArtist();

        try {
            int artistId = artist.getArtistId();

            Artist savedArtist = artistJpaRepository.findById(artistId).get();

            art.setArtist(savedArtist);

            return artJpaRepository.save(art);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Art updateArt(int artId, Art art) {
        try {
            Art newArt = artJpaRepository.findById(artId).get();

            if (art.getArtTitle() != null) {
                newArt.setArtTitle(art.getArtTitle());
            }

            if (art.getTheme() != null) {
                newArt.setTheme(art.getTheme());
            }

            artJpaRepository.save(newArt);

            if (art.getArtist() != null) {

                Artist artist = art.getArtist();

                int artistId = artist.getArtistId();

                Artist savedArtist = artistJpaRepository.findById(artistId).get();

                newArt.setArtist(savedArtist);

            }

            return artJpaRepository.save(newArt);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteArt(int artId) {
        try {

            Art art = artJpaRepository.findById(artId).get();

            artJpaRepository.deleteById(artId);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public Artist getArtistByArtId(int artId) {

        try {
            Art art = artJpaRepository.findById(artId).get();

            Artist artist = art.getArtist();
            return artist;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}