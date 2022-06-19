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
public class WorkshopMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("WorkshopGUIView.fxml"));
            final Pane root = ldr.load();
            //final WorkshopGUIController workshopCtrl = (WorkshopGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("workshop.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Workshop");
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