package main;

/**
 * Représente une maison dans le réseau.
 */
public class Maison {
    /** Nom unique de la maison. */
    private String nom;

    /** Niveau de consommation de la maison en kiloWatts (kW). */
    private NiveauConsommation niveau;

    /** Connexion associée à la maison. */
    private Connexion connexion;

    /**
     * Crée une nouvelle maison avec un nom et un niveau de consommation.
     * @param nom le nom de la maison
     * @param niveau le niveau de consommation de la maison
     */
    public Maison(String nom, NiveauConsommation niveau) {
        this.nom = nom;
        this.niveau = niveau;
        this.connexion = null;
    }

    /**
     * Retourne le nom de la maison.
     * @return le nom de la maison
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le type de consommation de la maison.
     * @return le type de consommation de la maison
     */
    public NiveauConsommation getNiveau() {
        return niveau;
    }

    /**
     * Retourne la connexion associée à la maison.
     * @return la connexion associée à la maison
     */
    public Connexion getConnexion() {
        return connexion;
    }

    /**
     * Met à jour la connexion associée à la maison.
     * @param connexion la nouvelle connexion 
     */
    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    /**
     * Retourne le niveau de consommation de la maison (en kW).
     * @return le niveau de consommation de la maison
     */
    public int getConsommation() {
        return niveau.getValeur();
    }

    /**
     * Met à jour le type de consommation de la maison.
     * @param niveau le nouveau type de consommation de la maison
     */
    public void setConsommation(NiveauConsommation niveau) {
        this.niveau = niveau ;
    }

    /**
    * Retourne une représentation textuelle de la maison.
    * @return une chaîne de caractère décrivant la maison
    */
    @Override
    public String toString() {
        String ConNom = (connexion != null) ? connexion.getGenerateur().getNom() : "Aucun";
        return "Maison " + nom + " (" + niveau + ", " + getConsommation() + " kW) → Générateur : " + ConNom;
    }
}
