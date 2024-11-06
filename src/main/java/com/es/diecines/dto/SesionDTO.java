package com.es.diecines.dto;

import java.time.LocalDate;

/**
 * The type Sesion dto.
 */
public class SesionDTO {
    private Long id;
    private Long movieId;
    private Long roomId;
    private LocalDate date;

    /**
     * Instantiates a new Sesion dto.
     */
    public SesionDTO() {

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
     * Gets movie id.
     *
     * @return the movie id
     */
    public Long getMovieId() {
        return movieId;
    }

    /**
     * Sets movie id.
     *
     * @param movieId the movie id
     */
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
