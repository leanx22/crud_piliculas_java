package com.peliculas.util;
import com.peliculas.exception.DataOutputException;
import com.peliculas.exception.InvalidDirectoryException;
import com.peliculas.exception.OperationCancelledException;

import javax.swing.*;
import java.io.File;

public final class DirectorySelector {

    public static String GetDirectoryFromUser() throws OperationCancelledException, InvalidDirectoryException{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            File selectedDirectory = fileChooser.getSelectedFile();
            if(!selectedDirectory.canWrite() || !selectedDirectory.canRead()){
                throw new InvalidDirectoryException("You don't have write/read permissions on this directory.");
            }
            return selectedDirectory.getAbsolutePath();
        }else if(result == JFileChooser.CANCEL_OPTION){
            throw new OperationCancelledException("Operation cancelled by the user");
        }else if(result == JFileChooser.ERROR_OPTION){
            throw new InvalidDirectoryException("An error occurred trying to select directory.");
        }
        return null;
    }

}
