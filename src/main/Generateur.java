package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Generateur {
    private String nom;
    private int capaciteMax;
    private List<Connexion> connexions;

    public Generateur(String nom, int capaciteMax) {
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.connexions = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public List<Connexion> getConnexions() {
        return new ArrayList<>(connexions);
    }

    // Ajouter une connexion à ce générateur
    public void ajouterConnexion(Connexion connexion) {
        if (!connexions.contains(connexion) && connexion.getGenerateur().equals(this)) {
            connexions.add(connexion);
        }
    }

    // Retirer une connexion de ce générateur
    public void retirerConnexion(Connexion connexion) {
        connexions.remove(connexion);
    }

    // Retirer toutes les connexions
    public void retirerToutesConnexions() {
        // Créer une copie pour éviter ConcurrentModificationException
        for (Connexion connexion : new ArrayList<>(connexions)) {
            retirerConnexion(connexion);
        }
    }

    // Obtenir les maisons connectées (via les connexions)
    public List<Maison> getMaisonsConnectees() {
        return connexions.stream()
                .map(Connexion::getMaison)
                .collect(Collectors.toList());
    }

    // Calculer la charge actuelle du générateur
    public int getChargeActuelle() {
        return connexions.stream()
                .mapToInt(connexion -> connexion.getMaison().getConsommation())
                .sum();
    }

    // Calculer le taux d'utilisation (charge actuelle / capacité max)
    public double getTauxUtilisation() {
        return (double) getChargeActuelle() / capaciteMax;
    }

    // Vérifier si le générateur est en surcharge
    public boolean estEnSurcharge() {
        return getChargeActuelle() > capaciteMax;
    }

    // Calculer le niveau de surcharge (0 si pas de surcharge)
    public int getNiveauSurcharge() {
        return Math.max(0, getChargeActuelle() - capaciteMax);
    }


    @Override
    public String toString() {
        return String.format("Générateur %s (Capacité: %d kW, Charge: %d kW, Taux: %.1f%%, Maisons: %d)", 
                           nom, capaciteMax, getChargeActuelle(), getTauxUtilisation() * 100, connexions.size());
    }
}