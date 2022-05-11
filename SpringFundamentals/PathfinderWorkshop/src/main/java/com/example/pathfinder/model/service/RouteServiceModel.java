package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.CategoryEntity;
import com.example.pathfinder.model.entity.PictureEntity;
import com.example.pathfinder.model.entity.UserEntity;
import com.example.pathfinder.model.entity.enums.UserLevelEnum;

import java.util.List;
import java.util.Set;

public class RouteServiceModel {

    private Long id;
    private String description;
    private String gpxCoordinates;
    private UserLevelEnum level;
    private String name;
    private UserEntity author;
    private String videoUrl;
    private Set<PictureEntity> pictures;
    private List<CategoryEntity> categories;

    public RouteServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public void setLevel(UserLevelEnum level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
