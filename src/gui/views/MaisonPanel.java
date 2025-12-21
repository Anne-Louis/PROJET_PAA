package gui.views;

import gui.controllers.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.components.Maison;
import main.components.NiveauConsommation;

/**
 * Panel de gestion des maisons.
 */
public class MaisonPanel extends VBox {

    private Label formLabel = new Label("Ajouter une Maison :");
    private Label nomLabel = new Label("Nom");
    private Label typeLabel = new Label("Catégorie");

    private TextField nomTextField = new TextField("nouvelle maison");
    private ComboBox<String> typeBox = new ComboBox<>();

    private Button ajouterButton = new Button("Ajouter");

    private ListView<Maison> maisonListView = new ListView<>();
    private static ObservableList<Maison> maisons = FXCollections.observableArrayList();

    private GridPane formPane = new GridPane();
    private VBox rightPane = new VBox();

    /**
     * Initialise le panel maison.
     */
    public MaisonPanel() {

        appliquerStyle();

        typeBox.getItems().addAll("BASSE", "NORMALE", "FORTE");
        typeBox.setValue("NORMALE");

        formPane.setHgap(10);
        formPane.setVgap(5);

        formPane.add(nomLabel, 0, 0);
        formPane.add(nomTextField, 0, 1);

        formPane.add(typeLabel, 1, 0);
        formPane.add(typeBox, 1, 1);

        formPane.add(ajouterButton, 0, 2);

        maisonListView.setItems(maisons);
        maisonListView.setPrefSize(400, 100);

        rightPane.setSpacing(5);
        rightPane.getChildren().addAll(
            new Label("Maisons ajoutées"),
            maisonListView
        );

        HBox content = new HBox(30, formPane, rightPane);
        content.setAlignment(Pos.CENTER_LEFT);

        this.setPadding(new Insets(5));
        this.getChildren().addAll(formLabel, new Separator(), content);

        ajouterButton.setOnAction(e -> ajouterMaison());
    }

    /**
     * Ajoute une maison via le contrôleur.
     */
    private void ajouterMaison() {
        String nom = nomTextField.getText().trim();
        String categorie = typeBox.getValue().trim();

        Maison m = Controleur.ajouterMaison(nom, NiveauConsommation.valueOf(categorie));
        if (m != null) maisons.add(m);

        nomTextField.setText("nouvelle maison");
        typeBox.setValue("NORMALE");
    }

    /**
     * Met à jour la liste des maisons.
     */
    public static void setMaisons(java.util.List<Maison> M){
        maisons.setAll(M);
    }

    /**
     * Applique les styles du panneau.
     */
    private void appliquerStyle() {

        this.setStyle(
            "-fx-background-color: #d6d8d6;" +
            "-fx-border-color: #9e9e9e;" +
            "-fx-border-width: 1.5;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;"
        );
        maisonListView.setStyle(
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        formLabel.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );

        nomLabel.setStyle("-fx-font-weight: bold;");
        typeLabel.setStyle("-fx-font-weight: bold;");

        nomTextField.setPrefWidth(140);
        typeBox.setPrefWidth(140);

        ajouterButton.setStyle(
            "-fx-background-color: #136ac1ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"+
            "-fx-background-radius: 8;"
        );
    }
}
