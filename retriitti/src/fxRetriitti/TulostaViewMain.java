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
public class TulostaViewMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TulostaViewGUIView.fxml"));
            final Pane root = ldr.load();
            //final TulostaViewGUIController tulostaviewCtrl = (TulostaViewGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tulostaview.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("TulostaView");
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