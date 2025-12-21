package main.io;

import java.io.*;
import java.util.regex.*;

import main.components.*;
import main.exceptions.*;

/* 
 * Classe dédiée à l'analyse et l'importation de fichiers au format de la partie 2
 * Format: generateur(nom,capacite). maison(nom,niveau). connexion(maison,generateur).
 */
public class ParseFile {
    
    /** 
     * Importe un réseau depuis un fichier au format de la partie 2
     * @param cheminFichier le chemin du fichier à importer
     * @return le réseau importé
     * @throws ImportException si l'importation échoue
     */
    public static Reseau importerReseau(String cheminFichier) throws ImportException {
        if( !validerSyntaxeFichier(cheminFichier) ){
            throw new ImportException("Erreur lors de la validation du fichier");
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            Reseau reseau = new Reseau();
            String ligne;
            int ligneNum = 0;
            
            // Patterns regex pour le nouveau format
            Pattern patternGenerateur = Pattern.compile("generateur\\(([\\w]+),\\s*(\\d+)\\)\\.");
            Pattern patternMaison = Pattern.compile("maison\\(([\\w]+),\\s*(BASSE|NORMAL|FORTE)\\)\\.");
            Pattern patternConnexion = Pattern.compile("connexion\\(([\\w]+),\\s*([\\w]+)\\)\\.");
            
            while ((ligne = reader.readLine()) != null) {
                ligneNum++;
                ligne = ligne.trim();
                if (ligne.isEmpty()) {
                    continue; 
                }
                
                try {
                    Matcher matcherGenerateur = patternGenerateur.matcher(ligne);
                    Matcher matcherMaison = patternMaison.matcher(ligne);
                    Matcher matcherConnexion = patternConnexion.matcher(ligne);
                    
                    if (matcherGenerateur.matches()) {
                        traiterGenerateur(reseau, matcherGenerateur);
                    } else if (matcherMaison.matches()) {
                        traiterMaison(reseau, matcherMaison);
                    } else if (matcherConnexion.matches()) {
                        traiterConnexion(reseau, matcherConnexion, ligneNum, ligne);
                    } else {
                        System.err.println("Erreur ligne " + ligneNum + ": Format non reconnu - " + ligne);
                    }
                    
                } catch (Exception e) {
                    throw new ImportException("Erreur ligne " + ligneNum + ": " + ligne + " - " + e.getMessage());
                }
            }
            
            return reseau;
            
        } catch (FileNotFoundException e) {
            throw new ImportException("Fichier non trouve: " + cheminFichier, e);
        } catch (IOException e) {
            throw new ImportException("Erreur d'entree/sortie lors de la lecture du fichier: " + cheminFichier, e);
        }
    }
    
    /**
     * Traite une ligne de générateur
     * @param reseau le réseau qu'on est en train de construire via le fichier
     * @param matcher le matcher qui utilise un pattern pour vérifier si une ligne est correcte
     */
    private static void traiterGenerateur(Reseau reseau, Matcher matcher) {
        String nom = matcher.group(1);
        int capacite = Integer.parseInt(matcher.group(2));
        reseau.ajouterGenerateur(new Generateur(nom, capacite));
    }
    
    /**
     * Traite une ligne de maison
     * @param reseau le réseau qu'on est en train de construire via le fichier
     * @param matcher le matcher qui utilise un pattern pour vérifier si une ligne est correcte
     */
    private static void traiterMaison(Reseau reseau, Matcher matcher) {
        String nom = matcher.group(1);
        String niveauStr = matcher.group(2);
        NiveauConsommation niveau = NiveauConsommation.fromString(niveauStr);
        reseau.ajouterMaison(new Maison(nom, niveau));
    }
    
    /**
     * Traite une ligne de connexion
     * @param reseau le réseau qu'on est en train de construire via le fichier
     * @param matcher le matcher qui utilise un pattern pour vérifier si une ligne est correcte
     */
    private static void traiterConnexion(Reseau reseau, Matcher matcher, int ligneNum, String ligne) {
        String nom1 = matcher.group(1);
        String nom2 = matcher.group(2);
        
        // Déterminer quel nom est la maison et quel est le générateur
        boolean connexionReussie = false;
        
        // Essayer dans un sens : maison -> générateur
        Maison maison = reseau.trouverMaisonParNom(nom1);
        Generateur generateur = reseau.trouverGenerateurParNom(nom2);
        if (maison != null && generateur != null) {
            reseau.connecterMaisonGenerateur(nom1, nom2);
            connexionReussie = true;
        }
        
        // Essayer dans l'autre sens : générateur -> maison
        if (!connexionReussie) {
            maison = reseau.trouverMaisonParNom(nom2);
            generateur = reseau.trouverGenerateurParNom(nom1);
            if (maison != null && generateur != null) {
                reseau.connecterMaisonGenerateur(nom2, nom1);
                connexionReussie = true;
            }
        }
        
        if (!connexionReussie) {
            System.err.println("Erreur ligne " + ligneNum + ": Connexion invalide - " + ligne);
        }
    }
    
    /**
     * Valide la syntaxe d'un fichier sans l'importer
     * @param cheminFichier le chemin du fichier à valider
     * @return true si le fichier est valide, false sinon
     */
    public static boolean validerSyntaxeFichier(String cheminFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int ligneNum = 0;
            
            Pattern patternGenerateur = Pattern.compile("generateur\\(([\\w]+),\\s*(\\d+)\\)\\.");
            Pattern patternMaison = Pattern.compile("maison\\(([\\w]+),\\s*(BASSE|NORMAL|FORTE)\\)\\.");
            Pattern patternConnexion = Pattern.compile("connexion\\(([\\w]+),\\s*([\\w]+)\\)\\.");

            int nbGenerateur = 0;
            int nbMaison = 0;
            int nbConnexion = 0;
            
            while ((ligne = reader.readLine()) != null) {
                ligneNum++;
                ligne = ligne.trim();
                if (ligne.isEmpty()) {
                    continue;
                }
                
                Matcher matcherGenerateur = patternGenerateur.matcher(ligne);
                Matcher matcherMaison = patternMaison.matcher(ligne);
                Matcher matcherConnexion = patternConnexion.matcher(ligne);
                
                if(matcherGenerateur.matches())     nbGenerateur++;
                else if(matcherMaison.matches())    nbMaison++;
                else if(matcherConnexion.matches()) nbConnexion++;
                else{
                    System.err.println("Erreur de syntaxe ligne " + ligneNum + ": " + ligne);
                    return false;
                }

                if(matcherGenerateur.matches() && ((nbMaison > 0) || nbConnexion > 0)){
                    System.err.println("Erreur d'ordre. generateur apres maison ou connexion \n ligne" + ligneNum+ ": " + ligne);
                    return false;
                }
                else if(matcherMaison.matches() && (nbConnexion != 0 || nbGenerateur == 0)){
                    System.err.println("Erreur d'ordre. Maison avant generateur ou apres connexion  \nligne " + ligneNum+ ": " + ligne);
                    return false;
                }
                else if(matcherConnexion.matches() && (nbGenerateur == 0 || nbMaison == 0) ){
                    System.err.println("Erreur d'ordre. Connexion avant Generateur ou maison \n ligne " + ligneNum+ ": " + ligne);
                    return false;
                }
            }
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la validation: " + e.getMessage());
            return false;
        }
    }
}
