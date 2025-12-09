package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.components.*;
import main.menus.*;

public class Test {
    public static void main (String[] args){
        //instance1
        /*Generateur gen1 = new Generateur("gen1", 60);
        Generateur gen2 = new Generateur("gen2", 45);
        Generateur gen3 = new Generateur("gen3", 20);
        Generateur gen4 = new Generateur("gen4", 42);
        Generateur gen5 = new Generateur("gen5", 35);
        Generateur gen6 = new Generateur("gen6", 90);
        List <Generateur> generateurs = new ArrayList<>();
        generateurs.add(gen1);
        generateurs.add(gen2); 
        generateurs.add(gen3);
        generateurs.add(gen4);
        generateurs.add(gen5); 
        generateurs.add(gen6);  

        Maison msn1 = new Maison("maison1",NiveauConsommation.NORMALE);
        Maison msn2 = new Maison("maison2",NiveauConsommation.BASSE);

        Maison msn3 = new Maison("maison3",NiveauConsommation.NORMALE);
        Maison msn4 = new Maison("maison4",NiveauConsommation.FORTE);
        Maison msn5 = new Maison("maison5",NiveauConsommation.FORTE);
        Maison msn6 = new Maison("maison6",NiveauConsommation.NORMALE);
        Maison msn7 = new Maison("maison7",NiveauConsommation.NORMALE);
        Maison msn8 = new Maison("maison8",NiveauConsommation.FORTE);
        Maison msn9 = new Maison("maison9",NiveauConsommation.BASSE);

        List <Maison> maisons = new ArrayList<>();
        maisons.add(msn1);
        maisons.add(msn2);
        maisons.add(msn3);
        maisons.add(msn4);
        maisons.add(msn5);
        maisons.add(msn6);
        maisons.add(msn7);
        maisons.add(msn8);
        maisons.add(msn9);

        Connexion con1 = new Connexion(gen1,msn1);
        gen1.getMaisons().add(con1);
        msn1.setConnexion(con1);
        Connexion con2 = new Connexion(gen2,msn2);
        gen2.getMaisons().add(con2);
        msn2.setConnexion(con2);
        Connexion con3 = new Connexion(gen3,msn3);
        gen2.getMaisons().add(con3);
        msn4.setConnexion(con3);
        Connexion con4 = new Connexion(gen4,msn4);
        gen3.getMaisons().add(con4);
        msn3.setConnexion(con4);
        Connexion con5 = new Connexion(gen2,msn5);
        gen1.getMaisons().add(con1);
        msn1.setConnexion(con1);
        Connexion con6 = new Connexion(gen3,msn6);
        gen2.getMaisons().add(con2);
        msn2.setConnexion(con2);
        Connexion con7 = new Connexion(gen4,msn7);
        gen2.getMaisons().add(con3);
        msn4.setConnexion(con3);
        Connexion con8 = new Connexion(gen2,msn8);
        gen3.getMaisons().add(con4);
        msn3.setConnexion(con4);
        Connexion con9 = new Connexion(gen3,msn9);
        gen3.getMaisons().add(con4);
        msn3.setConnexion(con4);
        List <Connexion> connexions = new ArrayList<>();
        connexions.add(con1);
        connexions.add(con2);
        connexions.add(con3);
        connexions.add(con4);
        connexions.add(con5);
        connexions.add(con6);
        connexions.add(con7);
        connexions.add(con8);
        connexions.add(con9);


        Reseau reseau = new Reseau(generateurs, maisons, connexions);
        System.out.println(reseau);
        System.out.println(reseau.calculerCoutReseau());
        
        Scanner sc = new Scanner(System.in);*/
        Reseau reseau = new Reseau() ;
        Scanner sc = new Scanner(System.in);
        Menu3.menu3(reseau, sc);
    }
}
