package gui.views;

import gui.controllers.Controleur;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.components.Generateur;
import main.components.Maison;

/**
 * Panel de création de connexion générateur–maison.
 */
public class ConnexionPanel extends VBox {
    private Label titreForm =  new Label("Ajouter une Connexion :");

    private Label choixGenLabel = new Label("Generateur");
    private Label choixMaisonLabel = new Label("Maison");

    private static ComboBox<Generateur> generateurBox = new ComboBox<>();
    private static ComboBox<Maison> maisonBox = new ComboBox<>();

    private static Button connecterButton = new Button("Connecter");

    private GridPane grid = new GridPane();

    public ConnexionPanel() {

        appliquerStyle();
        
        grid.setHgap(15);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));

        grid.add(choixGenLabel, 0, 0);
        grid.add(generateurBox, 0, 1);

        grid.add(choixMaisonLabel, 1, 0);
        grid.add(maisonBox, 1, 1);

        grid.add(connecterButton, 2, 1);

        connecterButton.setOnAction(e-> creerConnexion());

        this.setPadding(new Insets(5));
        this.setSpacing(5);
        this.getChildren().addAll(titreForm, new Separator(), grid);
    }

    /**
     * Met à jour la liste des générateurs.
     */
    public static void setGenerateurs(java.util.List<Generateur> generateurs) {
        generateurBox.getItems().setAll(generateurs);
    }

    /**
     * Met à jour la liste des maisons.
     */
    public static void setMaisons(java.util.List<Maison> maisons) {
        maisonBox.getItems().setAll(maisons);
    }

    /**
     * Crée une connexion via le contrôleur.
     */
    public void creerConnexion(){
        if(maisonBox.getItems().isEmpty() || generateurBox.getItems().isEmpty()){
            Alerte.showAlert(AlertType.ERROR, "Connexion impossible", "Generateur ou maison manquant(e)");
        }else{

            Maison m = maisonBox.getValue();
            Controleur.connecter(m, generateurBox.getValue());
            maisonBox.getItems().remove(m);
            Alerte.showAlert(AlertType.INFORMATION, "Connexion reussie", "Connexion ajoutee");
        }
    }

    /**
     * Applique le style graphique du panel.
     */
    private void appliquerStyle() {

        this.setStyle(
            "-fx-background-color: #d6d8d6;" +
            "-fx-border-color: #9e9e9e;" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;"
        );

        choixGenLabel.setStyle("-fx-font-weight: bold;");
        choixMaisonLabel.setStyle("-fx-font-weight: bold;");

        generateurBox.setPrefWidth(160);
        maisonBox.setPrefWidth(160);
        
        titreForm.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );

        connecterButton.setStyle(
            "-fx-background-color: #2E7D32;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-background-radius: 8;"
        );
    }
}
