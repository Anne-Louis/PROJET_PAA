package main.menus;

import java.util.Scanner;
import main.components.Reseau;

/**
 * Menu secondaire d'utilisation du réseau (menu 2).
 * Permet de :
 * <ul>
 *   <li>Calculer le coût du réseau électrique actuel,</li>
 *   <li>Modifier une connexion,</li>
 *   <li>Afficher le réseau complet.</li>
 * </ul>
 * Le programme se termine lorsque l'utilisateur choisit l'option 4.
 */
public class Menu2 {
    /**
     * @param reseau le reseau sur lequel on va appliquer les modifications ainsi que le calcul du coût
     * @param sc le scanner pour enregistrer les choix de l'utilisateur
     */
    public static void menu2(Reseau reseau, Scanner sc){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion réseau --------------------");
            System.out.println("----------- Utilisation d'un réseau (Menu 2) ---------");
            System.out.println("1) calculer le coût du réseau électrique actuel;") ;
            System.out.println("2) modifier une connexion;") ;
            System.out.println("3) afficher le réseau;");
            System.out.println("4) fin.") ;
            System.out.println("--------------------------------------------------------");

            int nb = UtilMenu.lireEntierAuClavier(sc, "Votre choix ? : ");
            sc.nextLine();

            switch(nb){
                case 1 :
                    System.out.println("Le coût du réseau électrique actuel est de : " + reseau.calculerCoutReseau() + " (La valeur de la sévérité de la pénalisation est de 10)");
                    break ;
                case 2 :
                    UtilMenu.modifierConnexion(reseau, sc);
                    break ;
                case 3 : 
                    System.out.println(reseau.toString());
                    break ;
                case 4 :
                    fin = true ;
                    System.out.println("Merci d'avoir utilisé ce programme !!!") ;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }
}
