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

}
