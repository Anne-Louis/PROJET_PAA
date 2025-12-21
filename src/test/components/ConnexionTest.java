package test.components;

import static org.junit.jupiter.api.Assertions.*;

import main.components.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConnexionTest {

    private Generateur gen1, gen2;
    private Maison m1, m2;
    private Connexion connexion;

    @BeforeEach
    void beforeEach() {
        gen1 = new Generateur("G1", 100);
        gen2 = new Generateur("G2", 200);

        m1 = new Maison("M1", NiveauConsommation.BASSE);
        m2 = new Maison("M2", NiveauConsommation.NORMALE);

        connexion = new Connexion(gen1, m1);
    }

    @Test
    void testCreationEtGetters() {
        assertEquals(gen1, connexion.getGenerateur());
        assertEquals(m1, connexion.getMaison());
    }

    @Test
    void testSetters() {
        connexion.setGenerateur(gen2);
        connexion.setMaison(m2);

        assertEquals(gen2, connexion.getGenerateur());
        assertEquals(m2, connexion.getMaison());
    }

    @Test
    void testEqualsEtHashCode() {
        Connexion cIdentique = new Connexion(gen1, m1);
        Connexion cDiffGen = new Connexion(gen2, m1);
        Connexion cDiffMaison = new Connexion(gen1, m2);

        assertEquals(connexion, connexion);

        assertEquals(connexion, cIdentique);
        assertEquals(connexion.hashCode(), cIdentique.hashCode());

        assertNotEquals(connexion, cDiffGen);
        assertNotEquals(connexion, cDiffMaison);

        assertNotEquals(connexion, null);
        assertNotEquals(connexion, "test");
    }

    @Test
    void testToString() {
        String s = connexion.toString();
        assertTrue(s.contains(gen1.getNom()));
        assertTrue(s.contains(m1.getNom()));
    }
}
