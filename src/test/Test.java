package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.components.*;
import main.menus.*;

public class Test {
    public static void main (String[] args){
        Generateur gen1 = new Generateur("gen1", 60);
        Generateur gen2 = new Generateur("gen2", 80);
        Generateur gen3 = new Generateur("gen3", 20);
        List <Generateur> generateurs = new ArrayList<>();
        generateurs.add(gen1);
        generateurs.add(gen2); 
        generateurs.add(gen3); 

        Maison msn1 = new Maison("maison1",NiveauConsommation.NORMAL);
        Maison msn2 = new Maison("maison2",NiveauConsommation.BASSE);
        Maison msn3 = new Maison("maison3",NiveauConsommation.FORTE);
        Maison msn4 = new Maison("maison4",NiveauConsommation.NORMAL);
        List <Maison> maisons = new ArrayList<>();
        maisons.add(msn1);
        maisons.add(msn2);
        maisons.add(msn3);
        maisons.add(msn4);

        Connexion con1 = new Connexion(gen1,msn1);
        gen1.getMaisons().add(con1);
        msn1.setConnexion(con1);
        Connexion con2 = new Connexion(gen2,msn2);
        gen2.getMaisons().add(con2);
        msn2.setConnexion(con2);
        Connexion con3 = new Connexion(gen2,msn4);
        gen2.getMaisons().add(con3);
        msn4.setConnexion(con3);
        Connexion con4 = new Connexion(gen3,msn3);
        gen3.getMaisons().add(con4);
        msn3.setConnexion(con4);
        List <Connexion> connexions = new ArrayList<>();
        connexions.add(con1);
        connexions.add(con2);
        connexions.add(con3);
        connexions.add(con4);


        Reseau reseau = new Reseau(generateurs, maisons, connexions);
        System.out.println(reseau);
        
        Scanner sc = new Scanner(System.in);
        Menu3.menu3(reseau, sc);
    }
}
