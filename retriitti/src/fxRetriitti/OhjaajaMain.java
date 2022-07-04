package fxRetriitti;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author jyrit
 * @version 19.6.2022
 *
 */
public class OhjaajaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("OhjaajaGUIView.fxml"));
            final Pane root = ldr.load();
            // final OhjaajaGUIController ohjaajaCtrl = (OhjaajaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ohjaaja.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ohjaaja");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}