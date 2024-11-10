package com.example.artgallery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

import com.example.artgallery.model.Artist;

@Entity
@Table(name = "art")
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int artId;

    @Column(name = "title")
    private String artTitle;

    @Column(name = "theme")
    private String theme;

    @ManyToOne
    @JoinColumn(name = "artistid")
    private Artist artist;

    public Art() {
    }

    public Art(int artId, String artTitle, String theme, Artist artist) {
        this.artId = artId;
        this.artTitle = artTitle;
        this.theme = theme;
        this.artist = artist;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}