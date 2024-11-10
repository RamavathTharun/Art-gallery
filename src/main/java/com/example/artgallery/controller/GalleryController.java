package com.example.artgallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.artgallery.service.GalleryJpaService;
import com.example.artgallery.model.*;

@RestController
public class GalleryController {
    @Autowired
    private GalleryJpaService galleryService;

    @GetMapping("/galleries")
    public ArrayList<Gallery> getGalleries() {
        return galleryService.getGalleries();
    }

    @GetMapping("/galleries/{galleryId}")
    public Gallery getGalleryById(@PathVariable("galleryId") int galleryId) {
        return galleryService.getGalleryById(galleryId);
    }

    @PostMapping("/galleries")
    public Gallery addGallery(@RequestBody Gallery gallery) {
        return galleryService.addGallery(gallery);
    }

    @PutMapping("/galleries/{galleryId}")
    public Gallery updateGallery(@PathVariable("galleryId") int galleryId, @RequestBody Gallery gallery) {
        return galleryService.updateGallery(galleryId, gallery);
    }

    @DeleteMapping("/galleries/{galleryId}")
    public void deleteGallery(@PathVariable("galleryId") int galleryId) {
        galleryService.deleteGallery(galleryId);
    }

    @GetMapping("/galleries/{galleryId}/artists")
    public ArrayList<Artist> getArtistsByGalleryId(@PathVariable("galleryId") int galleryId) {
        return galleryService.getArtistsByGalleryId(galleryId);
    }

}