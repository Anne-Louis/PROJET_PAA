package gui.views;

import gui.controllers.Controleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Panel de gestion globale du réseau.
 */
public class ReseauPanel extends VBox {
    private Label titreForm =new Label("Option:");

    private Button validerReseauBtn = new Button("Valider le reseau");
    private Button calculerCoutBtn = new Button("Optimiser le reseau");
    private Button afficherReseauBtn = new Button("Afficher le reseau");

    private Button sauvegarderBtn = new Button("Sauvegarder");
    private Button loadButton = new Button("Charger fichier");

    /**
     * Initialise le panel réseau.
     */
    public ReseauPanel() {

        appliquerStyle();

        this.setSpacing(12);
        this.setPadding(new Insets(4));
        this.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actionsPrincipales = new HBox(10,
            validerReseauBtn,
            calculerCoutBtn,
            afficherReseauBtn
        );

        HBox actionsFichier = new HBox(10,
                loadButton,
                sauvegarderBtn
        );
        HBox content = new HBox();

        content.getChildren().addAll(
                actionsPrincipales,
                spacer,
                actionsFichier
        );

        this.getChildren().addAll(
            titreForm,
            new Separator(),
            content
        );

        validerReseauBtn.setOnAction(e -> Controleur.validerReseau());
        calculerCoutBtn.setOnAction(e -> Controleur.optimiser(0.5));
        afficherReseauBtn.setOnAction(e -> Controleur.afficherReseau());
        sauvegarderBtn.setOnAction(e -> Controleur.sauvegarderReseau((Stage) this.getScene().getWindow() ));
        loadButton.setOnAction(e -> Controleur.chargerFichier( (Stage) this.getScene().getWindow() ));
    }

    /**
     * Applique le style du panel.
     */
    private void appliquerStyle() {

        this.setStyle(
            "-fx-background-color: #d4d6d4ff;" +
            "-fx-border-color: #a8aeb2ff;" +
            "-fx-border-width: 4;" +
            "-fx-border-radius:5 ;" +
            "-fx-background-radius: 5;"
        );
        
        titreForm.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );

        validerReseauBtn.setStyle(
            "-fx-background-color: #3b7c3dff;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;"+
            "-fx-font-weight: bold;"
        );

        calculerCoutBtn.setStyle(
            "-fx-background-color: #0d559dff;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-text-fill: white;"
        );

        afficherReseauBtn.setStyle(
            "-fx-background-color: #6f3a28ff;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-text-fill: white;"
        );

        sauvegarderBtn.setStyle(
            "-fx-background-color: #3f5a66ff;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-text-fill: white;"
        );

        loadButton.setStyle(
            "-fx-background-color: #546E7A;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-text-fill: white;"
        );
        
    }
}
