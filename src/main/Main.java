package main;

import java.util.Scanner;

import main.components.Reseau;
import main.exceptions.ArgumentsException;
import main.menus.UtilMenu;

public class Main {
    public static void main(String[] args){
        Reseau reseau = new Reseau();
        Scanner sc = new Scanner(System.in);
        try {
            UtilMenu.verifArguments(reseau, sc, args);
        } catch (ArgumentsException e){
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
