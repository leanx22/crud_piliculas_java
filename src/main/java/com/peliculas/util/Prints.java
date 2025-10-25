package com.peliculas.util;

import java.util.Scanner;

public class Prints {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String DNGR = "\u001B[31m";
    public static final String OK = "\u001B[32m";
    public static final String WARN = "\u001B[33m";
    public static final String INFO = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    private static Scanner sc = new Scanner(System.in);

    public static void PrintWarning(String message, boolean waitForInput){
        System.out.println(WARN+"[Warn]"+message+RESET);
        if(waitForInput){
            System.out.println("Press ENTER to continue...");
            sc.nextLine();
        }
    }

    public static void PrintDanger(String message, boolean waitForInput){
        System.out.println(DNGR+"[!]"+message+RESET);
        if(waitForInput){
            System.out.println("Press ENTER to continue...");
            sc.nextLine();
        }
    }

    public static void PrintInfo(String message, boolean waitForInput){
        String icon = "[>]";

        if(message.isEmpty()){
            icon = "";
        }

        System.out.println(INFO+icon+message+RESET);
        if(waitForInput){
            System.out.println("Press ENTER to continue...");
            sc.nextLine();
        }
    }

    public static void PrintSuccess(String message, boolean waitForInput){
        System.out.println(OK+"[>>]"+message+RESET);
        if(waitForInput){
            System.out.println("Press ENTER to continue...");
            sc.nextLine();
        }
    }
}
