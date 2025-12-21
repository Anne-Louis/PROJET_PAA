package main.menus;

import java.util.Scanner;

import gui.controllers.Controleur;
import gui.main.AppUI;
import main.components.Reseau;
import main.exceptions.InvalideReseauException;

/**
 * Menu principal de création du réseau (menu 1).
 * Permet de :
 * <ul>
 *   <li>Créer un générateur,</li>
 *   <li>Créer une maison,</li>
 *   <li>Créer une connexion,</li>
 *   <li>Supprimer une connexion,</li>
 *   <li>Ouvrir l'interface graphique.</li>
 * </ul>
 * Le programme passe au menu suivant une fois que le réseau est validé
 * lorsque l'utilisateur choisit l'option 5.
 */
public class Menu1 {
    /**
     * @param reseau le reseau qu'on va chercher à optimiser puis sauvegarder sa solution
     * @param sc le scanner pour enregistrer les choix de l'utilisateur
     */
    public static void menu1(Reseau reseau, Scanner sc){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion reseau --------------------");
            System.out.println("------------ Creation d'un reseau (Menu 1) -----------");
            System.out.println("1) ajouter un generateur;") ;
            System.out.println("2) ajouter une maison;") ;
            System.out.println("3) ajouter une connexion entre une maison et un generateur existants;");
            System.out.println("4) supprimer une connexion existante entre une maison et un generateur;");
            System.out.println("5) utiliser une interface graphique ;");
            System.out.println("6) fin.") ;
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
                    Controleur.setReseau(reseau);
                    AppUI.main(null);
                    break ;  
                case 6 :
                    try{
                        if (reseau.validerReseau()){
                            fin = true ;
                            Menu3.menu3(reseau, sc) ;
                        }
                    }catch(InvalideReseauException e){
                        System.out.println(e.getMessage());
                    }
                    break ;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }
}
