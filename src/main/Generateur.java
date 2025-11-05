package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un générateur électrique dans le réseau.
 */
public class Generateur {
    /** Nom unique du générateur */
    private String nom ;

    /** Capacité maximale du générateur en kiloWatts (kW) */
    private double capacite ;

    /** Liste des connexions associées à ce générateur (maison alimentées) */
    private List<Connexion> maisons ;

    /**
     * Crée un nouveau générateur avec un nom, une capacité et une liste de connexions existante.
     * @param nom le nom du générateur
     * @param capacite la capacité maximale du générateur (en kW)
     * @param maisons la liste des connexions associées
     */
    public Generateur(String nom, double capacite, List <Connexion> maisons){
        this.nom = nom ;
        this.capacite = capacite ;
        this.maisons = maisons ;
    }

    /**
     * Crée un nouveau générateur avec un nom et une capacité.
     * @param nom le nom du générateur
     * @param capacite la capacité maximale du générateur (en kW)
     */
    public Generateur(String nom, double capacite){
        this.nom = nom ;
        this.capacite = capacite ;
        this.maisons = new ArrayList<>() ;
    }

    /**
     * Ajoute une connexion au générateur.
     * @param c la connexion à ajouter
     */
    public void ajouterConnexion(Connexion c){
        this.maisons.add(c) ;
    }

    /**
     * Supprime une connexion au générateur.
     * @param c la connexion à supprimer
     */
    public void supprimerConnexion(Connexion c){
        this.maisons.remove(c);
    }

    /**
     * Retourne le nom du générateur.
     * @return le nom du générateur
     */
    public String getNom(){
        return nom ;
    }

    /**
    * Retourne la capacité maximale du générateur (en kW).
    * @return la capacité du générateur
    */
    public double getCapacite(){
        return capacite ;
    }

    /**
     * Retourne la liste de connexions associées au générateur.
     * @return la liste de connexions du générateur
     */
    public List<Connexion> getMaisons(){
        return maisons ;
    }

    /**
     * Met à jour le nom du générateur.
     * @param nom le nouveau nom du générateur
     */
    public void setNom(String nom){
        this.nom = nom ;
    }

    /**
     * Met à jour la capacité du générateur.
     * @param capacite la nouvelle capacité du générateur
     */
    public void setcapacite(double capacite){
        this.capacite = capacite ;
    }

    /**
     * Met à jour la liste de connexions du générateur.
     * @param maisons la nouvelle liste de connexions
     */
    public void setMaisons(List<Connexion> maisons){
        this.maisons = maisons ;
    }

    /**
     * Calcule la charge totale actuelle du générateur.
     * @return la charge totale du générateur (en kW)
     */
    public double calculCharge(){
        double charge = 0 ;
        for (Connexion connexion : maisons) {
            charge += connexion.getMaison().getConsommation() ;
        }
        return charge ;
    }

    /**
     * Calcule le taux d'utilisation du générateur.
     * @return le taux d'utilisation du générateur
     */
    public double calculTauxUtilisation(){
        return calculCharge()/capacite ;
    }
}
