package main.menus;

import java.util.Scanner;
import main.components.Reseau;
import main.io.SauvegardeReseau;

public class Menu3 {
    public static void menu3(Reseau reseau, Scanner sc){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion réseau --------------------");
            System.out.println("----------- Optimisation d'un réseau (Menu 3) ---------");
            System.out.println("1) résolution automatique") ;
            System.out.println("2) sauvegarder la solution actuelle;") ;
            System.out.println("3) fin.") ;
            System.out.println("--------------------------------------------------------");

            int nb = UtilMenu.lireEntierAuClavier(sc, "Votre choix ? : ");
            sc.nextLine();

            switch(nb){
                case 1 :
                    break ;
                case 2 :
                    SauvegardeReseau.sauvegardeReseau(reseau, "src/ressources/test.txt");
                    break ;
                case 3 :
                    fin = true ;
                    System.out.println("Merci d'avoir utilisé ce programme !!!") ;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }
}
