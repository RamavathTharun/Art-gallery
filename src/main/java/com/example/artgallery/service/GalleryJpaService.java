package com.example.artgallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import com.example.artgallery.repository.*;
import com.example.artgallery.model.*;

@Service
public class GalleryJpaService implements GalleryRepository {

    @Autowired
    private GalleryJpaRepository galleryJpaRepository;

    @Autowired
    private ArtistJpaRepository artistJpaRepository;

    @Override
    public ArrayList<Gallery> getGalleries() {
        List<Gallery> galleriesList = galleryJpaRepository.findAll();
        ArrayList<Gallery> galleries = new ArrayList<>(galleriesList);
        return galleries;

    }

    @Override
    public Gallery getGalleryById(int galleryId) {
        try {
            Gallery gallery = galleryJpaRepository.findById(galleryId).get();
            return gallery;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Gallery addGallery(Gallery gallery) {
        List<Integer> artistIds = new ArrayList<>();

        for (Artist artist : gallery.getArtists()) {
            artistIds.add(artist.getArtistId());
        }

        List<Artist> artistsList = artistJpaRepository.findAllById(artistIds);

        if (artistsList.size() != artistIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "some artists are found");
        }

        gallery.setArtists(artistsList);

        return galleryJpaRepository.save(gallery);

    }

    @Override
    public Gallery updateGallery(int galleryId, Gallery gallery) {
        try {
            Gallery newGallery = galleryJpaRepository.findById(galleryId).get();
            if (gallery.getGalleryName() != null) {
                newGallery.setGalleryName(gallery.getGalleryName());
            }

            if (gallery.getLocation() != null) {
                newGallery.setLocation(gallery.getLocation());
            }
            galleryJpaRepository.save(newGallery);

            if (gallery.getArtists() != null) {
                List<Integer> artistIds = new ArrayList<>();

                for (Artist artist : gallery.getArtists()) {
                    artistIds.add(artist.getArtistId());
                }

                List<Artist> artistsList = artistJpaRepository.findAllById(artistIds);

                if (artistsList.size() != artistIds.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "some artists are found");
                }

                newGallery.setArtists(artistsList);
            }

            return galleryJpaRepository.save(newGallery);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteGallery(int galleryId) {
        try {
            Gallery gallery = galleryJpaRepository.findById(galleryId).get();

            List<Artist> artists = gallery.getArtists();

            for (Artist artist : artists) {
                artist.getGalleries().remove(gallery);
            }

            artistJpaRepository.saveAll(artists);

            galleryJpaRepository.deleteById(galleryId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public ArrayList<Artist> getArtistsByGalleryId(int galleryId) {

        try {
            Gallery gallery = galleryJpaRepository.findById(galleryId).get();
            List<Artist> artistsList = gallery.getArtists();
            ArrayList<Artist> artists = new ArrayList<>(artistsList);

            return artists;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}