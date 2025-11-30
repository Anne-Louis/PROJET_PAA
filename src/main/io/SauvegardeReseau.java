package main.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.components.Connexion;
import main.components.Generateur;
import main.components.Maison;
import main.components.Reseau;

/**
 * Classe de sauvegarde dans un fichier sous le format demandé
 * de la solution d'un réseau trouvé par un algorithme.
 */
public class SauvegardeReseau {
    /**
     * @param reseau le reseau à sauvegarder
     * @param fichier le nom du fichier dans lequel on va sauvegarder la solution
     */
    public static void sauvegardeReseau(Reseau reseau, String fichier){
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichier)))) {
            for (Generateur gen : reseau.getGenerateurs()){
                pw.println("generateur(" + gen.getNom() + "," + gen.getCapacite() + ").");
            }
            for (Maison msn : reseau.getMaisons()){
                pw.println("maison(" + msn.getNom() + "," + msn.getNiveau() + ").");
            }
            for (Connexion con : reseau.getConnexions()){
                pw.println("connexion(" + con.getGenerateur().getNom() + "," + con.getMaison().getNom() + ").");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de fichier : " + e);
            e.printStackTrace();
        }
    }
}
