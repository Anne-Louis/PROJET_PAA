package test.io;

import main.components.*;
import main.exceptions.ImportException;
import main.exceptions.InvalideReseauException;
import main.io.ParseFile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class ParseFileTest {
    private Generateur g1, g2 ;
    private Maison m1, m2, m3 ;
    private Connexion c1, c2, c3 ;
    private Reseau reseau, reseauCharge;
    
    @BeforeEach
    void beforeEach(){
        g1 = new Generateur("gen1",50);
        g2 = new Generateur("gen2",40);

        m1 = new Maison("maison1",NiveauConsommation.FORTE);
        m2 = new Maison("maison2",NiveauConsommation.FORTE);
        m3 = new Maison("maison3",NiveauConsommation.BASSE);

        c1 = new Connexion(g1, m1);
        c2 = new Connexion(g1, m2);
        c3 = new Connexion(g1, m3);

        reseau = new Reseau();
        reseau.ajouterGenerateur(g2);
        reseau.ajouterConnexion(c1);
        reseau.ajouterConnexion(c2);
        reseau.ajouterConnexion(c3);

        reseauCharge = null;
    }

    @Test
    void chargerFichier(){
        try {
            reseauCharge = ParseFile.importerReseau("src" + File.separator + "ressources" + File.separator + "configurations" + File.separator + "simple.txt");
        } catch (ImportException e) {
            System.out.println(e);
        }
        assertEquals(reseauCharge.calculerCoutReseau(), reseau.calculerCoutReseau());
    }

    @Test
    void erreurChargerFichierConsommationDepasseProduction(){
        try {
            reseauCharge = ParseFile.importerReseau("src" + File.separator + "ressources" + File.separator + "configurations" + File.separator + "instance_fausse1.txt");
        } catch (ImportException e) {
            System.out.println(e);
        }
        InvalideReseauException e = assertThrows(InvalideReseauException.class,() -> reseauCharge.validerReseau());
        assertTrue(e.getMessage().contains("depasse la production"));
    }

    @Test
    void erreurChargerFichierMauvaisOrdre(){
        ImportException e = assertThrows(ImportException.class,() -> reseauCharge = ParseFile.importerReseau("src" + File.separator + "ressources" + File.separator + "configurations" + File.separator + "instance_fausse2.txt"));
        assertTrue(e != null);
        assertTrue(reseauCharge == null);
    }

    @Test
    void erreurChargerFichierGenerateurInexistant(){
        ImportException e = assertThrows(ImportException.class,() -> reseauCharge = ParseFile.importerReseau("src" + File.separator + "ressources" + File.separator + "configurations" + File.separator + "instance_fausse3.txt"));
        assertTrue(e != null);
        assertTrue(reseauCharge == null);
    }

    @Test
    void erreurChargerFichierManqueParametre(){
        ImportException e = assertThrows(ImportException.class,() -> reseauCharge = ParseFile.importerReseau("src" + File.separator + "ressources" + File.separator + "configurations" + File.separator + "instance_fausse4.txt"));
        assertTrue(e != null);
        assertTrue(reseauCharge == null);
    }
}
