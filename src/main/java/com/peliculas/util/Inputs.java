package com.peliculas.util;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {
    public static int GetNumber(int min, int max, String message, boolean pressEnterAfterError){
        Scanner inputScanner = new Scanner(System.in);
        int number;
        while(true){
            try{
                Prints.PrintInfo(message, false);
                number =inputScanner.nextInt();
                if(number < min || number > max){
                    Prints.PrintDanger("Number out of range ("+min+"-"+max+"). Try again.",false);
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                Prints.PrintDanger("Bad input. Try again.", pressEnterAfterError);
                inputScanner.nextLine(); //"Clear" the line - buffer to avoid loops.
            }
        }
        return number;
    }

    public static String GetText(int minL, int maxL, String message, boolean pressEnterAfterError){
        Scanner inputScanner = new Scanner(System.in);
        String input;
        while(true){
        Prints.PrintInfo(message,false);
            input = inputScanner.nextLine();
            if(input.length() < minL || input.length() > maxL){
                Prints.PrintDanger("Bad input. Try again.", pressEnterAfterError);
                continue;
            }
            return input;
        }
    }
}
