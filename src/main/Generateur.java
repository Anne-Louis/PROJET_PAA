package main;

import java.util.ArrayList;
import java.util.List;

public class Generateur {
    private String nom ;
    private double capacite ;
    private List<Connexion> maisons ;

    public Generateur(String nom, double capacite, List <Connexion> maisons){
        this.nom = nom ;
        this.capacite = capacite ;
        this.maisons = maisons ;
    }

    public Generateur(String nom, double capacite){
        this.nom = nom ;
        this.capacite = capacite ;
        this.maisons = new ArrayList<>() ;
    }

    public void ajouterConnexion(Connexion c){
        this.maisons.add(c) ;
    }

    public void supprimerConnexion(Connexion c){
        this.maisons.remove(c);
    }

    public String getNom(){
        return nom ;
    }

    public double getCapacite(){
        return capacite ;
    }

    public List<Connexion> getMaisons(){
        return maisons ;
    }

    public void setNom(String nom){
        this.nom = nom ;
    }

    public void setcapacite(double capacite){
        this.capacite = capacite ;
    }

    public void setMaisons(List<Connexion> maisons){
        this.maisons = maisons ;
    }

    public double calculCharge(){
        double charge = 0 ;
        for (Connexion connexion : maisons) {
            charge += connexion.getMaison().getConsommation() ;
        }
        return charge ;
    }

    public double calculTauxUtilisation(){
        return calculCharge()/capacite ;
    }
}
