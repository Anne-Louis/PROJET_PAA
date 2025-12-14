package test.menus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.components.*;
import main.menus.*;

public class UtilMenuTest {
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

    private static Scanner sc;
    private static Reseau reseau;
    private static Reseau reseauTest;
    
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

        reseau = new Reseau(generateurs, maisons, connexions);
    }

    @BeforeEach
    void beforeEach(){
        reseauTest = new Reseau();
    }

    @Test
    void creerGenerateurTest(){
        sc = new Scanner("G1 60");
        UtilMenu.creerGenerateur(reseauTest, sc);
        
        assertEquals(1, reseauTest.getGenerateurs().size());
        assertEquals("G1", reseauTest.getGenerateurs().get(0).getNom());
        assertEquals(60, reseauTest.getGenerateurs().get(0).getCapacite());
    }

    @Test
    void modifierGenerateurTest(){
        sc = new Scanner("G1 60");
        reseauTest.ajouterGenerateur(new Generateur("G1", 80));
        UtilMenu.creerGenerateur(reseauTest, sc);

        assertEquals(1, reseauTest.getGenerateurs().size());
        assertEquals(60, reseauTest.getGenerateurs().get(0).getCapacite());
    }

    @Test
    void creerMaisonTest(){
        sc = new Scanner("M1 NORMAL");
        UtilMenu.creerMaison(reseauTest, sc);
        
        assertEquals(1, reseauTest.getMaisons().size());
        assertEquals("M1", reseauTest.getMaisons().get(0).getNom());
        assertEquals(NiveauConsommation.NORMALE, reseauTest.getMaisons().get(0).getNiveau());
    }

    @Test
    void modifierMaisonTest(){
        sc = new Scanner("M1 NORMAL");
        reseauTest.ajouterMaison(new Maison("M1", NiveauConsommation.FORTE));
        UtilMenu.creerMaison(reseauTest, sc);

        assertEquals(1, reseauTest.getMaisons().size());
        assertEquals(NiveauConsommation.NORMALE, reseauTest.getMaisons().get(0).getNiveau());
    }

    @Test
    void creerEtSupprimerConnexionTest(){
        sc = new Scanner("gen1 maison1");
        
    }

    @Test
    void sauvegarderSolutionTest() throws FileAlreadyExistsException, IOException{
        sc = new Scanner("test");
        UtilMenu.sauvegardeSolution(reseau, sc);

        boolean existe = false ;
        File dossier = new File("src/ressources/solutions/");
        File[] liste = dossier.listFiles();
        if (liste == null){
            existe = false ;
        }
        for (File f : liste) {
            if (f.getName().equals("test")) {
                existe = true ;
            }
        }

        assertTrue(existe);
        Files.deleteIfExists(Path.of("src/ressources/solutions/test"));
    }

    /*@Test
    void sauvegarderFichierNomDejaExistant() throws IOException{
        //Path cheminExistant = Path.of("src/ressources/configurations/simple1.txt");
        //Files.createFile(cheminExistant);

        Scanner sc = new Scanner("simple\ntest\n");

        assertThrows(FileAlreadyExistsException.class,
            () -> UtilMenu.sauvegardeSolution(reseau, sc));

        //Files.deleteIfExists(cheminExistant);
    }*/
}
