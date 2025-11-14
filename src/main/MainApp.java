package main;

import java.util.Scanner ;

/**
 * Classe principale permettant de gérer l'application de simulation d'un réseau électrique.
 * Elle permet à l'utilisateur, via un menu textuel, de :
 * <ul>
 *   <li>Créer des générateurs, des maisons et leurs connexions (Menu 1).</li>
 *   <li>Modifier un réseau existant et calculer son coût (Menu 2).</li>
 * </ul>
 */
public class MainApp {
    /** Réseau électrique géré par l'application. */
    private static Reseau reseau = new Reseau() ;

    /** Scanner utilisé pour lire les entrées utilisateur. */
    private static Scanner sc = new Scanner(System.in) ;

    /**
     * Permet à l'utilisateur de créer un générateur.
     * Si un générateur du même nom existe déjà, sa capacité est mise à jour.
     */
    public static void creerGenerateur(){
        System.out.println("Veuillez saisir le générateur à enregistrer ainsi que sa puissance (ex : G1 60) : ");
        String ligne = sc.nextLine() ;
        String nom = ligne.split("\\s")[0] ;
        double cap = Double.parseDouble(ligne.split("\\s")[1]) ;

        for (Generateur g : reseau.getGenerateurs()){
            if (g.getNom().equals(nom)){
                g.setcapacite(cap);
                System.out.println("Le générateur " + nom + " existe déjà, sa capacité à été mise à jour : " + cap + "kW") ;
                return ;
            }
        }
        Generateur gen = new Generateur(nom, cap);
        reseau.ajouterGenerateur(gen) ;
        System.out.println("Le générateur " + gen.getNom() + " a bien été crée !");
    }

    /**
     * Permet à l'utilisateur de créer une maison.
     * Si une maison du même nom existe déjà, sa consommation est mise à jour.
     */
    public static void creerMaison(){
        System.out.println("Veuillez saisir la maison à enregistrer ainsi que sa consommation (ex : M1 NORMALE) : ");
        String ligne = sc.nextLine() ;
        String nom = ligne.split("\\s")[0] ;
        NiveauConsommation cons = NiveauConsommation.valueOf(ligne.split("\\s")[1]) ;
                    
            for (Maison m : reseau.getMaisons()){
                if (m.getNom().equals(nom)){
                    m.setConsommation(cons) ;
                    System.out.println("La maison " + nom + " existe déjà, sa consommation à été mise à jour : " + cons) ;
                    return ;
                }
            }
            Maison maison = new Maison(nom, cons);
            reseau.ajouterMaison(maison) ;
            System.out.println("La maison " + maison.getNom() + " a bien été crée !");
    }

    /**
     * Permet de construire une connexion à partir d'un générateur et d'une maison
     * rentrés à l'écrit par l'utilisateur
     * @param message le message à envoyer à l'utilisateur
     * @return connexion la connexion construite à partir du générateur et de la maison
     */
    public static Connexion enregistrerConnexion(String message){
        System.out.println(message);
        String ligne = sc.nextLine() ;
        Generateur genC = null ;
        Maison maiC = null ;

        if (ligne.startsWith("G")){
            for (Generateur g : reseau.getGenerateurs()){
                if (g.getNom().equals(ligne.split("\\s")[0])){
                    genC = g ;
                }
            }
            for (Maison m : reseau.getMaisons()){
                if (m.getNom().equals(ligne.split("\\s")[1])){
                    maiC = m ;
                }
            }
        } else if (ligne.startsWith("M")){
            for (Generateur g : reseau.getGenerateurs()){
                if (g.getNom().equals(ligne.split("\\s")[1])){
                    genC = g ;
                }
            }
            for (Maison m : reseau.getMaisons()){
                if (m.getNom().equals(ligne.split("\\s")[0])){
                    maiC = m ;
                }
            }
        }

        if (genC == null){
            System.out.println("Le générateur n'existe pas !");
            return null;
        }
        if (maiC == null){
            System.out.println("La maison n'existe pas !");
            return null;
        }

        return new Connexion(genC, maiC) ;
    }

    /**
     * Permet à l'utilisateur de créer une connexion entre un générateur et une maison existants.
     * La méthode vérifie l'existence des deux objets et empêche la création de doublons.
     */
    public static void creerConnexion(){
        Connexion con = enregistrerConnexion("Veuillez saisir la connexion à enregistrer  (ex : G1 M1 ou M1 G1) : ") ;

        if (con == null){
            return ;
        }
        for (Connexion c : reseau.getConnexions()){
            if (con.equals(c)){
                System.out.println("Cette connexion existe déjà") ;
                return;
            }
        }
        con.getGenerateur().ajouterConnexion(con);
        con.getMaison().setConnexion(con) ;
        reseau.ajouterConnexion(con) ;
        System.out.println("La connexion " + con.toString() + " a bien été crée !");
    }

    /**
     * Permet à l'utilisateur de supprimer une connexion existante.
     */
    public static boolean supprimerConnexion(){
        Connexion con = enregistrerConnexion("Veuillez saisir la connexion que vous souhaitez modifier : ");
        if (con == null){
            System.out.println("Cette connexion n'existe pas !") ;
            return false;
        }
                    
        reseau.supprimerConnexion(con);
        con.getGenerateur().supprimerConnexion(con);
        con.getMaison().setConnexion(null);
        System.out.println("La connexion " + con.toString() + " a bien été modifié !");
        return true ;
    }

    /*
     * Permet à l'utilisateur de modifier une connexion existante.
     * La connexion est supprimée puis une nouvelle est créée.
     */
    public static void modifierConnexion(){
        if (supprimerConnexion()){
            creerConnexion();
        }
    }

    /**
     * Menu principal de création du réseau (option 1).
     * Permet de :
     * <ul>
     *   <li>Créer un générateur,</li>
     *   <li>Créer une maison,</li>
     *   <li>Créer une connexion.</li>
     * </ul>
     * Le programme passe au menu suivant une fois que le réseau est validé
     * lorsque l'utilisateur choisit l'option 4.
     */
    public static void menu1(){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion réseau --------------------");
            System.out.println("------------ Création d'un réseau (Option 1) -----------");
            System.out.println("1) ajouter un générateur;") ;
            System.out.println("2) ajouter une maison;") ;
            System.out.println("3) ajouter une connexion entre une maison et un générateur existants;");
            System.out.println("4) supprimer une connexion existante entre une maison et un générateur;");
            System.out.println("5) fin.") ;
            System.out.println("--------------------------------------------------------");

            int nb = sc.nextInt() ;
            sc.nextLine();

            switch(nb){
                case 1 : 
                    creerGenerateur();
                    break ;
                case 2 :
                    creerMaison();
                    break ;
                case 3 :
                    creerConnexion();
                    break ;
                case 4 :
                    supprimerConnexion();
                    break ;
                case 5 :
                    if (!reseau.validerReseau()){
                        System.out.println("Le réseau n'est pas valide");
                        break ;
                    }
                    fin = true ;
                    menu2() ;
                    break ;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }

    /**
     * Menu secondaire d'utilisation du réseau (option 2).
     * Permet de :
     * <ul>
     *   <li>Calculer le coût du réseau électrique actuel,</li>
     *   <li>Modifier une connexion,</li>
     *   <li>Afficher le réseau complet.</li>
     * </ul>
     * Le programme se termine lorsque l'utilisateur choisit l'option 4.
     */
    public static void menu2(){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion réseau --------------------");
            System.out.println("----------- Utilisation d'un réseau (Option 2) ---------");
            System.out.println("1) calculer le coût du réseau électrique actuel;") ;
            System.out.println("2) modifier une connexion;") ;
            System.out.println("3) afficher le réseau;");
            System.out.println("4) fin.") ;
            System.out.println("--------------------------------------------------------");

            int nb = sc.nextInt() ;
            sc.nextLine();

            switch(nb){
                case 1 :
                    System.out.println("Le coût du réseau électrique actuel est de : " + reseau.calculerCoutReseau() + " (La valeur de la sévérité de la pénalisation est de 10)");
                    break ;
                case 2 :
                    modifierConnexion();
                    break ;
                case 3 : 
                    System.out.println(reseau.toString());
                    break ;
                case 4 :
                    fin = true ;
                    System.out.println("Merci d'avoir utilisé ce programme !!!") ;
                    sc.close();
                    break ;
                default :
                    System.out.println("Ce n'est pas un choix valide !");
            }
        }
    }
}
