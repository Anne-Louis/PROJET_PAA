package gui.views;

import javafx.scene.control.Alert;

/**
 * Utilitaire d’affichage d’alertes JavaFX.
 */
public class Alerte extends Alert {
    /**
     * Crée une alerte du type donné.
     */
    public Alerte(AlertType arg0) {
        super(arg0);
    }

    /**
     * Affiche une alerte simple.
     */
    public static void showAlert(AlertType arg, String titre, String message){
        Alert alert = new Alerte(arg);

        alert.setTitle(titre);
        alert.setContentText(message);
        
        alert.show();
    }
}
