package com.es.diecines.model;

import jakarta.persistence.*;

/**
 * The type Pelicula.
 */
@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    private String time;

    private String trailer;

    @Column(name = "poster_image")
    private String posterImage;

    private String screenshot;

    @Column(length = 1000)
    private String synopsis;

    @Column(nullable = false)
    private Double rating;

    /**
     * Instantiates a new Pelicula.
     *
     * @param title       the title
     * @param director    the director
     * @param time        the time
     * @param trailer     the trailer
     * @param posterImage the poster image
     * @param screenshot  the screenshot
     * @param synopsis    the synopsis
     * @param rating      the rating
     */
    public Pelicula(String title, String director, String time, String trailer, String posterImage, String screenshot, String synopsis, Double rating) {
        this.title = title;
        this.director = director;
        this.time = time;
        this.trailer = trailer;
        this.posterImage = posterImage;
        this.screenshot = screenshot;
        this.synopsis = synopsis;
        this.rating = rating;
    }


    /**
     * Instantiates a new Pelicula.
     */
    public Pelicula() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets director.
     *
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets director.
     *
     * @param director the director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets trailer.
     *
     * @return the trailer
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * Sets trailer.
     *
     * @param trailer the trailer
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * Gets poster image.
     *
     * @return the poster image
     */
    public String getPosterImage() {
        return posterImage;
    }

    /**
     * Sets poster image.
     *
     * @param posterImage the poster image
     */
    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    /**
     * Gets screenshot.
     *
     * @return the screenshot
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Sets screenshot.
     *
     * @param screenshot the screenshot
     */
    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    /**
     * Gets synopsis.
     *
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Sets synopsis.
     *
     * @param synopsis the synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }
}

