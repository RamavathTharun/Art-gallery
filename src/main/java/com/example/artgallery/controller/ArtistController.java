package com.example.artgallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.artgallery.service.ArtistJpaService;
import com.example.artgallery.model.*;

@RestController
public class ArtistController {
    @Autowired
    private ArtistJpaService artistService;

    @GetMapping("/galleries/artists")
    public ArrayList<Artist> getArtists() {
        return artistService.getArtists();
    }

    @GetMapping("/galleries/artists/{artistId}")
    public Artist getArtistById(@PathVariable("artistId") int artistId) {
        return artistService.getArtistById(artistId);
    }

    @PostMapping("/galleries/artists")
    public Artist addArtist(@RequestBody Artist artist) {
        return artistService.addArtist(artist);
    }

    @PutMapping("/galleries/artists/{artistId}")
    public Artist updateArtist(@PathVariable("artistId") int artistId, @RequestBody Artist artist) {
        return artistService.updateArtist(artistId, artist);
    }

    @DeleteMapping("/galleries/artists/{artistId}")
    public void deleteArtist(@PathVariable("artistId") int artistId) {
        artistService.deleteArtist(artistId);
    }

    @GetMapping("/artists/{artistId}/arts")
    public ArrayList<Art> getArtsByArtistId(@PathVariable("artistId") int artistId) {
        return artistService.getArtsByArtistId(artistId);
    }

    @GetMapping("/artists/{artistId}/galleries")
    public ArrayList<Gallery> getGalleryByArtistId(@PathVariable("artistId") int artistId) {
        return artistService.getGalleryByArtistId(artistId);
    }

}