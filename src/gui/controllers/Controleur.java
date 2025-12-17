package gui.controllers;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.Optional;

import gui.views.AffichagePanel;
import gui.views.Alerte;
import gui.views.ConnexionPanel;
import gui.views.GenerateurPanel;
import gui.views.MaisonPanel;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.algorithmes.Algorithme1;
import main.components.Connexion;
import main.components.Generateur;
import main.components.Maison;
import main.components.NiveauConsommation;
import main.components.Reseau;
import main.exceptions.ImportException;
import main.exceptions.InvalideReseauException;

/**
 * Contrôleur principal reliant l’interface graphique et le modèle du réseau.
 */
public class Controleur {
    private static Reseau reseau;

    /**
     * Initialise un nouveau réseau.
     */
    public Controleur(){
        Controleur.reseau = new Reseau();
    }

    /**
     * Ajoute un générateur au réseau.
     */
    public static Generateur ajouterGenerateur(String nom, double capacite) { 
        Generateur g = null;
        if (nom == null || nom.isEmpty() || !(Character.isLetter(nom.charAt(0))) ){
            Alerte.showAlert(AlertType.INFORMATION, "Erreur de saisie", "Entrer le nom d");
            return g;
        }else{
            g = reseau.trouverGenerateurParNom(nom);
            if(g == null){
                g = new Generateur(nom, capacite);
                reseau.ajouterGenerateur(g);
                ConnexionPanel.setGenerateurs(reseau.getGenerateurs());
                AffichagePanel.afficherTexte("");

                return g;
            }
        }

        return g;
    }

    /**
     * Ajoute une maison au réseau.
     */
    public static Maison ajouterMaison(String nom, NiveauConsommation conso) {
        if(nom.isEmpty() || conso == null || !(Character.isLetter(nom.charAt(0))) ){

            Alerte.showAlert(AlertType.ERROR,"champs incorrect" ,"Le nom de la maison est incorrect");
            return null;
        }

        Maison m = reseau.trouverMaisonParNom(nom);
        if(m != null){
            Alerte.showAlert(AlertType.INFORMATION, "Info", m + "existe");
            return null;
        }
        m = new Maison(nom, conso);
        reseau.ajouterMaison(m);
        ConnexionPanel.setMaisons(reseau.getMaisons());
        AffichagePanel.afficherTexte("");

        return m;
    }

    /**
     * Connecte une maison à un générateur.
     */
    public static void connecter(Maison m, Generateur g) { 
        Connexion conn = new Connexion(g, m);
        reseau.ajouterConnexion(conn);
        AffichagePanel.afficherTexte("");
    }

    /**
     * Valide la cohérence du réseau.
     */
    public static void validerReseau() {  
        try{
            if(reseau.validerReseau()){
                AffichagePanel.afficherTexte("");
                Alerte.showAlert(AlertType.CONFIRMATION, "Etat Reseau", "Reseau valide");
            }
        }catch(InvalideReseauException e){
            AffichagePanel.afficherTexte("RESEAU INVALIDE :\n" + e.getMessage());
        }
    }
    
    /**
     * Calcule et affiche le coût du réseau.
     */
    public static void calculerCoutReseaiu() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Paramettre réseau");
        dialog.setContentText("Saisie la valeur lamda du réseau (par default lamda = 10) ");
        
        Optional<String> textLamda = dialog.showAndWait();
        int lambda = 10;
        
        if(textLamda.isPresent() && !textLamda.get().isEmpty()){
            try{
                lambda = Integer.parseInt(textLamda.get());
            }
            catch(Exception e){
                Alerte.showAlert(AlertType.ERROR, "lamda incorrecte", "Lambda doit etre un nombre");
            }
        }

        reseau.setLambda(lambda);
        String text = "LE COUT DU RESEAU:\n lamda = " + lambda + "\n" + reseau.calculerCoutReseau();
        AffichagePanel.afficherTexte(text);
    }
    
    /**
     * Optimise le réseau selon un seuil donné.
     */
    public static void optimiser(double epsilon) {
        reseau = Algorithme1.resoudreReseau(reseau, epsilon);
        calculerCoutReseaiu();
    }

    /**
     * Charge un réseau depuis un fichier.
     */
    public static void chargerFichier(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choisir un fichier");
        File file = fileChooser.showOpenDialog(stage);

        if(file == null)
            Alerte.showAlert(AlertType.ERROR, "Fichier Reseau", "Ce fichier n'existe pas");
        else{
            try{
                reseau = main.io.ParseFile.importerReseau(file.getAbsolutePath());
                AffichagePanel.afficherTexte("Le fichier chargé : "+ file.getAbsolutePath() + "\n"+ reseau.calculerCoutReseau());

                ConnexionPanel.setGenerateurs(reseau.getGenerateurs());
                GenerateurPanel.setgenerateurs(reseau.getGenerateurs());
                MaisonPanel.setMaisons(reseau.getMaisons());
            }catch(ImportException e){
                Alerte.showAlert(AlertType.ERROR, "Fichier reseau", e.getMessage());
                AffichagePanel.afficherTexte(e.getMessage());
            }
        }
    }

    /**
     * Sauvegarde le réseau courant.
     */
    public static void sauvegarderReseau(Stage stage){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Sauvegarde de l'instance ");
        dialog.setContentText("Saisie le chemin complet du fichier sans extantion (ex: instance1) ");
        
        Optional<String> textLamda = dialog.showAndWait();
        String fileName = "ReseauOptimisé";
        
        if(textLamda.isPresent() && !textLamda.get().isEmpty()){
                fileName = textLamda.get();
        }
        try{
            Alerte.showAlert(AlertType.CONFIRMATION, "Sauvegarde", "l'instance sera enregister dans le fichier: src/ressources/solutions/" + fileName + ".txt");
            main.io.SauvegardeReseau.sauvegardeReseau(reseau, fileName);
            
            AffichagePanel.afficherTexte("Le fichier est enregistré");
        }catch(FileAlreadyExistsException e){
            Alerte.showAlert(AlertType.ERROR, "Fichier reseau", e.getMessage());
            AffichagePanel.afficherTexte(e.getMessage());
        }catch(Exception e){
            Alerte.showAlert(AlertType.ERROR, "Fichier reseau", "Une erreur de sauvegarde de la solution est survenue");   
        }
    }
    
    /**
     * Affiche le réseau courant.
     */
    public static void afficherReseau(){
        AffichagePanel.afficherTexte(reseau.toString());
    }
}
