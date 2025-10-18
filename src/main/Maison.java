package main;

public class Maison {
    private String nom;
    private NiveauConsommation niveau;
    private Generateur generateur;

    public House(String nom, NiveauConsommation niveau) {
        this.nom = nom;
        this.niveau = niveau;
        this.generateur = null;
    }

    public String getNom() {
        return nom;
    }

    public NiveauConsommation getNiveau() {
        return niveau;
    }

    public Generateur getGenerateur() {
        return generateur;
    }

    public void setGenerator(Generateur generateur) {
        this.generateur = generateur;
    }

    public int getConsommation() {
        return niveu.getValeur();
    }

    @Override
    public String toString() {
        String genNom = (generateur != null) ? generateur.getNom() : "Aucun";
        return "Maison " + nom +
               " (" + niveau + ", " + getConsommation() + " kW) → Générateur : " + genNom;
    }
}
