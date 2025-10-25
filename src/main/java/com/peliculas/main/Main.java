package com.peliculas.main;

import com.peliculas.exception.DataOutputException;
import com.peliculas.exception.InvalidDirectoryException;
import com.peliculas.exception.MovieBinaryPersistanceException;
import com.peliculas.exception.OperationCancelledException;
import com.peliculas.io.MovieInputHandler;
import com.peliculas.io.MovieOutputHandler;
import com.peliculas.service.MovieService;
import com.peliculas.util.DirectorySelector;
import com.peliculas.util.FileUtil;
import com.peliculas.util.Inputs;
import com.peliculas.util.Prints;

public class Main {
    public static void main(String[] args) {
        MovieService movieService = new MovieService();
        boolean exitFlag = false;

        String[] optionsText = {
                "######################MENU#######################",
                "1. Add a new movie.",
                "2. Update an existing movie.",
                "3. Delete a movie.",
                "4. Export list to CSV.",
                "5. Random selection.",
                "6. Go to list.",
                "7. Exit.",
                "8. Create Test Movies."
        };

        while(!exitFlag){
            PrintMenu(optionsText, 200);
            int option = Inputs.GetNumber(1,8,"Select an option: ", false);
            exitFlag = HandleMenuOption(option, movieService);
        }

    }

    private static void PrintMenu(String[] optionsText, int animationTime){
        for(String option: optionsText){
            try {
                System.out.println(option);
                Thread.sleep(animationTime);
            }catch (InterruptedException e){
                Prints.PrintDanger("Thread interrupted.", false);
            }
        }

    }

    private static boolean HandleMenuOption(int option, MovieService movieService) {
        switch (option) {
            case 1 -> movieService.AddMovie(MovieInputHandler.GetMovieFromUser(movieService));
            case 2 -> {
                movieService.UpdateMovie(movieService.GetMovieById(MovieInputHandler.GetIDFromUser(movieService)));
                try {
                    FileUtil.SaveMoviesBinary(movieService.GetList());
                } catch (MovieBinaryPersistanceException e) {
                    Prints.PrintDanger("An error occurred: "+e.getMessage(),true);
                }
            }
            case 3 -> movieService.DeleteMovieByID(MovieInputHandler.GetIDFromUser(movieService));
            case 4 -> {
                Prints.PrintInfo("Please, select a directory.",false);
                try{
                    String res = DirectorySelector.GetDirectoryFromUser();
                    Prints.PrintSuccess("Selected directory: "+res, false);
                    FileUtil.saveMoviesCsv(movieService.GetList(), res);
                    Prints.PrintSuccess("Export OK at: "+res+"/movies.csv",true);
                }catch (InvalidDirectoryException | OperationCancelledException | DataOutputException e){
                    Prints.PrintDanger("Error trying to export CSV File: "+e.getMessage(),true);
                    return false;
                }
            }
            case 5 -> {
                MovieOutputHandler.PrintMovieInfo(movieService.GetRandomMovie());
                Prints.PrintInfo("",true);
            }
            case 6 -> MovieOutputHandler.PrintAllMovies(movieService.GetList());
            case 7 -> {
                return true;
            }
            case 8 -> movieService.AddTestMovies(20);
            default -> Prints.PrintWarning("Option not implemented yet.", true);
        }
        return false;
    }

}