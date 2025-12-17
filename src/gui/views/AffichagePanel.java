package gui.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * Panneau d'affichage des options du réseau
 */
public class AffichagePanel extends VBox {

    private static TextArea affichage;
    private Label titreLabel;

    public AffichagePanel() {

        affichage = new TextArea();
        affichage.setEditable(false);
        affichage.setWrapText(true);

        titreLabel = new Label("Affichage du Reseau");


        appliquerStyle();

        affichage.setPrefWidth(600);
        affichage.setPrefHeight(130);

        affichage.setLayoutX(10);
        affichage.setLayoutY(10);

        this.setPadding(new Insets(5));
        this.setSpacing(3);
        this.getChildren().addAll(titreLabel, new Separator(), affichage);
    }

    /**
     *  Affiche un texte en remplaçant l'existant 
    */
    public static void afficherTexte(String texte) {
        affichage.setText(texte);
    }

    private void appliquerStyle() {

        this.setStyle(
            "-fx-background-color: #d6d8d6;" +
            "-fx-border-color: #78909c;" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 10;"
        );
        affichage.setStyle(
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        affichage.setStyle(
            "-fx-font-family: Consolas;" +
            "-fx-font-size: 13px;" +
            "-fx-control-inner-background: #f8f3f3ff;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;"
        );

        titreLabel.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;"
        );
    }
}
