package main.menus;

import java.util.Scanner;
import main.components.Reseau;

/**
 * Menu principal de création du réseau (menu 1).
 * Permet de :
 * <ul>
 *   <li>Créer un générateur,</li>
 *   <li>Créer une maison,</li>
 *   <li>Créer une connexion,</li>
 *   <li>Supprimer une connexion.</li>
 * </ul>
 * Le programme passe au menu suivant une fois que le réseau est validé
 * lorsque l'utilisateur choisit l'option 5.
 */
public class Menu1 {
    /** Réseau électrique géré par l'application. */
    private static Reseau reseau = new Reseau() ;

    /** Scanner utilisé pour lire les entrées utilisateur. */
    private static Scanner sc = new Scanner(System.in) ;
    
    public static void menu1(){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion réseau --------------------");
            System.out.println("------------ Création d'un réseau (Menu 1) -----------");
            System.out.println("1) ajouter un générateur;") ;
            System.out.println("2) ajouter une maison;") ;
            System.out.println("3) ajouter une connexion entre une maison et un générateur existants;");
            System.out.println("4) supprimer une connexion existante entre une maison et un générateur;");
            System.out.println("5) fin.") ;
            System.out.println("--------------------------------------------------------");

            int nb = UtilMenu.lireEntierAuClavier(sc, "Votre choix ? : ");
            sc.nextLine();

            switch(nb){
                case 1 : 
                    UtilMenu.creerGenerateur(reseau, sc);
                    break ;
                case 2 :
                    UtilMenu.creerMaison(reseau, sc);
                    break ;
                case 3 :
                    UtilMenu.creerConnexion(reseau, sc);
                    break ;
                case 4 :
                    UtilMenu.supprimerConnexion(reseau, sc);
                    break ;
                case 5 :
                    if (!reseau.validerReseau()){
                        System.out.println("Le réseau n'est pas valide");
                        break ;
                    }
                    fin = true ;
                    Menu2.menu2(reseau, sc) ;
                    break ;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
        sc.close();
    }
}
