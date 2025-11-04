package main;

import java.util.Objects;

public class Connexion {
    private Generateur generateur ;
    private Maison maison ;

    public Connexion(Generateur generateur, Maison maison){
        this.generateur = generateur ;
        this.maison = maison ;
    }

    public Generateur getGenerateur(){
        return generateur ;
    }

    public Maison getMaison(){
        return maison ;
    }

    public void setGenerateur(Generateur generateur){
        this.generateur = generateur ;
    }

    public void setMaison(Maison maison){
        this.maison = maison ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connexion)) return false;
        Connexion c = (Connexion) o;
        return this.generateur.equals(c.generateur)
            && this.maison.equals(c.maison);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generateur, maison);
    }

    @Override
    public String toString(){
        return "Connexion : " + generateur.getNom() + " " + maison.getNom() ;
    }
}
