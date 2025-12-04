package main.components;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Représente un réseau électrique composé de générateurs, de maisons et de connexions.
 * Cette classe permet de construire, valider et analyser un réseau électrique.
 * Elle propose des calculs tels que le coût du réseau, la surcharge ou la dispersion.
 */
public class Reseau {
    /** Liste des générateurs présents dans le réseau. */
    private List<Generateur> generateurs;

    /** Liste des maisons présentes dans le réseau. */
    private List<Maison> maisons;

    /** Liste des connexions entre les générateurs et les maisons. */
    private List<Connexion> connexions;

    /** Facteur de séverité de pénalisation utilisé pour le calcul du coût du réseau. 
     * Pour la première partie du projet, il sera fixé arbitrairement à 10.
     */
    private int lampda;

    /** Coût total du réseau. */
    private double totalCout;
    
    /** Crée un nouveau réseau vide avec les listes initialisées. */
    public Reseau(){
        generateurs = new ArrayList<Generateur>();
        maisons = new ArrayList<Maison>();
        connexions = new ArrayList<Connexion>();
        lampda = 10; totalCout = 1.0;
    }

    /**
     * Crée un nouveau réseau à partir de listes existantes de générateurs, maisons et connexions.
     * @param gens la liste de générateurs
     * @param msns la liste de maisons
     * @param conns la liste de connexions
     */
    public Reseau(List<Generateur> gens, List<Maison> msns, List<Connexion> conns){
        this.generateurs = gens;
        this.maisons = msns;
        this.connexions = conns;
        lampda = 10;totalCout = 0.0;
    }

    /**
     * Crée un nouveau réseau à partir d'un réseau existant.
     * @param r le réseau à copier
     */
    public Reseau(Reseau r){
        this.generateurs = new ArrayList<>(r.getGenerateurs());
        this.maisons = new ArrayList<>(r.getMaisons());
        this.connexions = new ArrayList<>(r.getConnexions());
        this.lampda = r.lampda;
        this.totalCout = r.totalCout;
    }
    
    /**
    * Retourne une représentation textuelle du réseau.
    * @return une chaîne de caractère décrivant le réseau
    */
    @Override
    public String toString(){
        String res = "\nRESEAU ELECTRIQUE\n";
        res += "Branchements:\n|";
        for (Generateur gen : this.generateurs){
            res +="--------------------------------------------------------------------------------------\n|\t"+ gen.getNom() + "-\t" + gen.getCapacite() + " KW \t Taux d'utilisation: " + Math.round(gen.calculTauxUtilisation()*100.0)+ "%\n|";
            for (Connexion msn : gen.getMaisons()) {
                res += "\t" + msn.getMaison().getNom() + "("+msn.getMaison().getNiveau() +")   ";
            }
            res += "\n|-------------------------------------------------------------------------------------\n";
        }     
        
        return res;
    }

    /**
     * Calcule le coût total du réseau en fonction :
     * <ul>
     *   <li>de la surcharge des générateurs (dépassement de la capacité maximale)</li>
     *   <li>de la dispersion du réseau (écart des taux d’utilisation par rapport à la moyenne des générateurs)</li>
     * </ul>
     * @return le coût total du réseau arrondi à trois décimales
     */
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
        this.totalCout = ((surchargeReseau * lampda) + dispertionReseau);
        
        return this.totalCout;
    }

    /**
     * Remplace une connexion existante par une nouvelle.
     * @param oldConn l’ancienne connexion à remplacer
     * @param newConn la nouvelle connexion
     * @return true si la modification a été effectuée, false sinon
     */
    public boolean modifierConnexion(Connexion oldConn, Connexion newConn){
        int index = this.connexions.indexOf(oldConn);
        if (index == -1 || newConn == null)
            return false;
        this.connexions.set(index, newConn);
        return true;
    }

    /**
     * Vérifie si le réseau est valide.
     * Le réseau est considéré comme valide si la capacité totale des générateurs
     * est supérieure ou égale à la consommation totale des maisons
     * et si chaque maison est reliée à un seul et unique générateur.
     * @return true si le réseau est valide, false sinon
     */
    public boolean validerReseau(){
        double totalCapacite = 0;
        double totalConsommation = 0;
        int nbConnexions;
        List <Maison> maisonsInvalides = new ArrayList<>();

        if (this.generateurs.size() == 0 || this.maisons.size() == 0){
            System.out.println("Le réseau est vide");
            return false;
        }
        for (Generateur gen : this.generateurs){
            totalCapacite += gen.getCapacite();
        }
        for (Maison msn : this.maisons){
            totalConsommation += msn.getConsommation();
            nbConnexions = 0 ;
            for(Connexion cons : this.connexions){
                if (cons.getMaison().equals(msn))
                    nbConnexions++;
            }
            if (nbConnexions != 1){ 
                maisonsInvalides.add(msn);
            }
        }
        
        if (!maisonsInvalides.isEmpty()){
            for (Maison msnI : maisonsInvalides){
                System.out.println("La maison : " + msnI.getNom() + " n'a pas le bon nombre de connexions !");
            }
            return false ;
        }

        if (totalCapacite >= totalConsommation){
            return true;
        } else {
            System.out.println("La consommation d'énergie dépasse la production");
            return false;
        }
    } 

    /**
     * Ajoute une maison au réseau.
     * @param msn la maison à ajouter
     * @return true si l’ajout a réussi, false sinon
     */
    public boolean ajouterMaison(Maison msn){
        if (msn == null)
            return false;
        return this.maisons.add(msn);
    }

    /**
     * Ajoute un générateur au réseau.
     * @param gen le générateur à ajouter
     * @return true si l’ajout a réussi, false sinon
     */
    public boolean ajouterGenerateur(Generateur gen){
        if (gen == null)
            return false;
        return this.generateurs.add(gen);
    }

    /**
     * Ajoute une connexion au réseau.
     * @param conn la connexion à ajouter
     * @return true si l’ajout a réussi, false sinon
     */
    public boolean ajouterConnexion(Connexion conn){
        if (conn == null)
            return false;
        return this.connexions.add(conn);
    }

    /**
     * Supprime une connexion du réseau.
     * @param c la connexion à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean supprimerConnexion(Connexion c){
        return this.connexions.remove(c) ;
    }

    /**
     * Retourne la liste de générateurs du réseau.
     * @return la liste de générateurs
     */
    public List<Generateur> getGenerateurs(){
        return this.generateurs;
    }

    /**
     * Retourne la liste de maisons du réseau.
     * @return la liste de maisons
     */
    public List<Maison> getMaisons(){
        return this.maisons;
    }

    /**
     * Retourne la liste de connexions du réseau.
     * @return la liste de connexions
     */
    public List<Connexion> getConnexions(){
        return this.connexions;
    }

    /**
     * Met à jour la liste de maisons du réseau.
     * @param msns la nouvelle liste de maisons
     */
    public void setMaisons(List<Maison> msns){
        this.maisons = msns;
    }

    /**
     * Met à jour la liste de connexions du réseau.
     * @param conns la nouvelle liste de connexions
     */
    public void setConnexions(List<Connexion> conns){
        this.connexions = conns;
    }

    /**
     * Met à jour la liste de générateurs du réseau.
     * @param gens la nouvelle liste de générateurs
     */
    public void setGenerateurs(List<Generateur> gens){
        this.generateurs = gens;
    }

    /**
     * Creer une copie profonde de l'instance reseau appelant
     * @return copie de l'instance this.
     */
    public Reseau copierReseau() {
        Reseau copy = new Reseau();

        Map<Generateur, Generateur> mapGen = new HashMap<>();
        Map<Maison, Maison> mapMaison = new HashMap<>();

        for (Generateur g : this.generateurs) {
            Generateur ng = new Generateur(g.getNom(), g.getCapacite());
            mapGen.put(g, ng);
            copy.ajouterGenerateur(ng);
        }

        for (Maison m : this.maisons) {
            Maison nm = new Maison(m.getNom(), m.getNiveau());
            mapMaison.put(m, nm);
            copy.ajouterMaison(nm);
        }

        for (Connexion c : this.connexions) {
            Connexion nc=new  Connexion(mapGen.get(c.getGenerateur()), mapMaison.get(c.getMaison()));
            copy.ajouterConnexion(nc);

            mapGen.get(c.getGenerateur()).ajouterConnexion(nc);
            mapMaison.get(c.getMaison()).setConnexion(nc);
        }

        copy.lampda = this.lampda;
        copy.totalCout = copy.calculerCoutReseau();

        return copy;
    }
}
