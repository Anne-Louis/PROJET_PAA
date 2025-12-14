package test.components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import main.components.*;


class ComponentsTest {
    private static Generateur gen1;
    private static Generateur gen2;
    private static Generateur gen3;

    private static Maison msn1;
    private static Maison msn2;
    private static Maison msn3;
    private static Maison msn4;

    private static Connexion con1;
    private static Connexion con2;
    private static Connexion con3;
    private static Connexion con4;

    private static List <Generateur> generateurs;
    private static List <Maison> maisons;
    private static List <Connexion> connexions;

    Generateur genTest;
    Maison msnTest;
    Connexion conTest;
    Reseau reseauTest;

    @BeforeAll
    static void beforeAll(){
        gen1 = new Generateur("gen1", 60);
        gen2 = new Generateur("gen2", 80);
        gen3 = new Generateur("gen3", 20);
        generateurs = new ArrayList<>();
        generateurs.add(gen1);
        generateurs.add(gen2); 
        generateurs.add(gen3); 

        msn1 = new Maison("maison1",NiveauConsommation.NORMALE);
        msn2 = new Maison("maison2",NiveauConsommation.BASSE);
        msn3 = new Maison("maison3",NiveauConsommation.FORTE);
        msn4 = new Maison("maison4",NiveauConsommation.NORMALE);
        maisons = new ArrayList<>();
        maisons.add(msn1);
        maisons.add(msn2);
        maisons.add(msn3);
        maisons.add(msn4);

        con1 = new Connexion(gen1,msn1);
        gen1.getMaisons().add(con1);
        msn1.setConnexion(con1);
        con2 = new Connexion(gen2,msn2);
        gen2.getMaisons().add(con2);
        msn2.setConnexion(con2);
        con3 = new Connexion(gen2,msn4);
        gen2.getMaisons().add(con3);
        msn4.setConnexion(con3);
        con4 = new Connexion(gen3,msn3);
        gen3.getMaisons().add(con4);
        msn3.setConnexion(con4);
        connexions = new ArrayList<>();
        connexions.add(con1);
        connexions.add(con2);
        connexions.add(con3);
        connexions.add(con4);
    }

    @BeforeEach
    void beforeEach(){
        genTest = null ;
        msnTest = null ;
        conTest = null ;
        reseauTest = null ;
    }

    @Test
    void creationGenerateurTest(){
        genTest = new Generateur("gen1", 60, connexions);
        assertEquals(gen1.getNom(), genTest.getNom());
        assertEquals(gen1.getCapacite(), genTest.getCapacite());
        assertEquals(connexions, genTest.getMaisons());
    }

    @Test
    void creationMaisonTest(){
        msnTest = new Maison("maison1", NiveauConsommation.NORMALE);
        msnTest.setConnexion(con1);
        assertEquals(msn1.getNiveau(), msnTest.getNiveau());
        assertEquals(NiveauConsommation.NORMALE, msnTest.getNiveau());
        assertEquals(msn1.getNom(), msnTest.getNom());
        assertEquals(NiveauConsommation.NORMALE.getValeur(), msnTest.getConsommation());
        assertEquals(con1, msnTest.getConnexion());
    }

    @Test
    void creationConnexionTest(){
        conTest = new Connexion(gen1, msn1);
        assertEquals(con1.getGenerateur(), conTest.getGenerateur());
        assertEquals(con1.getMaison(), conTest.getMaison());
    }

    @Test
    void calculTauxUtilisationEtChargeTest(){
        assertEquals(0.375, gen2.calculTauxUtilisation());
        assertEquals(1.0/3.0, gen1.calculTauxUtilisation());
        assertEquals(20, gen1.calculCharge());
        assertEquals(30, gen2.calculCharge());
    }
}
