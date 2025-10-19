package main;

public class Maison {
    private String nom;
    private NiveauConsommation niveau;
    private Connexion connexion;

    public Maison(String nom, NiveauConsommation niveau) {
        this.nom = nom;
        this.niveau = niveau;
        this.connexion = null;
    }

    public String getNom() {
        return nom;
    }

    public NiveauConsommation getNiveau() {
        return niveau;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public int getConsommation() {
        return niveau.getValeur();
    }

    @Override
    public String toString() {
        String ConNom = (connexion != null) ? connexion.getGenerateur().getNom() : "Aucun";
        return "Maison " + nom +
               " (" + niveau + ", " + getConsommation() + " kW) → Générateur : " + ConNom;
    }
}
