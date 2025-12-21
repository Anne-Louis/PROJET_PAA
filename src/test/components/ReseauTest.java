package test.components;

import main.components.*;
import main.exceptions.InvalideReseauException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReseauTest {

    private Reseau reseau;
    private Generateur g1, g2;
    private Maison m1, m2, m3;
    private Connexion c1, c2;

    @BeforeEach
    void beforeEach() {
        reseau = new Reseau();

        g1 = new Generateur("G1", 50);
        g2 = new Generateur("G2", 40);

        m1 = new Maison("M1", NiveauConsommation.BASSE);   
        m2 = new Maison("M2", NiveauConsommation.NORMALE);  
        m3 = new Maison("M3", NiveauConsommation.FORTE);    

        c1 = new Connexion(g1, m1);
        c2 = new Connexion(g2, m2);

        reseau.ajouterConnexion(c1); 
        reseau.ajouterConnexion(c2);
    }

    @Test
    void testAjoutMaisonsEtGenerateurs() {
        assertTrue(reseau.getMaisons().contains(m1));
        assertTrue(reseau.getMaisons().contains(m2));
        assertTrue(reseau.getGenerateurs().contains(g1));
        assertTrue(reseau.getGenerateurs().contains(g2));
    }

    @Test
    void testTrouverMaisonEtGenerateurParNom() {
        assertEquals(m1, reseau.trouverMaisonParNom("M1"));
        assertEquals(g2, reseau.trouverGenerateurParNom("G2"));
        assertNull(reseau.trouverMaisonParNom("Inexistante"));
        assertNull(reseau.trouverGenerateurParNom("Inexistant"));
    }

    @Test
    void testTrouverConnexion() {
        assertEquals(c1, reseau.trouverConnexion("M1", "G1"));
        assertNull(reseau.trouverConnexion("M3", "G1"));
    }

    @Test
    void testConnecterMaisonGenerateur() {
        reseau.ajouterMaison(m3);
        reseau.connecterMaisonGenerateur("M3", "G1");
        Connexion c = reseau.trouverConnexion("M3", "G1");

        assertNotNull(c);
        assertEquals(g1, c.getGenerateur());
        assertEquals(m3, c.getMaison());
    }

    @Test
    void testModifierConnexion() {
        Connexion newConn = new Connexion(g2, m1);
        assertTrue(reseau.modifierConnexion(c1, newConn));
        assertEquals(newConn, reseau.trouverConnexion("M1", "G2"));

        Connexion nonExistante = new Connexion(g1, m3);
        assertFalse(reseau.modifierConnexion(nonExistante, newConn));

        assertFalse(reseau.modifierConnexion(c2, null));
    }

    @Test
    void testValiderReseauValide() throws InvalideReseauException {
        assertTrue(reseau.validerReseau());
    }

    @Test
    void testValiderReseauSurcharge() {
        Maison m4 = new Maison("M4", NiveauConsommation.FORTE);
        reseau.ajouterConnexion(new Connexion(g1, m4));
        reseau.ajouterConnexion(new Connexion(g1, m3));

        InvalideReseauException e = assertThrows(InvalideReseauException.class,() -> reseau.validerReseau());
        assertTrue(e.getMessage().contains("depasse la production"));
    }

    @Test
    void testCalculerCoutReseau() {
        double cout = reseau.calculerCoutReseau();
        assertTrue(cout >= 0);
    }

    @Test
    void testCopierReseau() {
        Reseau copie = reseau.copierReseau();
        assertEquals(reseau.getMaisons().size(), copie.getMaisons().size());
        assertEquals(reseau.getGenerateurs().size(), copie.getGenerateurs().size());
        assertEquals(reseau.getConnexions().size(), copie.getConnexions().size());

        assertNotSame(reseau.getConnexions().get(0), copie.getConnexions().get(0));
        assertNotSame(reseau.getMaisons().get(0), copie.getMaisons().get(0));
        assertNotSame(reseau.getGenerateurs().get(0), copie.getGenerateurs().get(0));
    }

    @Test
    void testAjouterConnexionNullOuDuplique() {
        assertFalse(reseau.ajouterConnexion(null));

        Connexion duplicate = new Connexion(g1, m1);
        assertFalse(reseau.ajouterConnexion(duplicate));
    }

    @Test
    void testSupprimerConnexion() {
        assertFalse(reseau.supprimerConnexion(null));
        assertTrue(reseau.supprimerConnexion(c1));
        assertNull(m1.getConnexion());
        assertFalse(g1.getMaisons().contains(c1));
    }

    @Test
    void testClearReseau() {
        reseau.clear();
        assertTrue(reseau.getConnexions().isEmpty());
        for (Generateur g : reseau.getGenerateurs()) {
            assertTrue(g.getMaisons().isEmpty());
        }
        for (Maison m : reseau.getMaisons()) {
            assertNull(m.getConnexion());
        }
    }

    @Test
    void testToString() {
        String s = reseau.toString();
        assertNotNull(s);
        assertTrue(s.contains("RESEAU ELECTRIQUE"));
        assertTrue(s.contains("G1"));
        assertTrue(s.contains("G2"));
    }
}

