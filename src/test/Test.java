package test;

import java.util.Scanner;

import main.components.*;
import main.menus.*;

public class Test {
    public static void main (String[] args){
        Reseau reseau = new Reseau();
        Generateur gen = new Generateur("G1", 60);
        Maison msn = new Maison("M1",NiveauConsommation.NORMAL);
        reseau.ajouterGenerateur(gen);
        reseau.ajouterMaison(msn);
        reseau.ajouterConnexion(new Connexion(gen, msn));
        System.out.println(reseau);
        Scanner sc = new Scanner(System.in);
        Menu3.menu3(reseau, sc);
    }
}
