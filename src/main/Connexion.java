package main;

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
}
