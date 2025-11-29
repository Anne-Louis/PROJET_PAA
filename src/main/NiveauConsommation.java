package main;

/* enumeration pour les niveau de consommation des maison les valeur de consommation affecter a leur categories :) */
public enum NiveauConsommation {
    BASSE(10),
    NORMALE(20),
    FORTE(40);

    private final int valeur;

    NiveauConsommation(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    /**
     * Convertit une chaîne en niveau de consommation
     * Accepte "NORMAL" (format fichier) et "NORMALE" (format interne)
     */
    public static NiveauConsommation fromString(String niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau de consommation ne peut pas être null");
        }
        switch (niveau.toUpperCase()) {
            case "BASSE": return BASSE;
            case "NORMALE": return NORMALE;
            case "NORMAL": return NORMALE; // Accepte les deux formats
            case "FORTE": return FORTE;
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

    @Override
    public String toString() {
        return name() + " (" + valeur + "kW)";
    }
}