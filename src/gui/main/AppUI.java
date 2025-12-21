package gui.main;

import gui.controllers.Controleur;
import gui.views.MainWindows;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d’entrée de l’interface graphique JavaFX.
 */
public class AppUI extends Application {

    /**
     * Initialise et affiche la fenêtre principale.
     */
    @Override
    public void start(Stage primaryScene) throws Exception {
        MainWindows mainWdws = new MainWindows();
        Controleur.updateWindows();

        Scene scene = new Scene(mainWdws);

        primaryScene.setTitle("Reseau électrique");
        primaryScene.setScene(scene);
        primaryScene.sizeToScene();
        primaryScene.show();
        primaryScene.centerOnScreen();
    }

    /**
     * Lance l’application JavaFX.
     */
    public static void main(String[] args){
        launch(args);
    }
}
