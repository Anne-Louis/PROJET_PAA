package main.menus;

import java.util.Scanner;
import main.components.Reseau;

/**
 * Menu tertiaire d'optimisation du réseau (menu 3).
 * Permet de :
 * <ul>
 *   <li>Trouver une solution plus performante pour le réseau,</li>
 *   <li>Sauvegarder la solution actuelle du réseau.</li>
 * </ul>
 * Le programme se termine lorsque l'utilisateur choisit l'option 3.
 */
public class Menu3 {
    /**
     * @param reseau le reseau qu'on va chercher à optimiser puis sauvegarder sa solution
     * @param sc le scanner pour enregistrer les choix de l'utilisateur
     */
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
                    UtilMenu.sauvegardeSolution(reseau, sc);
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
