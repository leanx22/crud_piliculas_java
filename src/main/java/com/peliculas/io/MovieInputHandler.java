package com.peliculas.io;

import com.peliculas.model.Genre;
import com.peliculas.model.Movie;
import com.peliculas.service.MovieService;
import com.peliculas.util.Inputs;

public final class MovieInputHandler {

    private MovieInputHandler(){}

    public static Movie GetMovieFromUser(MovieService service){
        String name = Inputs.GetText(2,75,"Enter movie's name (max 75ch.): ", false);
        Genre genre = GetGenreFromUser();
        int duration = Inputs.GetNumber(1,300, "Enter the duration of the movie (max 300min.): ", false);
        return new Movie(name, genre, duration);
    }

    public static Genre GetGenreFromUser(){
        Genre[] genres = Genre.values();
        for(int i=0; i<genres.length; i++){
            System.out.println((i+1)+". "+genres[i]);
        }

        return genres[Inputs.GetNumber(1, genres.length, "Select a genre: ", false) - 1];
    }

    public static int GetIDFromUser(MovieService service){
        int lastID = service.GetLastID();
        return Inputs.GetNumber(0,lastID, "Enter movie ID (0 - "+lastID+"): ", false);
    }

}
