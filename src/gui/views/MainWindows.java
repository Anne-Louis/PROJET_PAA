package gui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Conteneur principal de l'application Reseau
 * structure et place chaque les differents panneaux
 */
public class MainWindows extends VBox {

    private AffichagePanel affPanel   = new AffichagePanel();
    private GenerateurPanel genPanel  = new GenerateurPanel();
    private MaisonPanel msnPanel      = new MaisonPanel();
    private ConnexionPanel connPanel  = new ConnexionPanel();
    private ReseauPanel reseauPanel   = new ReseauPanel();

    /**
     * Construit la fenêtre principale.
     */
    public MainWindows() {
        fixeMainPage();
    }

    /**
     * Configure la disposition et ajoute les panneaux.
     */
    public void fixeMainPage(){

        this.setSpacing(5);
        this.setPrefWidth(850);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #e4f2fdff;");
        this.getChildren().addAll(
            affPanel,
            genPanel,
            msnPanel,
            connPanel,
            reseauPanel
        );
    }
}
