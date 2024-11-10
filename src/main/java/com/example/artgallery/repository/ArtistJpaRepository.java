package com.example.artgallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.artgallery.model.*;

@Repository
public interface ArtistJpaRepository extends JpaRepository<Artist, Integer> {

}