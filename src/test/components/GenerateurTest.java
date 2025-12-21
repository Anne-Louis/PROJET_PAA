package test.components;

import static org.junit.jupiter.api.Assertions.*;

import main.components.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenerateurTest {

    private Generateur gen;
    private Maison m1;
    private Maison m2;

    @BeforeEach
    void beforeEach() {
        gen = new Generateur("G1", 100);

        m1 = new Maison("M1", NiveauConsommation.BASSE);   
        m2 = new Maison("M2", NiveauConsommation.FORTE); 
    }

    @Test
    void testCreationGenerateur() {
        assertEquals("G1", gen.getNom());
        assertEquals(100, gen.getCapacite());
        assertTrue(gen.getMaisons().isEmpty());
    }

    @Test
    void testAjoutConnexion() {
        Connexion c = new Connexion(gen, m1);
        gen.ajouterConnexion(c);

        assertEquals(1, gen.getMaisons().size());
        assertEquals(10, gen.calculCharge());
    }

    @Test
    void testSuppressionConnexion() {
        Connexion c = new Connexion(gen, m1);
        gen.ajouterConnexion(c);
        gen.supprimerConnexion(c);

        assertTrue(gen.getMaisons().isEmpty());
        assertEquals(0, gen.calculCharge());
    }

    @Test
    void testCalculChargePlusieursMaisons() {
        Connexion c1 = new Connexion(gen, m1); 
        Connexion c2 = new Connexion(gen, m2);

        gen.ajouterConnexion(c1);
        gen.ajouterConnexion(c2);

        assertEquals(50, gen.calculCharge());
    }

    @Test
    void testCalculTauxUtilisation() {
        Connexion c1 = new Connexion(gen, m1);
        Connexion c2 = new Connexion(gen, m2);

        gen.ajouterConnexion(c1);
        gen.ajouterConnexion(c2);

        double taux = gen.calculTauxUtilisation();
        assertEquals(0.5, taux, 1e-6);
    }

    @Test
    void testGenerateurSansMaison() {
        assertEquals(0, gen.calculCharge());
        assertEquals(0, gen.calculTauxUtilisation());
    }

    @Test
    void testConstructeurCopie() {
        Connexion c = new Connexion(gen, m1);
        gen.ajouterConnexion(c);

        Generateur copie = new Generateur(gen);

        assertEquals(gen.getNom(), copie.getNom());
        assertEquals(gen.getCapacite(), copie.getCapacite());
        assertTrue(copie.getMaisons().isEmpty(), "La copie ne doit pas partager les connexions");
    }

    @Test
    void testToString() {
        assertEquals("G1-100.0 KW", gen.toString());
    }
}
