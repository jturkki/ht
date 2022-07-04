package fxRetriitti;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author jyrit
 * @version 12.6.2022
 *
 */
public class AloitusViewMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("AloitusViewGUIView.fxml"));
            final Pane root = ldr.load();
            // final AloitusViewGUIController aloitusviewCtrl = (AloitusViewGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("aloitusview.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("AloitusView");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}