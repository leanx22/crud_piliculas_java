package com.peliculas.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Movie implements Serializable {
    private final int id;
    private String name;
    private Genre genre;
    private int minutes;
    private final LocalDateTime addedDate;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Movie(String name, Genre genre, int minutes) {
        this.id = 0;
        this.name = name;
        this.genre = genre;
        this.minutes = minutes;
        this.addedDate = LocalDateTime.now();
    }

    public Movie(String name, Genre genre, int minutes, LocalDateTime addedDate) {
        this.id = 0;
        this.name = name;
        this.genre = genre;
        this.minutes = minutes;
        this.addedDate = addedDate;
    }

    public Movie(int id, String name, Genre genre, int minutes, LocalDateTime addedDate) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.minutes = minutes;
        this.addedDate = addedDate;
    }

    //GETTERS
    public int getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public LocalDateTime getAddedDate() {
        return this.addedDate;
    }

    public DateTimeFormatter getDateFormatter(){
        return Movie.dateFormat;
    }
    ////

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    ////

    //METHODS
    public String GetCsvFormatted(){
        return this.id+","+this.name+","+this.genre+","+this.minutes+","+this.GetFormattedAddedDate();
    }

    public String GetFormattedAddedDate() {
        return addedDate.format(dateFormat);
    }

    public String toString(){
        return String.format("|%-4s|%-20s|%-12s|%-8s|%-10s|", this.id, this.name, this.genre.toString().toLowerCase(), this.minutes+"min.", this.GetFormattedAddedDate());
    }
    ////

}
