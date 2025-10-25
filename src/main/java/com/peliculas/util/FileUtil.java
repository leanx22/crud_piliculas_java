package com.peliculas.util;
import com.peliculas.exception.DataOutputException;
import com.peliculas.exception.MovieBinaryPersistanceException;
import com.peliculas.model.Movie;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {
    private static final String binPath = "movies.dat";

    public static void SaveMoviesBinary(ArrayList<Movie> list) throws MovieBinaryPersistanceException {
        if(list == null){
            throw new IllegalArgumentException("List cant be null.");
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binPath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            throw new MovieBinaryPersistanceException(e.getMessage());
        }
    }

    public static ArrayList<Movie> LoadMoviesBinary(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binPath))){
            ArrayList<Movie> list = (ArrayList<Movie>) ois.readObject();
            return list;
        }catch(IOException | ClassNotFoundException e){
            //If the list does not exist or anything else, just returns a new empty list.
            return new ArrayList<Movie>();
        }
    }

    public static void saveMoviesCsv(ArrayList<Movie> list, String path) throws DataOutputException {

        if(list == null || list.isEmpty()){
            throw new IllegalArgumentException("Cant export an empty/null list to CSV.");
        }

        try(FileWriter fw = new FileWriter(path+"/movies.csv")){
            fw.write("id,name,genre,duration,addedDate\n");
            for(Movie m : list){
                fw.write(m.GetCsvFormatted()+"\n");
            }
        }catch(IOException e){
            throw new DataOutputException("Error occurred trying to export CSV file. ->"+e.getMessage());
        }

    }

}
