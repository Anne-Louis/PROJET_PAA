package main.io;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

import main.components.*;

/**
 * Classe de sauvegarde dans un fichier sous le format demandé
 * de la solution d'un réseau trouvé par un algorithme.
 */
public class SauvegardeReseau {
    /**
     * @param reseau le reseau à sauvegarder
     * @param fichier le nom du fichier dans lequel on va sauvegarder la solution
     */
    public static void sauvegardeReseau(Reseau reseau, String fichier) throws FileAlreadyExistsException{
        if (fichierExiste(fichier + ".txt")){
            throw new FileAlreadyExistsException("Un fichier contenant une configuration de réseau porte déjà ce nom !");
        }
        fichier = "src/ressources/solutions/" + fichier ;
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

    /**
     * Vérifie si un fichier avec le même nom existe déjà
     * @param fichier le nom de fichier qu'on veut comparer
     * @return true si le fichier existe déjà, false sinon
     */
    private static boolean fichierExiste(String fichier) {
        File dossier = new File("src/ressources/configurations/");
        File[] liste = dossier.listFiles();

        if (liste == null){
            return false ;
        }

        for (File f : liste) {
            if (f.getName().equals(fichier)) {
                return true ;
            }
        }
        return false ;
    }
}
