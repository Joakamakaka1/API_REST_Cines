package com.es.diecines.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * The type Sesiones.
 */
@Entity
@Table(name = "sesiones")
public class Sesiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Pelicula pelicula;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    /**
     * Instantiates a new Sesiones.
     *
     * @param pelicula the pelicula
     * @param roomId   the room id
     * @param date     the date
     */
    public Sesiones(Pelicula pelicula, Long roomId, LocalDate date) {
        this.pelicula = pelicula;
        this.roomId = roomId;
        this.date = date;
    }

    /**
     * Instantiates a new Sesiones.
     */
    public Sesiones() {
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
     * Gets pelicula.
     *
     * @return the pelicula
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Sets pelicula.
     *
     * @param pelicula the pelicula
     */
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
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
