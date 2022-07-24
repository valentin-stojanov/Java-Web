package bg.softuni.patfinder2.model;

import bg.softuni.patfinder2.model.enums.LevelEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(columnDefinition = "LONGTEXT")
    @Lob
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    private String name;

    @ManyToOne
    private UserEntity author;

    private String videoUrl;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<PictureEntity> pictures;

    @ManyToMany
    private Set<CategoryEntity> categories;

    @Column(columnDefinition = "TEXT")
    private String description;

    public RouteEntity() {
        this.comments = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
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

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public RouteEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
