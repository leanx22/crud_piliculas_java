package com.peliculas.service;

import com.peliculas.exception.MovieBinaryPersistanceException;
import com.peliculas.exception.OperationCancelledException;
import com.peliculas.io.MovieInputHandler;
import com.peliculas.io.MovieOutputHandler;
import com.peliculas.model.Genre;
import com.peliculas.model.Movie;
import com.peliculas.util.FileUtil;
import com.peliculas.util.Inputs;
import com.peliculas.util.Prints;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class MovieService {
    private ArrayList<Movie> movieList = null;

    public MovieService(){
        Prints.PrintInfo("Starting new MovieService...",false);
        this.movieList = FileUtil.LoadMoviesBinary();
    }

    public ArrayList<Movie> GetList(){
        return this.movieList;
    }

    public void AddTestMovies(int q){
        if(q<=0){
            throw new IllegalArgumentException("Argument q cant be <= 0");
        }

        for(int i = 0; i<q; i++){
            Movie newMovie = new Movie("Test"+i, Genre.DESCONOCIDO, (int) (Math.random() * 81)+60);
            AddMovie(newMovie);
        }
    }

    public void AddMovie(Movie movie){

        if(movie == null){
            throw new IllegalArgumentException(this.getClass().getName()+". You are trying to add a null movie.");
        }

        Prints.PrintInfo("Saving...", false);

        int id = 0;
        if(!this.movieList.isEmpty()){
            id = this.movieList.getLast().getId() + 1;
        }

        Movie newMovie = new Movie(id, movie.getName(), movie.getGenre(),
                movie.getMinutes(), movie.getAddedDate());

        movieList.add(newMovie);

        try {
            FileUtil.SaveMoviesBinary(this.movieList);
            Prints.PrintSuccess("Saved!", true);
        } catch (MovieBinaryPersistanceException e) {
            this.movieList.remove(newMovie);
            Prints.PrintDanger("An error has occurred while saving the movie to a file. Error: "+e.getMessage(), true);
        }
    }

    public Movie GetMovieById(int id) throws NoSuchElementException{

        if(id < 0){
            throw new IllegalArgumentException("Invalid ID: ID cannot be negative.");
        }

        for(Movie movie : movieList){
            if(movie.getId() == id){
                return movie;
            }
        }
        throw new NoSuchElementException("No movie found with ID: " + id);
    }

    public void DeleteMovieByID(int id){
        try {
            Movie movie = GetMovieById(id);
            this.movieList.remove(movie);
            Prints.PrintSuccess("Movie deleted!", true);
        } catch (NoSuchElementException e) {
            Prints.PrintDanger("Movie not found ID: "+id, true);
        }
    }

    public void UpdateMovie(Movie movie){
        boolean continueFlag = true;
        while(continueFlag){
            MovieOutputHandler.PrintMovieInfo(movie);
            Prints.PrintInfo("EDIT: \n1.Title.\n2.Genre.\n3.Duration.\n4.Exit",false);
            try {
                movie = HandleEditOption(Inputs.GetNumber(1, 4, ">", false), movie);
                Prints.PrintSuccess("Update OK!",true);
            } catch (OperationCancelledException e) {
                continueFlag = false;
            }
        }
    }

    private Movie HandleEditOption(int option, Movie movie) throws OperationCancelledException {
        switch (option) {
            case 1 -> {
                movie.setName(Inputs.GetText(2,75, "Enter the new title: ", false));
            }
            case 2 -> {
                movie.setGenre(MovieInputHandler.GetGenreFromUser());
            }
            case 3 -> {
                movie.setMinutes(Inputs.GetNumber(1, 300, "Enter new duration (max. 300 min.): ", false));
            }
            case 4 -> throw new OperationCancelledException("Edition cancelled.");

        }
        return movie;
    }

    public int GetLastID(){
        if(this.movieList.isEmpty()){
            return 0;
        }
        return this.movieList.getLast().getId();
    }

    public Movie GetRandomMovie(){
        Random random = new Random();
        return GetMovieById(random.nextInt(GetLastID() + 1));
    }
}
