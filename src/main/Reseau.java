package main;

import java.util.*;

public class Reseau {
    private List<Maison> maisons;
    private List<Generateur> generateurs;
    private List<Connexion> connexions;
    
    public Reseau() {
        this.maisons = new ArrayList<>();
        this.generateurs = new ArrayList<>();
        this.connexions = new ArrayList<>();
    }
    
    // Méthodes d'ajout
    public void ajouterMaison(Maison maison) {
        if (trouverMaisonParNom(maison.getNom()) == null) {
            maisons.add(maison);
        }
    }
    
    public void ajouterGenerateur(Generateur generateur) {
        if (trouverGenerateurParNom(generateur.getNom()) == null) {
            generateurs.add(generateur);
        }
    }
    
    public void ajouterConnexion(Connexion connexion) {
        if (!connexions.contains(connexion)) {
            connexions.add(connexion);
            connexion.getMaison().setGenerateur(connexion.getGenerateur());
            connexion.getGenerateur().ajouterConnexion(connexion);
        }
    }
    
    // Méthode pour connecter une maison à un générateur
    public void connecterMaisonGenerateur(String nomMaison, String nomGenerateur) {
        Maison maison = trouverMaisonParNom(nomMaison);
        Generateur generateur = trouverGenerateurParNom(nomGenerateur);
        
        if (maison != null && generateur != null) {
            retirerConnexionMaison(maison);
            Connexion nouvelleConnexion = new Connexion(maison, generateur);
            ajouterConnexion(nouvelleConnexion);
        }
    }
    
    // Méthode pour modifier une connexion existante
    public boolean modifierConnexion(String maisonNom, String ancienGenerateurNom, String nouveauGenerateurNom) {
        Maison maison = trouverMaisonParNom(maisonNom);
        Generateur ancienGenerateur = trouverGenerateurParNom(ancienGenerateurNom);
        Generateur nouveauGenerateur = trouverGenerateurParNom(nouveauGenerateurNom);
        
        if (maison == null || ancienGenerateur == null || nouveauGenerateur == null) {
            return false;
        }
        
        Connexion ancienneConnexion = trouverConnexion(maisonNom, ancienGenerateurNom);
        if (ancienneConnexion == null) {
            return false;
        }
        
        retirerConnexionMaison(maison);
        connecterMaisonGenerateur(maisonNom, nouveauGenerateurNom);
        return true;
    }
    
    // Méthode pour retirer une connexion pour une maison
    private void retirerConnexionMaison(Maison maison) {
        Connexion connexionASupprimer = null;
        for (Connexion connexion : connexions) {
            if (connexion.getMaison().equals(maison)) {
                connexionASupprimer = connexion;
                break;
            }
        }
        
        if (connexionASupprimer != null) {
            connexions.remove(connexionASupprimer);
            connexionASupprimer.getGenerateur().retirerConnexion(connexionASupprimer);
            maison.setGenerateur(null);
        }
    }
    
    // Méthodes de recherche
    public Maison trouverMaisonParNom(String nom) {
        for (Maison maison : maisons) {
            if (maison.getNom().equals(nom)) {
                return maison;
            }
        }
        return null;
    }
    
    public Generateur trouverGenerateurParNom(String nom) {
        for (Generateur generateur : generateurs) {
            if (generateur.getNom().equals(nom)) {
                return generateur;
            }
        }
        return null;
    }
    
    public Connexion trouverConnexion(String nomMaison, String nomGenerateur) {
        for (Connexion connexion : connexions) {
            if (connexion.getMaison().getNom().equals(nomMaison) && 
                connexion.getGenerateur().getNom().equals(nomGenerateur)) {
                return connexion;
            }
        }
        return null;
    }
    
    // Validation des connexions
    public List<String> validerConnexions() {
        List<String> problemes = new ArrayList<>();
        for (Maison maison : maisons) {
            if (maison.getGenerateur() == null) {
                problemes.add("La maison " + maison.getNom() + " n'est connectée à aucun générateur");
            }
        }
        return problemes;
    }
    
    // Vérifier si le réseau est valide
    public boolean estValide() {
        return validerConnexions().isEmpty();
    }
    
    // Calcul du coût
    public double calculerCoutTotal(int lambda) {
        double dispersion = calculerDispersion();
        double surcharge = calculerSurcharge();
        return dispersion + lambda * surcharge;
    }
    
    private double calculerDispersion() {
        if (generateurs.isEmpty()) return 0.0;
        
        double sommeTaux = 0.0;
        for (Generateur generateur : generateurs) {
            sommeTaux += generateur.getTauxUtilisation();
        }
        double moyenneTaux = sommeTaux / generateurs.size();
        
        double sommeEcarts = 0.0;
        for (Generateur generateur : generateurs) {
            sommeEcarts += Math.abs(generateur.getTauxUtilisation() - moyenneTaux);
        }
        return sommeEcarts;
    }
    
    private double calculerSurcharge() {
        double surchargeTotale = 0.0;
        for (Generateur generateur : generateurs) {
            if (generateur.estEnSurcharge()) {
                surchargeTotale += (generateur.getTauxUtilisation() - 1.0);
            }
        }
        return surchargeTotale;
    }
    
    // Méthode pour afficher les détails du coût
    public String afficherDetailsCout(int lambda) {
        double dispersion = calculerDispersion();
        double surcharge = calculerSurcharge();
        double coutTotal = calculerCoutTotal(lambda);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- DÉTAILS DU COÛT ---\n");
        sb.append(String.format("Dispersion (équilibre): %.4f\n", dispersion));
        sb.append(String.format("Pénalité de surcharge: %.4f\n", surcharge));
        sb.append(String.format("Coefficient λ: %d\n", lambda));
        sb.append(String.format("COÛT TOTAL: %.4f\n", coutTotal));
        
        sb.append("\nDétails par générateur:\n");
        for (Generateur generateur : generateurs) {
            sb.append(String.format("  - %s: Charge=%d/%d kW, Taux=%.1f%%%s\n", 
                generateur.getNom(),
                generateur.getChargeActuelle(),
                generateur.getCapaciteMax(),
                generateur.getTauxUtilisation() * 100,
                generateur.estEnSurcharge() ? " (SURCHARGE!)" : ""));
        }
        
        return sb.toString();
    }
    
    // Méthodes utilitaires
    public int getNombreMaisonsConnectees() {
        return (int) maisons.stream()
                .filter(maison -> maison.getGenerateur() != null)
                .count();
    }
    
    public int getConsommationTotale() {
        return maisons.stream()
                .mapToInt(Maison::getConsommation)
                .sum();
    }
    
    public int getCapaciteTotale() {
        return generateurs.stream()
                .mapToInt(Generateur::getCapaciteMax)
                .sum();
    }
    
    // Getters
    public List<Maison> getMaisons() { return new ArrayList<>(maisons); }
    public List<Generateur> getGenerateurs() { return new ArrayList<>(generateurs); }
    public List<Connexion> getConnexions() { return new ArrayList<>(connexions); }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RÉSEAU ÉLECTRIQUE ===\n");
        
        sb.append("\nGénérateurs (").append(generateurs.size()).append("):\n");
        for (Generateur generateur : generateurs) {
            sb.append("  - ").append(generateur).append("\n");
        }
        
        sb.append("\nMaisons (").append(maisons.size()).append("):\n");
        for (Maison maison : maisons) {
            sb.append("  - ").append(maison).append("\n");
        }
        
        sb.append("\nConnexions (").append(connexions.size()).append("):\n");
        for (Connexion connexion : connexions) {
            sb.append("  - ").append(connexion).append("\n");
        }
        
        sb.append("\nRésumé: ").append(getNombreMaisonsConnectees())
          .append("/").append(maisons.size()).append(" maisons connectées, ")
          .append("Consommation: ").append(getConsommationTotale()).append(" kW, ")
          .append("Capacité: ").append(getCapaciteTotale()).append(" kW");
        
        return sb.toString();
    }
}