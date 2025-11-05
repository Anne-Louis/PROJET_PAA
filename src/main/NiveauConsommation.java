package main;

/** Représente les différents niveaux de consommation électrique d'une maison (en kW). */
public enum NiveauConsommation {
    /** Maison à basse consommation : 10 kW. */
    BASSE(10),

    /** Maison à consommation normale : 20 kW. */
    NORMALE(20),

    /** Maison à forte consommation : 40 kW. */
    FORTE(40);

    /** Consommation associée à ce niveau. */
    private final int valeur;

    /**
     * Constructeur interne de l'énumération.
     * @param valeur la consommation électrique en kW associée au niveau
     */
    NiveauConsommation(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Retourne la valeur de consommation associée à ce niveau.
     * @return la consommation en kilowatts
     */
    public int getValeur() {
        return valeur;
    }

}
