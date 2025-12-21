package test.io;

import main.components.*;
import main.io.SauvegardeReseau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

public class SauvegardeReseauTest {
    private Generateur g1, g2 ;
    private Maison m1, m2, m3 ;
    private Connexion c1, c2, c3 ;
    private Reseau reseau;

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
    }

    @ParameterizedTest
    @ValueSource(strings = {"instance1","instance2","instance_tres_grande1","simple"})
    void nomFichierDejaPris(String nom){
        FileAlreadyExistsException e = assertThrows(FileAlreadyExistsException.class,() -> SauvegardeReseau.sauvegardeReseau(reseau, nom));
        assertTrue(e.getMessage().contains("porte deja ce nom"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test1","test2","toutMarche","42"})
    void fichierSauvegarde(String nom){
        try {
            SauvegardeReseau.sauvegardeReseau(reseau, nom);
        } catch (FileAlreadyExistsException e){
            System.out.println(e);
        }
        File fichier = new File("src" + File.separator + "ressources" + File.separator + "solutions" + File.separator + nom + ".txt");
        assertTrue(fichier.exists());
        fichier.delete();
    }
}
