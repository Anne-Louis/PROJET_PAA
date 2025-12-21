package test.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.components.*;

public class MaisonTest {

    private Maison maison;

    @BeforeEach
    void beforeEach() {
        maison = new Maison("M1", NiveauConsommation.BASSE);
    }

    @Test
    void testCreationMaison() {
        assertEquals("M1", maison.getNom());
        assertEquals(NiveauConsommation.BASSE, maison.getNiveau());
        assertEquals(10, maison.getConsommation());
        assertNull(maison.getConnexion());
    }

    @Test
    void testChangementConsommation() {
        maison.setConsommation(NiveauConsommation.FORTE);

        assertEquals(NiveauConsommation.FORTE, maison.getNiveau());
        assertEquals(40, maison.getConsommation());
    }

    @Test
    void testAjoutConnexion() {
        Generateur gen = new Generateur("G1", 100);
        Connexion c = new Connexion(gen, maison);

        maison.setConnexion(c);

        assertNotNull(maison.getConnexion());
        assertEquals(gen, maison.getConnexion().getGenerateur());
    }

    @Test
    void testSuppressionConnexion() {
        Generateur gen = new Generateur("G1", 100);
        Connexion c = new Connexion(gen, maison);

        maison.setConnexion(c);
        maison.setConnexion(null);

        assertNull(maison.getConnexion());
    }

    @Test
    void testToStringSansConnexion() {
        String s = maison.toString();

        assertTrue(s.contains("M1"));
        assertTrue(s.contains("BASSE"));
        assertTrue(s.contains("10"));
        assertTrue(s.contains("Aucun"));
    }

    @Test
    void testToStringAvecConnexion() {
        Generateur gen = new Generateur("G1", 100);
        Connexion c = new Connexion(gen, maison);
        maison.setConnexion(c);

        String s = maison.toString();

        assertTrue(s.contains("M1"));
        assertTrue(s.contains("G1"));
    }
}
