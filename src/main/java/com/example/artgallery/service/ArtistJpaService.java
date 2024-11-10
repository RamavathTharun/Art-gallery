package com.example.artgallery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import com.example.artgallery.model.*;
import com.example.artgallery.repository.*;

@Service
public class ArtistJpaService implements ArtistRepository {

    @Autowired
    private ArtistJpaRepository artistJpaRepository;

    @Autowired
    private GalleryJpaRepository galleryJpaRepository;

    @Autowired
    private ArtJpaRepository artJpaRepository;

    @Override
    public ArrayList<Artist> getArtists() {
        List<Artist> artistsList = artistJpaRepository.findAll();
        ArrayList<Artist> artists = new ArrayList<>(artistsList);
        return artists;
    }

    @Override
    public Artist getArtistById(int artistId) {
        try {

            Artist artist = artistJpaRepository.findById(artistId).get();
            return artist;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Artist addArtist(Artist artist) {
        // Initialize list to hold galleries
        List<Gallery> galleryList = new ArrayList<>();

        // Fetch existing galleries by their IDs and add to the list
        for (Gallery gallery : artist.getGalleries()) {
            // Check if gallery exists in the database
            Optional<Gallery> existingGallery = galleryJpaRepository.findById(gallery.getGalleryId());

            if (existingGallery.isPresent()) {
                galleryList.add(existingGallery.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Gallery with id " + gallery.getGalleryId() + " not found.");
            }
        }

        // Set the fetched galleries to the artist
        artist.setGalleries(galleryList);

        // Save the artist to the database
        artist = artistJpaRepository.save(artist);

        // Associate the artist with the galleries
        for (Gallery gallery : galleryList) {
            gallery.getArtists().add(artist);
        }

        // Save the updated galleries with the new artist
        galleryJpaRepository.saveAll(galleryList);

        return artist;
    }

    @Override
    public Artist updateArtist(int artistId, Artist artist) {

        try {

            Artist newArtist = artistJpaRepository.findById(artistId).get();

            if (artist.getArtistName() != null) {
                newArtist.setArtistName(artist.getArtistName());
            }

            if (artist.getGenre() != null) {
                newArtist.setGenre(artist.getGenre());
            }
            artistJpaRepository.save(newArtist);

            if (artist.getGalleries() != null) {

                List<Gallery> galleries = newArtist.getGalleries();

                for (Gallery gallery : galleries) {
                    gallery.getArtists().remove(newArtist);
                }

                galleryJpaRepository.saveAll(galleries);

                List<Integer> galleryIds = new ArrayList<>();

                for (Gallery gallery : artist.getGalleries()) {
                    galleryIds.add(gallery.getGalleryId());
                }

                List<Gallery> galleryList = galleryJpaRepository.findAllById(galleryIds);

                if (galleryIds.size() != galleryList.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "some galleries are found");
                }
                newArtist.setGalleries(galleryList);

                for (Gallery gallery : galleryList) {
                    gallery.getArtists().add(newArtist);
                }

                galleryJpaRepository.saveAll(galleryList);

            }

            return artistJpaRepository.save(newArtist);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteArtist(int artistId) {

        try {

            Artist artist = artistJpaRepository.findById(artistId).get();

            List<Gallery> galleries = artist.getGalleries();

            for (Gallery gallery : galleries) {
                gallery.getArtists().remove(artist);
            }

            galleryJpaRepository.saveAll(galleries);

            ArrayList<Art> arts = artJpaRepository.findByArtist(artist);

            for (Art art : arts) {
                art.setArtist(null);
            }

            artJpaRepository.saveAll(arts);

            artistJpaRepository.deleteById(artistId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public ArrayList<Art> getArtsByArtistId(int artistId) {
        try {

            Artist artist = artistJpaRepository.findById(artistId).get();
            ArrayList<Art> arts = artJpaRepository.findByArtist(artist);
            return arts;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ArrayList<Gallery> getGalleryByArtistId(int artistId) {
        try {
            Artist artist = artistJpaRepository.findById(artistId).get();
            List<Gallery> galleryList = artist.getGalleries();

            ArrayList<Gallery> galleries = new ArrayList<>(galleryList);
            return galleries;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}