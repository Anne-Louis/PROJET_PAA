package main;

import java.util.Objects;

/**
 * Représente une connexion entre un générateur et une maison
 * dans le réseau électrique.
 */
public class Connexion {
    /** Générateur associé à cette connexion. */
    private Generateur generateur ;

    /** Maison alimentée par cette connexion. */
    private Maison maison ;

    /**
     * Crée une nouvelle connexion entre un générateur et une maison.
     * @param generateur instance de générateur relié par la connexion
     * @param maison instance de maison reliée par la connexion
     */
    public Connexion(Generateur generateur, Maison maison){
        this.generateur = generateur ;
        this.maison = maison ;
    }

    /**
     * Retourne le générateur associé à cette connexion.
     * @return le générateur de cette connexion
     */
    public Generateur getGenerateur(){
        return generateur ;
    }

    /**
     * Retourne la maison alimentée par cette connexion.
     * @return la maison de cette connexion
     */
    public Maison getMaison(){
        return maison ;
    }

    /**
     * Met à jour le générateur de la connexion.
     * @param generateur le nouveau générateur à associer
     */
    public void setGenerateur(Generateur generateur){
        this.generateur = generateur ;
    }

    /**
     * Met à jour la maison de la connexion.
     * @param maison la nouvelle maison à associé.
     */
    public void setMaison(Maison maison){
        this.maison = maison ;
    }

    /**
     * Compare cette connexion à un autre objet.
     * Deux connexions sont égales si elles relient la même maison
     * au même générateur.
     * @param o l'objet à comparer
     * @return true si les deux connexions sont identiques, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connexion)) return false;
        Connexion c = (Connexion) o;
        return this.generateur.equals(c.generateur)
            && this.maison.equals(c.maison);
    }

    /**
    * Calcule le hashcode de cette connexion, basé sur le générateur et la maison.
    * @return le hashcode de la connexion
    */
    @Override
    public int hashCode() {
        return Objects.hash(generateur, maison);
    }

    /**
    * Retourne une représentation textuelle de la connexion.
    * @return une chaîne de caractère décrivant la connexion
    */
    @Override
    public String toString(){
        return "Connexion : " + generateur.getNom() + " " + maison.getNom() ;
    }
}
