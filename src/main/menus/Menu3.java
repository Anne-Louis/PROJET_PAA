package main.menus;

import java.util.Scanner;

import gui.controllers.Controleur;
import gui.main.AppUI;
import main.components.Reseau;
import main.exceptions.InvalideReseauException;

/**
 * Menu tertiaire d'optimisation du réseau (menu 3).
 * Permet de :
 * <ul>
 *   <li>Trouver une solution plus performante pour le réseau,</li>
 *   <li>Sauvegarder la solution actuelle du réseau,</li>
 *   <li>Ouvrir l'interface graphique.</li>
 * </ul>
 * Le programme se termine lorsque l'utilisateur choisit l'option 3.
 */
public class Menu3 {
    /**
     * @param reseau le reseau qu'on va chercher à optimiser puis sauvegarder sa solution
     * @param sc le scanner pour enregistrer les choix de l'utilisateur 
     */
    public static void menu3(Reseau reseau, Scanner sc){
        System.out.println("Le reseau a bien ete charge : ");
        System.out.println(reseau);
        System.out.println("Le cout du reseau est : " + reseau.calculerCoutReseau());

        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion reseau --------------------");
            System.out.println("----------- Optimisation d'un reseau (Menu 3) ---------");
            System.out.println("1) reseolution automatique ;") ;
            System.out.println("2) sauvegarder la solution actuelle ;") ;
            System.out.println("3) utiliser une interface graphique ;");
            System.out.println("4) fin.");
            System.out.println("--------------------------------------------------------");

            int nb = UtilMenu.lireEntierAuClavier(sc, "Votre choix ? : ");
            sc.nextLine();

            switch(nb){
                case 1 :
                    reseau = UtilMenu.optimiserReseau(reseau);
                    break ;
                case 2 :
                    UtilMenu.sauvegardeSolution(reseau, sc);
                    break ;
                case 3 :
                    Controleur.setReseau(reseau);
                    AppUI.main(null);
                    break ;
                case 4 :
                    fin = true;
                    System.out.println("Merci d'avoir utilise ce programme !!!") ;
                    break;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }
}
