package main;
import java.util.List;
import java.util.ArrayListe;

public class Reseau {
    private liste<Generateur> generateurs;
    private liste<Maison> maisons;
    private Liste<Connexion> connexions;
    private int lampda;
    private double totalCout;
    
    public Reseau(){
        generateurs = new ArrayListe<Generateur>();
        maisons = new ArrayListe<Maison>();
        connexions = new ArrayListe<Connexion>();
        lampda = 10; totalCout = 0.0;
    }
    public Reseau(liste<Generateur> gens, liste<Maison> msns, liste<Connexion> conns){
        this.generateurs = gens;
        this.maisons = msns;
        this.connexions = conns;
        lampda = 10;totalCout = 0.0;
    }
    public Reseau(Reseau r){
        this.generateurs = r.getGenerateurs();
        this.maisons = r.getMaisons();
        this.connexions = r.getConnexions();
        this.lampda = r.lampda;
        this.totalCout = r.totalCout;
    }
    
    @Override
    public string toSring(){
        String res = "RESEAU ELECTRIQUE\n";
        res += "Generateurs:\n|";
        for (Generateur gen : this.generateurs){
            res +="--------------------------------------------------------------------------------------\n|\t"+ gen.getNom() + "-\t" + gen.getCapacite() + " KW \t Taux d'utilisation: " + Math.round(gen.calculTauxUtilisation()*100)+ "%\n";
            for (Connexion msn : gen.getMaisons()) {
                res += "\t" + msn.getMaison().getNom() + "("+msn.getMaison().getNiveauConsommation()+")|\n";
            }
        }     
        res += "--------------------------------------------------------------------------------------\n";
        
        return res;
    }

    public double calculerCoutReseau(){
        double capaciteMoyenne = 0;
        double dispertionReseau = 0;
        double surchargeReseau = 0;

        for (Generateur gen : this.generateurs)
            capaciteMoyenne += gen.calculTauxUtilisation();
        capaciteMoyenne /= this.generateurs.size();
        for (Generateur gen : this.generateurs){
            dispertionReseau += Math.abs(gen.calculTauxUtilisation() - capaciteMoyenne);
            surchargeReseau += Math.max(0, gen.calculTauxUtilisation() - 1);
        }
        this.totalCout = (surchargeReseau * lampda) + dispertionReseau;

        return totalCout;
    }

    public boolean modifierConnexion(Connexion oldConn, Connexion newConn){
        int index = this.connexions.indexOf(oldConn);
        if (index == -1 || newConn == null)
            return false;
        this.connexions.set(index, newConn);
        return true;
    }

    public boolean validerReseau(){
        double totalCapacite = 0;
        double totalConsommation = 0;
        int nbConnexions = 0;

        if (this.generateurs.size() == 0 || this.maisons.size() == 0)
            return false;
        for (Generateur gen : this.generateurs)
            totalCapacite += gen.getCapacite();
        for (Maison msn : this.maisons){
            totalConsommation += msn.getConsommation();
            for(Connexion cons : this.connexions)
                if (cons.getMaison().equals(msn))
                    nbConnexions++;
            if (nbConnexions != 0) 
                return false;      
        }
        
        return (totalCapacite >= totalConsommation);
        return true;
    } 
    public boolean ajouterMaison(Maison msn){
        if (msn == null)
            return false;
        return this.maisons.add(msn);
    }

    public boolean ajouterGenerateur(Generateur gen){
        if (gen == null)
            return false;
        return this.generateurs.add(gen);
    }
    public boolean ajouterConnexion(Connexion conn){
        if (conn == null)
            return false;
        return this.connexions.add(conn);
    }


    public getGenerateurs(){
        return this.generateurs;
    }
    public getMaisons(){
        return this.maisons;
    }
    public getConnexions(){
        return this.connexions;
    }
    public void setMaisons(liste<Maison> msns){
        this.maisons = msns;
    }
    public void setConnexions(liste<Connexion> conns){
        this.connexions = conns;
    }
    public void setGenerateurs(liste<Generateur> gens){
        this.generateurs = gens;
    }
}
