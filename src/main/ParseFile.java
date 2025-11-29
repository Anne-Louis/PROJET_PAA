package main;

import java.io.*;
import java.util.regex.*;

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
                if (ligne.isEmpty() || ligne.startsWith("%") || ligne.startsWith("#")) {
                    continue; // Ignorer les lignes vides et les commentaires
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
                        System.err.println("Warning ligne " + ligneNum + ": Format non reconnu - " + ligne);
                    }
                    
                } catch (Exception e) {
                    throw new ImportException("Erreur ligne " + ligneNum + ": " + ligne + " - " + e.getMessage());
                }
            }
            
            return reseau;
            
        } catch (FileNotFoundException e) {
            throw new ImportException("Fichier non trouvé: " + cheminFichier, e);
        } catch (IOException e) {
            throw new ImportException("Erreur d'entrée/sortie lors de la lecture du fichier: " + cheminFichier, e);
        }
    }
    
    /**
     * Traite une ligne de générateur
     */
    private static void traiterGenerateur(Reseau reseau, Matcher matcher) {
        String nom = matcher.group(1);
        int capacite = Integer.parseInt(matcher.group(2));
        reseau.ajouterGenerateur(new Generateur(nom, capacite));
    }
    
    /**
     * Traite une ligne de maison
     */
    private static void traiterMaison(Reseau reseau, Matcher matcher) {
        String nom = matcher.group(1);
        String niveauStr = matcher.group(2);
        NiveauConsommation niveau = NiveauConsommation.fromString(niveauStr);
        reseau.ajouterMaison(new Maison(nom, niveau));
    }
    
    /**
     * Traite une ligne de connexion
     */
    private static void traiterConnexion(Reseau reseau, Matcher matcher, int ligneNum, String ligne) {
        String nom1 = matcher.group(1);
        String nom2 = matcher.group(2);
        
        // Déterminer quel nom est la maison et quel est le générateur
        boolean connexionReussie = false;
        
        // Essayer dans un sens : maison → générateur
        Maison maison = reseau.trouverMaisonParNom(nom1);
        Generateur generateur = reseau.trouverGenerateurParNom(nom2);
        if (maison != null && generateur != null) {
            reseau.connecterMaisonGenerateur(nom1, nom2);
            connexionReussie = true;
        }
        
        // Essayer dans l'autre sens : générateur → maison
        if (!connexionReussie) {
            maison = reseau.trouverMaisonParNom(nom2);
            generateur = reseau.trouverGenerateurParNom(nom1);
            if (maison != null && generateur != null) {
                reseau.connecterMaisonGenerateur(nom2, nom1);
                connexionReussie = true;
            }
        }
        
        if (!connexionReussie) {
            System.err.println("Warning ligne " + ligneNum + ": Connexion invalide - " + ligne);
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
            
            while ((ligne = reader.readLine()) != null) {
                ligneNum++;
                ligne = ligne.trim();
                if (ligne.isEmpty() || ligne.startsWith("%") || ligne.startsWith("#")) {
                    continue;
                }
                
                Matcher matcherGenerateur = patternGenerateur.matcher(ligne);
                Matcher matcherMaison = patternMaison.matcher(ligne);
                Matcher matcherConnexion = patternConnexion.matcher(ligne);
                
                if (!matcherGenerateur.matches() && !matcherMaison.matches() && !matcherConnexion.matches()) {
                    System.err.println("Erreur de syntaxe ligne " + ligneNum + ": " + ligne);
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