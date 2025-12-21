package main;

import java.util.Scanner;

import main.components.Reseau;
import main.exceptions.ArgumentsException;
import main.menus.UtilMenu;

/**
 * Point d'entrée du programmme
 */
public class Main {
    /**
     * Méthode principale permettant de lancer le programme
     * @param args liste d'arguments écrit dans la ligne de commande via le terminal
     */
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
