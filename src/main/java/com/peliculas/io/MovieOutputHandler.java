package com.peliculas.io;

import com.peliculas.model.Movie;
import com.peliculas.util.Prints;

import java.util.ArrayList;

public final class MovieOutputHandler {

    public static void PrintAllMovies(ArrayList<Movie> list){
        if(list.isEmpty()){
            Prints.PrintWarning("Nothing to see here.", true);
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("|%-4s|%-20s|%-12s|%-8s|%-10s|\n", "ID", "TITLE", "GENRE", "DURATION", "CREATED AT");
        for(Movie movie : list){
            System.out.println(movie.toString());
        }
        System.out.println("------------------------------------------------------------");
        Prints.PrintInfo("", true);
    }

    public static void PrintMovieInfo(Movie movie){
        System.out.println("-------------------|MOVIE INFO|-----------------------------");
        System.out.println("Title: "+movie.getName()+"\n" +
                "Genre: "+movie.getGenre()+"\n" +
                "Duration (minutes): "+movie.getMinutes()+"\n" +
                "Added date: "+movie.getAddedDate().format(movie.getDateFormatter()));
        System.out.println("------------------------------------------------------------");
    }

}
