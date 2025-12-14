package main.menus;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.algorithmes.Algorithme1;
import main.algorithmes.Algorithme2;
import main.components.*;
import main.exceptions.*;
import main.io.*;

/**
 * Classe utilitaire contenant les méthodes pour les différents menus
 */
public class UtilMenu {
    /**
	 * Cette methode lit un entier dans un scanner, et s'assure que c'est bien un
	 * entier qui a ete lu. Dans le cas contraire, un message est affiche a
	 * l'utilisateur, et un nouveal entier est demande.
	 * 
     * @author Jérôme Delobelle (Correction du TP5 de PAA, MathMenu.java)
	 * @param sc le scanner dans lequel on veut lire un entier
	 * @param message le message affiche a l'utilisateur pour demander l'entier
	 * @return l'entier lu
	 */
	protected static int lireEntierAuClavier(Scanner sc, String message) {
		int res = 0;
		boolean lectureOK = false;

		while (!lectureOK) {
			try {
				System.out.print(message);
				res = sc.nextInt();
				lectureOK = true;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper un nombre entier");
				sc.nextLine();
			}
		}
		return res;
	}

    /**
	 * Cette methode lit une chaîne de caractère dans un scanner, et s'assure que c'est bien une
	 * chaîne de caractère qui a été lu. Dans le cas contraire, un message est affiché à
	 * l'utilisateur, et une nouvelle chaîne de caractère est demandée.
	 * 
     * inspiré de la méthode lireEntierAuClavier de Jérôme Delobelle
     * (Correction du TP5 de PAA, MathMenu.java)
     * 
	 * @param sc le scanner dans lequel on veut lire une chaîne de caractère
	 * @param message le message affiche à l'utilisateur pour demander la chaîne de caractère
	 * @return la chaîne de caractère lue
	 */
	public static String lireStringAuClavier(Scanner sc, String message) {
		String res = "";
		boolean lectureOK = false;

		while (!lectureOK) {
			try {
				System.out.print(message);
				res = sc.nextLine();
				lectureOK = true;
			} catch (InputMismatchException e) {
				System.out.println("Il faut taper une chaîne de caractère");
				sc.nextLine();
			}
		}
		return res;
	}

    /**
     * Permet à l'utilisateur de créer un générateur.
     * Si un générateur du même nom existe déjà, sa capacité est mise à jour.
     * @param reseau le reseau sur lequel on va créer le générateur
     * @param scanner le scanner pour enregistrer les choix de l'utilisateur
     */
    public static void creerGenerateur(Reseau reseau, Scanner sc){
        String message = "Veuillez saisir le générateur à enregistrer ainsi que sa puissance (ex : G1 60) : ";
        String ligne = lireStringAuClavier(sc, message) ;
        String nom = ligne.split("\\s")[0] ;
        double cap = Double.parseDouble(ligne.split("\\s")[1]) ;

        for (Generateur g : reseau.getGenerateurs()){
            if (g.getNom().equals(nom)){
                g.setcapacite(cap);
                System.out.println("Le générateur " + nom + " existe déjà, sa capacité à été mise à jour : " + cap + "kW") ;
                return ;
            }
        }
        Generateur gen = new Generateur(nom, cap);
        reseau.ajouterGenerateur(gen) ;
        System.out.println("Le générateur " + gen.getNom() + " a bien été crée !");
    }

    /**
     * Permet à l'utilisateur de créer une maison.
     * Si une maison du même nom existe déjà, sa consommation est mise à jour.
     * @param reseau le reseau sur lequel on va créer la maison
     * @param scanner le scanner pour enregistrer les choix de l'utilisateur
     */
    public static void creerMaison(Reseau reseau, Scanner sc){
        String message = "Veuillez saisir la maison à enregistrer ainsi que sa consommation (BASSE, NORMAL, FORTE) : ";
        String ligne = lireStringAuClavier(sc, message) ;
        String nom = ligne.split("\\s")[0] ;
        NiveauConsommation cons = NiveauConsommation.fromString(ligne.split("\\s")[1]) ;
                    
            for (Maison m : reseau.getMaisons()){
                if (m.getNom().equals(nom)){
                    m.setConsommation(cons) ;
                    System.out.println("La maison " + nom + " existe déjà, sa consommation à été mise à jour : " + cons) ;
                    return ;
                }
            }
            Maison maison = new Maison(nom, cons);
            reseau.ajouterMaison(maison) ;
            System.out.println("La maison " + maison.getNom() + " a bien été crée !");
    }

    /**
     * Permet de construire une connexion à partir d'un générateur et d'une maison
     * rentrés à l'écrit par l'utilisateur
     * @param reseau le reseau sur lequel on va enregistrer la connexion
     * @param scanner le scanner pour enregistrer les choix de l'utilisateur
     * @param message le message à envoyer à l'utilisateur
     * @return connexion la connexion construite à partir du générateur et de la maison
     */
    public static Connexion enregistrerConnexion(Reseau reseau, Scanner sc, String message){
        String ligne = lireStringAuClavier(sc, message) ;
        Generateur genC = null ;
        Maison maiC = null ;

        if (ligne.startsWith("G")){
            for (Generateur g : reseau.getGenerateurs()){
                if (g.getNom().equals(ligne.split("\\s")[0])){
                    genC = g ;
                }
            }
            for (Maison m : reseau.getMaisons()){
                if (m.getNom().equals(ligne.split("\\s")[1])){
                    maiC = m ;
                }
            }
        } else if (ligne.startsWith("M")){
            for (Generateur g : reseau.getGenerateurs()){
                if (g.getNom().equals(ligne.split("\\s")[1])){
                    genC = g ;
                }
            }
            for (Maison m : reseau.getMaisons()){
                if (m.getNom().equals(ligne.split("\\s")[0])){
                    maiC = m ;
                }
            }
        }

        if (genC == null){
            System.out.println("Le générateur n'existe pas !");
            return null;
        }
        if (maiC == null){
            System.out.println("La maison n'existe pas !");
            return null;
        }

        return new Connexion(genC, maiC) ;
    }

    /**
     * Permet à l'utilisateur de créer une connexion entre un générateur et une maison existants.
     * La méthode vérifie l'existence des deux objets et empêche la création de doublons.
     * @param reseau le reseau sur lequel on va créer la connexion
     * @param scanner le scanner pour enregistrer les choix de l'utilisateur
     */
    public static void creerConnexion(Reseau reseau, Scanner sc){
        String message = "Veuillez saisir la connexion à enregistrer  (ex : G1 M1 ou M1 G1) : " ;
        Connexion con = enregistrerConnexion(reseau, sc, message) ;

        if (con == null){
            return ;
        }
        for (Connexion c : reseau.getConnexions()){
            if (con.equals(c)){
                System.out.println("Cette connexion existe déjà") ;
                return;
            }
        }
        con.getGenerateur().ajouterConnexion(con);
        con.getMaison().setConnexion(con) ;
        reseau.ajouterConnexion(con) ;
        System.out.println("La connexion " + con.toString() + " a bien été crée !");
    }

    /**
     * Permet à l'utilisateur de supprimer une connexion existante.     
     * @param reseau le reseau sur lequel on va supprimer la connexion
     * @param scanner le scanner pour enregistrer les choix de l'utilisateur
     */
    public static boolean supprimerConnexion(Reseau reseau, Scanner sc){
        String message = "Veuillez saisir la connexion que vous souhaitez modifier : " ;
        Connexion con = enregistrerConnexion(reseau, sc, message);
        if (con == null){
            System.out.println("Cette connexion n'existe pas !") ;
            return false;
        }
                    
        reseau.supprimerConnexion(con);
        con.getGenerateur().supprimerConnexion(con);
        con.getMaison().setConnexion(null);
        System.out.println("La connexion " + con.toString() + " a bien été modifié !");
        return true ;
    }

    /**
     * Permet à l'utilisateur de modifier une connexion existante.
     * La connexion est supprimée puis une nouvelle est créée.
     * @param reseau le reseau sur lequel on va modifier la connexion
     * @param scanner le scanner pour enregistrer les choix de l'utilisateur
     */
    public static void modifierConnexion(Reseau reseau, Scanner sc){
        if (supprimerConnexion(reseau, sc)){
            creerConnexion(reseau, sc);
        }
    }

    /**
     * Permet à l'utilisateur de sauvegarder la solution d'un réseau
     * dans un fichier de son choix.
     * Si le fichier existe déjà, il est remplacé sinon un nouveau est crée.
     * @param reseau
     * @param sc
     */
    public static void sauvegardeSolution(Reseau reseau, Scanner sc){
        System.out.println("Votre fichier sera sauvegardé dans le dossier : src/ressources/solutions/");

        boolean fichierSauvegarder = false ;
        while(!fichierSauvegarder){
            try {
                String fichier = lireStringAuClavier(sc, "Quel est le nom de votre fichier ? (pas besoin d'ajouter le .txt) : ");
                SauvegardeReseau.sauvegardeReseau(reseau, fichier);
                fichierSauvegarder = true ;
                System.out.println("Votre réseau a bien été sauvegardé !");
            } catch (FileAlreadyExistsException e) {
                System.out.println(e);
            }
        }
    }

    public static Reseau chargerFichier(Reseau reseau, String filePath, int lambda) throws ImportException, InvalideReseauException{
        System.out.println("Votre fichier sera chargé depuis le dossier : src/ressources/configurations/");
        filePath = "src" + File.separator + "ressources" + File.separator + "configurations" + File.separator + filePath + ".txt";
        
        reseau = ParseFile.importerReseau(filePath);
        reseau.validerReseau();
        reseau.setLambda(lambda);
        return reseau ;
    }

    public static void verifArguments(Reseau reseau, Scanner sc, String[] args) throws ArgumentsException{
        switch (args.length){
            case 1 :
                if (verifLambda(args[0])){
                    reseau.setLambda(Integer.parseInt(args[0]));
                    Menu1.menu1(reseau, sc);
                }
                break ;
            case 2 :
                if (verifLambda(args[1])){
                    try { 
                        reseau = chargerFichier(reseau, args[0], Integer.parseInt(args[1]));
                        Menu3.menu3(reseau, sc);
                    } catch(ImportException e){
                        System.out.println(e);
                    } catch (InvalideReseauException e){
                        System.out.println("Le réseau n'est pas valide\n"+ e.getMessage());
                    }
                }
                break ;
            default : 
                throw new ArgumentsException("Le nombre d'arguments dans la ligne de commande n'est pas valide");
        }        
    }

    private static boolean verifLambda(String lambda){
        try {
            Integer.parseInt(lambda);
            return true ;
        } catch (NumberFormatException e){
            System.out.println("Le lambda doit être un entier supérieur ou égale à 0 !");
            return false ;
        }
    }

    public static void corrigerReseau(Reseau reseau){
        reseau.getConnexions().clear();
        for (Generateur g : reseau.getGenerateurs()){
            for (Connexion c : g.getMaisons()){
                reseau.ajouterConnexion2(c);
            }
        }
    }

    public static void optimiserReseau(Reseau reseau){
        reseau = Algorithme2.resoudreReseau(reseau, Algorithme1.epsilonInit);
        UtilMenu.corrigerReseau(reseau);
        try {
            reseau.validerReseau();
        } catch(InvalideReseauException e){
            System.out.println("Le réseau n'est pas valide\n"+ e.getMessage());
        }
        System.out.println(reseau);
        System.out.println("Le coût du réseau vaut : "+ reseau.calculerCoutReseau());
    }
}
