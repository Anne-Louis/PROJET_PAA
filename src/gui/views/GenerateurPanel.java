package gui.views;

import gui.controllers.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.components.Generateur;

/**
 * Panel de gestion des générateurs.
 */
public class GenerateurPanel extends VBox {

    private Label titreLabel = new Label("Formulaire Générateur :");
    private Label nomLabel = new Label("Nom");
    private Label capaciteLabel = new Label("Capacité en KW");

    private TextField nomTextField = new TextField("nouveau Generateur");
    private TextField capaciteTextField = new TextField("0.0");

    private Button ajouterButton = new Button("Ajouter");

    private ListView<Generateur> geneListView = new ListView<>();
    private static ObservableList<Generateur> generateurs = FXCollections.observableArrayList();

    /**
     * Initialise le panel générateur.
     */
    public GenerateurPanel() {

        appliquerStyle();

        GridPane form = new GridPane();
        form.setHgap(5);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        form.add(nomLabel, 0, 0);
        form.add(nomTextField, 0, 1);

        form.add(capaciteLabel, 1, 0);
        form.add(capaciteTextField, 1, 1);

        form.add(ajouterButton, 0, 2);

        geneListView.setItems(generateurs);
        geneListView.setPrefSize(400, 100);
        VBox rightBox = new VBox(5);
        rightBox.getChildren().addAll(new Label("Générateurs ajoutés"), geneListView);

        HBox contenu = new HBox(30, form, rightBox);
        contenu.setAlignment(Pos.CENTER_LEFT);

        this.setPadding(new Insets(5));
        this.getChildren().addAll(titreLabel, new Separator(),  contenu);

        ajouterButton.setOnAction(e -> ajouterGenerateur());
    }

    /**
     * Ajoute un générateur via le contrôleur.
     */
    private void ajouterGenerateur() {
        try {
            String nom = nomTextField.getText().trim();
            double capacite = Double.parseDouble(capaciteTextField.getText().trim());
            
            generateurs.add(Controleur.ajouterGenerateur(nom, capacite));

            nomTextField.setText("nouveau Generateur");
            capaciteTextField.setText("0.0");

        } catch (NumberFormatException ex) {
            System.out.println("Capacité invalide");
            Alerte.showAlert(AlertType.INFORMATION,"Champs vide", "La capcité doit etre un nombre");
        }
    }

    /**
     * Met à jour la liste des générateurs.
     */
    public static void setgenerateurs(java.util.List<Generateur> G){
        generateurs.setAll(G);
    }

    /**
     * Applique le style du panel.
     */
    private void appliquerStyle() {

        this.setStyle(
            "-fx-background-color: #d6d8d6;" +
            "-fx-border-color: #78909c;" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;"
        );
        geneListView.setStyle(
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        titreLabel.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );

        nomLabel.setStyle("-fx-font-weight: bold;");
        capaciteLabel.setStyle("-fx-font-weight: bold;");

        nomTextField.setPrefWidth(130);
        capaciteTextField.setPrefWidth(80);

        ajouterButton.setStyle(
            "-fx-background-color: #136ac1ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-background-radius: 8;"
        );
    }
}
