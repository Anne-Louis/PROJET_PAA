package main;
import java.util.List;
import java.util.ArrayList;

public class Reseau {
    private List<Generateur> generateurs;
    private List<Maison> maisons;
    private List<Connexion> connexions;
    private int lampda;
    private double totalCout;
    
    public Reseau(){
        generateurs = new ArrayList<Generateur>();
        maisons = new ArrayList<Maison>();
        connexions = new ArrayList<Connexion>();
        lampda = 10; totalCout = 1.0;
    }
    public Reseau(List<Generateur> gens, List<Maison> msns, List<Connexion> conns){
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
    public String toString(){
        String res = "\nRESEAU ELECTRIQUE\n";
        res += "Branchements:\n|";
        for (Generateur gen : this.generateurs){
            res +="--------------------------------------------------------------------------------------\n|\t"+ gen.getNom() + "-\t" + gen.getCapacite() + " KW \t Taux d'utilisation: " + Math.round(gen.calculTauxUtilisation()*100)+ "%\n|";
            for (Connexion msn : gen.getMaisons()) {
                res += "\t" + msn.getMaison().getNom() + "("+msn.getMaison().getNiveau() +")   ";
            }
            res += "\n|-------------------------------------------------------------------------------------\n";
        }     
        
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
        
        return this.totalCout;
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
        
        if (totalCapacite >= totalConsommation)
            return true;
        else 
            return false;
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


    public List<Generateur> getGenerateurs(){
        return this.generateurs;
    }
    public List<Maison> getMaisons(){
        return this.maisons;
    }
    public List<Connexion> getConnexions(){
        return this.connexions;
    }
    public void setMaisons(List<Maison> msns){
        this.maisons = msns;
    }
    public void setConnexions(List<Connexion> conns){
        this.connexions = conns;
    }
    public void setGenerateurs(List<Generateur> gens){
        this.generateurs = gens;
    }
}
