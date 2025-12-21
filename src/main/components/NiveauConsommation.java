package main.components;

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

    /**
     * Convertit une chaîne en niveau de consommation
     * Accepte "NORMAL" (format fichier) et "NORMALE" (format interne)
     */
    public static NiveauConsommation fromString(String niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau de consommation ne peut pas etre null");
        }
        switch (niveau.toUpperCase()) {
            case "BASSE": return BASSE;
            case "NORMALE": return NORMALE;
            case "NORMAL": return NORMALE; // Accepte les deux formats
            case "FORTE": return FORTE;
            case "FORT": return FORTE;
            default: 
                throw new IllegalArgumentException("Niveau de consommation invalide: " + niveau);
        }
    }

    /**
     * Retourne le nom au format utilisé dans les fichiers (NORMAL au lieu de NORMALE)
     */
    public String toFileFormat() {
        switch (this) {
            case NORMALE: return "NORMAL";
            default: return name();
        }
    }

}
