package com.example.artgallery.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.artgallery.model.*;

public interface GalleryRepository {
    ArrayList<Gallery> getGalleries();

    Gallery getGalleryById(int galleryId);

    Gallery addGallery(Gallery gallery);

    Gallery updateGallery(int galleryId, Gallery gallery);

    void deleteGallery(int galleryId);

    ArrayList<Artist> getArtistsByGalleryId(int galleryId);
}
