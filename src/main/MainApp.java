package main;

import java.util.Scanner ;

public class MainApp {
    private static Reseau reseau = new Reseau() ;
    private static Scanner sc = new Scanner(System.in) ;

    public static void menu1(){
        boolean fin = false ;

        while (!fin){
            System.out.println("-------------------- Gestion réseau --------------------");
            System.out.println("------------ Création d'un réseau (Option 1) -----------");
            System.out.println("1) ajouter un générateur;") ;
            System.out.println("2) ajouter une maison;") ;
            System.out.println("3) ajouter une connexion entre une maison et un générateur existants;");
            System.out.println("4) fin.") ;
            System.out.println("--------------------------------------------------------");

            int nb = sc.nextInt() ;
            sc.nextLine();
            String ligne ;
            String nom ;

            switch(nb){
                case 1 : 
                    System.out.println("Veuillez saisir le générateur à enregistrer ainsi que sa puissance (ex : G1 60) : ");
                    ligne = sc.nextLine() ;
                    nom = ligne.split("\\s")[0] ;
                    double cap = Double.parseDouble(ligne.split("\\s")[1]) ;

                    for (Generateur g : reseau.getGenerateurs()){
                        if (g.getNom().equals(nom)){
                            g.setcapacite(cap);
                            System.out.println("Le générateur " + nom + " existe déjà, sa capacité à été mise à jour : " + cap + "kW") ;
                        }
                    }
                    Generateur gen = new Generateur(nom, cap);
                    reseau.ajouterGenerateur(gen) ;
                    System.out.println("Le générateur a bien été crée !");
                    break ;

                case 2 :
                    System.out.println("Veuillez saisir la maison à enregistrer ainsi que sa consommation (ex : M1 NORMALE) : ");
                    ligne = sc.nextLine() ;
                    nom = ligne.split("\\s")[0] ;
                    NiveauConsommation cons = NiveauConsommation.valueOf(ligne.split("\\s")[1]) ;
                    
                    for (Maison m : reseau.getMaisons()){
                        if (m.getNom().equals(nom)){
                            m.setConsommation(cons) ;
                            System.out.println("La maison " + nom + " existe déjà, sa consommation à été mise à jour : " + cons) ;
                        }
                    }
                    Maison maison = new Maison(nom, cons);
                    reseau.ajouterMaison(maison) ;
                    System.out.println("La maison a bien été crée !");
                    break ;

                case 3 :
                    System.out.println("Veuillez saisir la connextion à enregistrer  (ex : G1 M1 ou M1 G1) : ");
                    ligne = sc.nextLine() ;
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
                        System.out.println("Le générateur n'existe pas, la création de la connexion n'est pas possible");
                        break ;
                    }
                    if (maiC == null){
                        System.out.println("La maison n'existe pas, la création de la connexion n'est pas possible");
                        break ;
                    }

                    Connexion con = new Connexion(genC, maiC) ;

                    for (Connexion c : reseau.getConnexions()){
                        if (con.equals(c)){
                            System.out.println("Cette connexion existe déjà") ;
                            break ;
                        }
                    }
                    genC.ajouterConnexion(con);
                    maiC.setConnexion(con) ;
                    reseau.ajouterConnexion(con) ;
                    System.out.println("La connexion a bien été crée !");
                    break ;

                case 4 :
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
            String ligne ;

            switch(nb){
                case 1 :
                    System.out.println("Le coût du réseau électrique actuel est de : " + reseau.calculerCoutReseau() + " (La valeur de la sévérité de la pénalisation est de 10)");
                    break ;
                case 2 :
                    System.out.println("Veuillez saisir la connexion que vous souhaitez modifier : ");
                    ligne = sc.nextLine();
                    Connexion oldCon = null ;
                    for (Connexion c : reseau.getConnexions()){
                        if ((c.getGenerateur().getNom().equals(ligne.split("\\s")[0])) && (c.getGenerateur().getNom().equals(ligne.split("\\s")[1]))){
                            oldCon = c ;
                        } else if ((c.getGenerateur().getNom().equals(ligne.split("\\s")[1])) && (c.getGenerateur().getNom().equals(ligne.split("\\s")[0]))){
                            oldCon = c ;
                        }
                    }
                    if (oldCon == null){
                        System.out.println("Cette connexion n'existe pas !") ;
                        break ;
                    }

                    System.out.println("Veuillez saisir la nouvelle connexion : ");
                    ligne = sc.nextLine() ;
                    

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
