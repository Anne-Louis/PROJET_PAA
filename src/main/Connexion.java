package main;

public class Connexion {
    private Maison maison;
    private Generateur generateur;

    public Connexion(Maison maison, Generateur generateur) {
        this.maison = maison;
        this.generateur = generateur;
    }

    // Getters
    public Maison getMaison() { 
        return maison; 
    }
    public Generateur getGenerateur() {
        return generateur; 
    }

    public void setGenerateur(Generateur generateur){
        this.generateur = generateur;
    }
    public void setMaison(Maison maison){
        this.maison = maison;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Connexion that = (Connexion) obj;
        return maison.equals(that.maison) && generateur.equals(that.generateur);
    }

    @Override
    public String toString() {
        return String.format("Connexion: %s → %s", maison.getNom(), generateur.getNom());
    }
}