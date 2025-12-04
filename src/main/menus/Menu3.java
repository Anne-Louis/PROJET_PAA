package main.menus;

import java.util.Scanner;
import main.components.Reseau;
import main.algorithmes.*;
import main.io.ParserFile;

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
            System.out.println("0 Charger un fichier de test");
            System.out.println("1) résolution automatique 1") ;
            System.out.println("2) resolution automatique 2") ;
            System.out.println("3) sauvegarder la solution actuelle;") ;
            System.out.println("4) Calculer cout reseau.") ;
            System.out.println("5) affichiser le reseau.");
            System.out.println("6) fin");
            System.out.println("--------------------------------------------------------");

            int nb = UtilMenu.lireEntierAuClavier(sc, "Votre choix ? : ");
            sc.nextLine();

            switch(nb){
                case 0:
                    reseau = ParserFile.readReseau();
                    break;
                case 1 :
                    Algorithme1.resoudreReseau(reseau, Algorithme1.epsilonInit);
                    System.out.println("Le cout du reseau vaut : "+ reseau.calculerCoutReseau());
                    break ;
                case 2 :
                    reseau = Algorithme2.resoudreReseau(reseau, Algorithme1.epsilonInit);
                    System.out.println("Le cout du reseau vaut : "+ reseau.calculerCoutReseau());
                    break ;
                case 3 :
                    UtilMenu.sauvegardeSolution(reseau, sc);
                    break ;
                case 4 :
                    System.out.println("Le cout du reseau vaut : "+ reseau.calculerCoutReseau());
                    break ;
                case 5 :
                    System.out.println(reseau.toString());
                    break;
                case 6 :
                    fin = true;
                    System.out.println("Merci d'avoir utilisé ce programme !!!") ;
                    break;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }
}
