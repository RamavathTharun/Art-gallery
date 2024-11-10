package com.example.artgallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.artgallery.service.ArtJpaService;
import com.example.artgallery.model.*;

@RestController
public class ArtController {
    @Autowired
    ArtJpaService artService;

    @GetMapping("/galleries/artists/arts")
    public ArrayList<Art> getArts() {
        return artService.getArts();
    }

    @GetMapping("/galleries/artists/arts/{artId}")
    public Art getArtById(@PathVariable("artId") int artId) {
        return artService.getArtById(artId);
    }

    @PostMapping("/galleries/artists/arts")
    public Art addArt(@RequestBody Art art) {
        return artService.addArt(art);
    }

    @PutMapping("/galleries/artists/arts/{artId}")
    public Art updateArt(@PathVariable("artId") int artId, @RequestBody Art art) {
        return artService.updateArt(artId, art);
    }

    @DeleteMapping("/galleries/artists/arts/{artId}")
    public void deleteArt(@PathVariable("artId") int artId) {
        artService.deleteArt(artId);
    }

    @GetMapping("/arts/{artId}/artist")
    public Artist getArtistByArtId(@PathVariable("artId") int artId) {
        return artService.getArtistByArtId(artId);
    }

}